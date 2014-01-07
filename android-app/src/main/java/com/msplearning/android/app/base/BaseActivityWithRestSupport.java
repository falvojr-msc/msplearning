package com.msplearning.android.app.base;

import java.lang.reflect.Field;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.api.rest.RestClientSupport;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import com.actionbarsherlock.app.SherlockActivity;

@EBean
public class BaseActivityWithRestSupport extends SherlockActivity {

	@AfterInject
	protected void changeRequestFactoryRestTemplate() {
		Field[] props = this.getClass().getSuperclass().getDeclaredFields();
		for (int i = 0; i < props.length; i++) {
			Object prop;
			try {
				props[i].setAccessible(true);
				prop = props[i].get(this);
				if (prop instanceof RestClientSupport) {
					((RestClientSupport) prop).getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
				}
			} catch (Exception exception) { 
				exception.printStackTrace();
			}
		}
	}
}
