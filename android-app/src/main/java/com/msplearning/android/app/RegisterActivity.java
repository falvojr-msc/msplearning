package com.msplearning.android.app;

import java.util.Date;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.google.gson.Gson;
import com.msplearning.android.app.base.BaseActivityWithRestSupport;
import com.msplearning.android.compatibility.interoperability.StudentRESTfulClient;
import com.msplearning.android.compatibility.interoperability.TeacherRESTfulClient;
import com.msplearning.entity.Gender;
import com.msplearning.entity.Student;
import com.msplearning.entity.Teacher;
import com.msplearning.entity.User;
import com.msplearning.entity.json.GsonFactory;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivityWithRestSupport {

	@ViewById(R.id.firstName)
	protected EditText mFirstNameView;
	@ViewById(R.id.lastName)
	protected EditText mLastNameView;
	@ViewById(R.id.gender)
	protected RadioGroup mGenderView;
	@ViewById(R.id.username)
	protected EditText mUsernameView;
	@ViewById(R.id.password)
	protected EditText mPasswordView;
	@ViewById(R.id.repeatPassword)
	protected EditText mRepeatPasswordView;
	@ViewById(R.id.type)
	protected RadioGroup mTypeView;
	
	@RestService
	protected StudentRESTfulClient mStudentRESTfulClient;
	@RestService
	protected TeacherRESTfulClient mTeacherRESTfulClient;
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater();
        return true;
    }
	
	@AfterViews
	public void init() {
		String username = this.getIntent().getStringExtra(LoginActivity.KEY_USERNAME);
		if (username !=  null) {
			this.mUsernameView.setText(username);
			this.getIntent().removeExtra(LoginActivity.KEY_USERNAME);
		}
		
		String password = this.getIntent().getStringExtra(LoginActivity.KEY_PASSWORD);
		if (password !=  null) {
			this.mPasswordView.setText(password);
			this.getIntent().removeExtra(LoginActivity.KEY_PASSWORD);
		}
	}
	
	@Click(R.id.register_button)
	public void register() {
		User user = new User();
        user.setFirstName(this.mFirstNameView.getText().toString());
        user.setLastName(this.mLastNameView.getText().toString());
        user.setGender(this.mGenderView.indexOfChild(findViewById(this.mGenderView.getCheckedRadioButtonId())) == 0 ? Gender.M : Gender.F);
        user.setUsername(this.mUsernameView.getText().toString());
        user.setPassword(this.mPasswordView.getText().toString());
        
        insertUser(user);
	}
	
	@Background
	public void insertUser(User user) {
        Gson gson = GsonFactory.createGson();
        
        if (this.mTypeView.indexOfChild(findViewById(this.mTypeView.getCheckedRadioButtonId())) == 0)
        	this.mStudentRESTfulClient.insert(gson.fromJson(gson.toJson(user), Student.class));
		else
			this.mTeacherRESTfulClient.insert(gson.fromJson(gson.toJson(user), Teacher.class));
        
        showUiMessage("User created!");
	}
	
	@UiThread
	void showUiMessage(String message) {
		Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG).show();
	}
}
