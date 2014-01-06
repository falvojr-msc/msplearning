package com.msplearning.android.app;

import java.util.Date;

import android.widget.EditText;
import android.widget.RadioGroup;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.google.gson.Gson;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.msplearning.android.compatibility.interoperability.StudentRESTfulClient;
import com.msplearning.android.compatibility.interoperability.TeacherRESTfulClient;
import com.msplearning.entity.Gender;
import com.msplearning.entity.Student;
import com.msplearning.entity.Teacher;
import com.msplearning.entity.User;
import com.msplearning.entity.json.GsonFactory;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends SherlockActivity {

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
	
	@Click(R.id.register_button)
	@Background
	public void register() {
		User user = new User();
        user.setFirstName(this.mFirstNameView.getText().toString());
        user.setLastName(this.mLastNameView.getText().toString());
        user.setGender(this.mGenderView.indexOfChild(findViewById(this.mGenderView.getCheckedRadioButtonId())) == 0 ? Gender.M : Gender.F);
        user.setUsername(this.mUsernameView.getText().toString());
        user.setPassword(this.mPasswordView.getText().toString());
        user.setDateRegistration(new Date());
        user.setDateLastLogin(new Date());
        
        Gson gson = GsonFactory.createGson();
        String jsonUser = gson.toJson(user);
        
        if (this.mTypeView.indexOfChild(findViewById(this.mTypeView.getCheckedRadioButtonId())) == 0) {
        	mStudentRESTfulClient.insert(gson.fromJson(jsonUser, Student.class));
		} else {
			mTeacherRESTfulClient.insert(gson.fromJson(jsonUser, Teacher.class));
		}
	}
}
