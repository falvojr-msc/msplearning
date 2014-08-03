package com.msplearning.android.app;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.RestClientException;

import com.msplearning.android.app.generic.GenericAsyncActivity;
import com.msplearning.android.app.rest.AppRestClient;
import com.msplearning.entity.AppUserId;

/**
 * The MainActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity
public class MainActivity extends GenericAsyncActivity<MSPLearningApplication> {

	@RestService
	AppRestClient mAppRestClient;

	@AfterInject
	void afterInject() {
		try {
			// Load "product.properties", file where the id of the application in question is stored.
			Properties properties = new Properties();
			properties.load(this.getResources().getAssets().open("product.properties"));
			Long idApp = Long.parseLong(properties.getProperty("msplearning.app.id", BigInteger.ZERO.toString()));

			this.resolveAuthenticityVariability(idApp);
		} catch (IOException e) {
			this.showDialogAlert(this.getString(R.string.error_find_id_app), null);
		}
	}

	/**
	 * XXX Variation Point: Authenticity.
	 *
	 * @param idApp
	 */
	@Background
	void resolveAuthenticityVariability(Long idApp) {
		try {
			AppUserId appSetings = new AppUserId();
			appSetings.setApp(this.mAppRestClient.findById(idApp));
			this.getApplicationContext().setAppSettings(appSetings);
			SignInActivity_.intent(this).start();
			this.finish();
		} catch (RestClientException restClientException) {
			this.showDialogAlert(this.getString(R.string.rest_error_find_app), null);
		}

	}
}
