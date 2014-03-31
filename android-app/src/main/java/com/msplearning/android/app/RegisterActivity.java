package com.msplearning.android.app;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.app.AlertDialog;
import android.content.Intent;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.msplearning.android.app.generic.GenericAsyncRestActivity;
import com.msplearning.android.rest.StudentRestClient;
import com.msplearning.android.rest.TeacherRestClient;
import com.msplearning.entity.Gender;
import com.msplearning.entity.Student;
import com.msplearning.entity.Teacher;
import com.msplearning.entity.User;
import com.msplearning.entity.common.json.GsonFactory;

/**
 * The RegisterActivity class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_register)
public class RegisterActivity extends GenericAsyncRestActivity<MSPLearningApplication> {

	@ViewById(R.id.txtFirstName)
	protected EditText mFirstNameView;
	@ViewById(R.id.txtLastName)
	protected EditText mLastNameView;
	@ViewById(R.id.rdoGrpGender)
	protected RadioGroup mGenderView;
	@ViewById(R.id.txtUsername)
	protected EditText mUsernameView;
	@ViewById(R.id.txtPassword)
	protected EditText mPasswordView;
	@ViewById(R.id.txtRepeatPassword)
	protected EditText mRepeatPasswordView;
	@ViewById(R.id.rdoGrpType)
	protected RadioGroup mTypeView;

	@RestService
	protected StudentRestClient mStudentRESTfulClient;
	@RestService
	protected TeacherRestClient mTeacherRESTfulClient;

	@AfterViews
	public void init() {
		String username = this.getIntent().getStringExtra(SignInActivity.KEY_USERNAME);
		if (username != null) {
			this.mUsernameView.setText(username);
			this.getIntent().removeExtra(SignInActivity.KEY_USERNAME);
		}

		String password = this.getIntent().getStringExtra(SignInActivity.KEY_PASSWORD);
		if (password != null) {
			this.mPasswordView.setText(password);
			this.getIntent().removeExtra(SignInActivity.KEY_PASSWORD);
		}
	}

	@Click
	public void btnRegister() {
		super.showLoadingProgressDialog();

		User user = new User();
		user.setFirstName(this.mFirstNameView.getText().toString());
		user.setLastName(this.mLastNameView.getText().toString());
		user.setGender(this.mGenderView.indexOfChild(this.findViewById(this.mGenderView.getCheckedRadioButtonId())) == 0 ? Gender.M : Gender.F);
		user.setUsername(this.mUsernameView.getText().toString());
		user.setPassword(this.mPasswordView.getText().toString());

		this.insertUser(user);
	}

	@Background
	public void insertUser(User user) {
		Gson gson = GsonFactory.createGson();
		try {
			if (this.mTypeView.indexOfChild(this.findViewById(this.mTypeView.getCheckedRadioButtonId())) == 0) {
				this.mStudentRESTfulClient.insert(gson.fromJson(gson.toJson(user), Student.class));
			} else {
				this.mTeacherRESTfulClient.insert(gson.fromJson(gson.toJson(user), Teacher.class));
			}
		} catch (Exception exception) {
			this.showDialogAlertError(exception);
		} finally {
			super.dismissProgressDialog();
		}
		Intent intent = DashboardActivity_.intent(this.getApplicationContext()).get();
		this.startActivity(intent);
		this.finish();
	}

	@UiThread
	protected void showDialogAlertError(Exception exception) {
		new AlertDialog.Builder(this).setTitle(this.getString(R.string.title_dialog_error)).setMessage(exception.getMessage())
		.setIcon(android.R.drawable.ic_dialog_alert).setNeutralButton(android.R.string.ok, null).show();
	}
}
