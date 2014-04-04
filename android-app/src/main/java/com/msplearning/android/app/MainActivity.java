package com.msplearning.android.app;

import java.io.IOException;
import java.util.Properties;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.rest.RestService;

import com.msplearning.android.app.generic.GenericAsyncActivity;
import com.msplearning.android.rest.AppRestClient;
import com.msplearning.entity.App;

/**
 * The MainActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity
public class MainActivity extends GenericAsyncActivity<MSPLearningApplication> {

	@RestService
	protected AppRestClient mAppRestClient;

	/**
	 * TODO: In implementation...
	 */
	@AfterInject
	protected void init() {
		try {
		    Properties properties = new Properties();
		    properties.load(this.getResources().getAssets().open("product.properties"));
		    Long idApp = Long.parseLong(properties.getProperty("msplearning.app.id", "0"));

		    this.findApp(idApp);

		} catch (IOException e) {

		}
	}

	/**
	 * TODO: In implementation...
	 */
	@Background
	protected void findApp(Long idApp) {
		try {
			App app = this.mAppRestClient.findById(idApp).getEntity();
			super.showDialogAlert(app.getName(), null);
		} catch (Exception e) {
			super.showDialogAlert(e.getMessage(), null);
		}

	}
}
