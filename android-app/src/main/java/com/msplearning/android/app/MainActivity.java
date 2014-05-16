package com.msplearning.android.app;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.rest.RestService;

import com.msplearning.android.app.generic.GenericAsyncActivity;
import com.msplearning.android.app.rest.AppRestClient;
import com.msplearning.entity.AppFeature;
import com.msplearning.entity.AppUserId;
import com.msplearning.entity.Variability;

/**
 * The MainActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity
public class MainActivity extends GenericAsyncActivity<MSPLearningApplication> {

	@RestService
	protected AppRestClient mAppRestClient;

	@AfterInject
	protected void init() {
		try {
			// Load "product.properties", file where the id of the application in question is stored.
			Properties properties = new Properties();
			properties.load(this.getResources().getAssets().open("product.properties"));
			Long idApp = Long.parseLong(properties.getProperty("msplearning.app.id", BigInteger.ZERO.toString()));

			this.resolveAuthenticityVariability(idApp);
		} catch (IOException e) {
			this.showDialogAlert("Unable to load the properties file.", null);
		}
	}

	/**
	 * XXX Variation Point: Authenticity.
	 *
	 * @param idApp
	 */
	@Background
	protected void resolveAuthenticityVariability(Long idApp) {
		try {
			AppUserId appSetings = new AppUserId();
			appSetings.setApp(this.mAppRestClient.findById(idApp).getEntity());
			this.getApplicationContext().setAppSettings(appSetings);

			boolean hasAuth = false;
			for (AppFeature appFeature : appSetings.getApp().getAppFeatures()) {
				if (Variability.AUTHENTICITY.getId().equals(appFeature.getId().getFeature().getId())) {
					hasAuth = appFeature.isActive();
					break;
				}
			}
			if (hasAuth) {
				SignInActivity_.intent(this).start();
			} else {
				DashboardActivity_.intent(this).start();
			}
			this.finish();
		} catch (Exception e) {
			this.showDialogAlert("Unable to configure App.", null);
		}

	}
}
