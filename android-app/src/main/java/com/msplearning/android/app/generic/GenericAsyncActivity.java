package com.msplearning.android.app.generic;

import java.lang.reflect.Field;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.api.rest.RestClientSupport;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;

import com.msplearning.android.app.MSPLearningApplication;
import com.msplearning.android.app.R;
import com.msplearning.android.ext.generic.AbstractAsyncActivity;

/**
 * The GenericAsyncRestActivity class extends {@link AbstractAsyncActivity}. Technical adaptation of the bug (java.io.EOFException) already known by Spring and Google
 * HTTP Client. Set the @UiThread on dismissProgressDialog method.
 *
 * @see <a href="http://sapandiwakar.in/eofexception-with-spring-rest-template-android/">Sapan Diwakar</a>
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity
public abstract class GenericAsyncActivity<T extends MSPLearningApplication> extends AbstractAsyncActivity<T> {

	protected static final String TAG = GenericAsyncActivity.class.getSimpleName();

	protected static final int REQUEST_CODE_CREATE = 0;
	protected static final int REQUEST_CODE_UPDATE = 1;

	@UiThread
	@Override
	public void dismissProgressDialog() {
		super.dismissProgressDialog();
	}

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

	@UiThread
	protected void showDialogAlert(String message, OnClickListener listenerOk) {
		new AlertDialog.Builder(this)
		.setTitle(this.getString(R.string.dialog_title_error))
		.setMessage(message).setIcon(android.R.drawable.ic_dialog_alert)
		.setNeutralButton(android.R.string.ok, listenerOk)
		.show();
	}

	@UiThread
	protected void showDialogConfirm(String title, String message, OnClickListener listenerYes, OnClickListener listenerNo) {
		new AlertDialog.Builder(this)
		.setTitle(title)
		.setMessage(message)
		.setIcon(android.R.drawable.ic_dialog_info)
		.setPositiveButton(android.R.string.yes, listenerYes)
		.setNegativeButton(android.R.string.no, listenerNo)
		.show();
	}
}
