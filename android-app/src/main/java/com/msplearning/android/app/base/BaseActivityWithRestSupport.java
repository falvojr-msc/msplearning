package com.msplearning.android.app.base;

import java.lang.reflect.Field;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.api.rest.RestClientSupport;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * The BaseActivityWithRestSupport class extends {@link BaseActivity}. Technical
 * adaptation of the bug already known by Spring (java.io.EOFException)
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EBean
public class BaseActivityWithRestSupport extends BaseActivity {

	@AfterInject
	protected void changeRequestFactoryRestTemplate() {
		Field[] props = this.getClass().getSuperclass().getDeclaredFields();
		for (Field prop2 : props) {
			Object prop;
			try {
				prop2.setAccessible(true);
				prop = prop2.get(this);
				if (prop instanceof RestClientSupport) {
					((RestClientSupport) prop).getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
