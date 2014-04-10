package com.msplearning.android.app;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.msplearning.android.app.generic.GenericAsyncActivity;
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
public class RegisterActivity extends GenericAsyncActivity<MSPLearningApplication> {

	@ViewById(R.id.first_name)
	protected EditText mFirstNameView;
	@ViewById(R.id.last_name)
	protected EditText mLastNameView;
	@ViewById(R.id.radio_group_gender)
	protected RadioGroup mGenderView;
	@ViewById(R.id.username)
	protected EditText mUsernameView;
	@ViewById(R.id.password)
	protected EditText mPasswordView;
	@ViewById(R.id.repeat_password)
	protected EditText mRepeatPasswordView;
	@ViewById(R.id.radio_group_type)
	protected RadioGroup mTypeView;

	@RestService
	protected StudentRestClient mStudentRESTfulClient;
	@RestService
	protected TeacherRestClient mTeacherRESTfulClient;

	@AfterViews
	public void init() {
		String username = this.getIntent().getStringExtra(SignInActivity.EXTRA_KEY_USERNAME);
		if (username != null) {
			this.mUsernameView.setText(username);
		}

		String password = this.getIntent().getStringExtra(SignInActivity.EXTRA_KEY_PASSWORD);
		if (password != null) {
			this.mPasswordView.setText(password);
		}

		// Remove used Intent's extras
		this.getIntent().removeExtra(SignInActivity.EXTRA_KEY_USERNAME);
		this.getIntent().removeExtra(SignInActivity.EXTRA_KEY_PASSWORD);
	}

	@Click(R.id.button_register)
	public void onUserRegister() {
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
				user = this.mStudentRESTfulClient.insert(gson.fromJson(gson.toJson(user), Student.class)).getEntity();
			} else {
				user = this.mTeacherRESTfulClient.insert(gson.fromJson(gson.toJson(user), Teacher.class)).getEntity();
			}
		} catch (Exception exception) {
			this.showDialogAlert(exception.getMessage(), null);
		} finally {
			super.dismissProgressDialog();
		}
		super.getApplicationContext().getAppSettings().setUser(user);
		this.setResult(RESULT_OK);
		this.finish();
	}
}
