package com.msplearning.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.App;
import com.msplearning.entity.AppFeature;
import com.msplearning.entity.AppUser;
import com.msplearning.entity.AppUserId;
import com.msplearning.repository.AppRepository;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The AppService class provides the business operations of entity {@link App}.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("appService")
public class AppService extends GenericCrudService<App, Long> {

	private static final String SERVER_APK_PATH = "%1$s" + File.separator + "apps"  + File.separator + "%2$s" + File.separator + "MSPLearning.apk";

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppRepository appRepository;

	@Override
	protected GenericRepository<App, Long> getRepository() {
		return this.appRepository;
	}

	public void insert(App entity, Long idUser) {
		// Configuration and persistence of App:
		entity.setDateCreation(new Date());
		for (AppFeature appFeature : entity.getAppFeatures()) {
			appFeature.getId().setApp(entity);
		}
		super.insert(entity);
		for (AppFeature appFeature : entity.getAppFeatures()) {
			appFeature.getId().setApp(new App(entity.getId()));
		}
		// Configuration and persistence of App Administrator:
		AppUser appUser = new AppUser();
		appUser.setId(new AppUserId(entity.getId(), idUser));
		appUser.setAdmin(true);
		appUser.setActive(true);
		this.appUserService.insert(appUser);
	}

	public List<App> findByUser(Long idUser) {
		return this.appUserService.findAppsByUser(idUser);
	}

	/**
	 * Java 7 Skills! ;)
	 */
	public void generateApk(Long idApp, String rootPath) {
		try {
			// Creates the full path to generate the custom APK: %ROOT_PATH%/apps/{idApp}/MSPLearning.apk
			File customApk = new File(String.format(SERVER_APK_PATH, rootPath, idApp));
			// Creates directories (if necessary)
			customApk.getParentFile().mkdirs();
			// Duplicates the base APK (%ROOT_PATH%/apps/base/MSPLearning.apk) in the custom APK folder
			Path pathBaseApk = Paths.get(String.format(SERVER_APK_PATH, rootPath, "base"));
			Path pathCustomAPK = Paths.get(customApk.getPath());
			Files.copy(pathBaseApk, pathCustomAPK, StandardCopyOption.REPLACE_EXISTING);
			// Creation of product.properties file to replace your similar on custom APK
			File productPropertiesToReplace = new File(customApk.getParentFile().getPath() + File.separator + "product.properties");
			productPropertiesToReplace.createNewFile();
			try(FileWriter fw = new FileWriter(productPropertiesToReplace.getAbsoluteFile()); BufferedWriter bw = new BufferedWriter(fw)) {
				bw.write("msplearning.app.id=" + idApp);
			}
			// Replace the product.properties file in custom APK
			Path pathProductPropertiesToReplace = Paths.get(productPropertiesToReplace.getPath());
		    try (FileSystem fs= FileSystems.newFileSystem(pathCustomAPK, null)) {
		        Path pathProductProperties = fs.getPath("/assets/product.properties");
		        Files.copy(pathProductPropertiesToReplace, pathProductProperties, StandardCopyOption.REPLACE_EXISTING);
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		    	productPropertiesToReplace.delete();
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
