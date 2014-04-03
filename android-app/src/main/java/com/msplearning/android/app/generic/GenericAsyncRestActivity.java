package com.msplearning.android.app.generic;

import java.lang.reflect.Field;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.api.rest.RestClientSupport;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import android.app.Application;

import com.msplearning.android.app.ext.generic.AbstractAsyncActivity;

/**
 * The GenericAsyncRestActivity class extends {@link AbstractAsyncActivity}. Technical adaptation of the bug (java.io.EOFException) already known by Spring and Google
 * HTTP Client.
 *
 * @see <a href="http://sapandiwakar.in/eofexception-with-spring-rest-template-android/">Sapan Diwakar</a>
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity
public abstract class GenericAsyncRestActivity<T extends Application> extends GenericAsyncActivity<T> {

	protected static final String TAG = GenericAsyncRestActivity.class.getSimpleName();

	/**
	 * Method with loop through each property of type RestClientSupport, changing its RequestFactory for {@link HttpComponentsClientHttpRequestFactory}.
	 */
	@AfterInject
	protected void changeRequestFactoryRestTemplate() {
		Field[] props = this.getClass().getSuperclass().getDeclaredFields();
		for (Field propIt : props) {
			Object prop;
			try {
				propIt.setAccessible(true);
				prop = propIt.get(this);
				if (prop instanceof RestClientSupport) {
					((RestClientSupport) prop).getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
