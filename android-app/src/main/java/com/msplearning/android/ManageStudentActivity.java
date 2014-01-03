package com.msplearning.android;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.msplearning.android.compatibility.interoperability.StudentRESTfulClient;
import com.msplearning.entity.Gender;
import com.msplearning.entity.Student;

/**
 * The ManageStudentActivity class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_manage_student)
public class ManageStudentActivity extends SherlockActivity {

	@ViewById(R.id.editTextFirstName)
	EditText editTextFirstName;
	
	@ViewById(R.id.editTextLastName)
	EditText editTextLastName;
	
	@ViewById(R.id.radioGroupGender)
	RadioGroup radioGroupGender;
	
	@ViewById(R.id.editTextDateBirth)
	EditText editTextDateBirth;
	
	@ViewById(R.id.editTextUsername)
	EditText editTextUsername;
	
	@ViewById(R.id.editTextPass)
	EditText editTextPass;
	
	@ViewById(R.id.buttonSave)
	Button buttonSave;
	
	@RestService
	StudentRESTfulClient restClient;
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater();
        return true;
    }

	@Click(R.id.buttonSave)
	void onSaveClick() {
		Student student = new Student();
        student.setFirstName(this.editTextFirstName.getText().toString());
        student.setLastName(this.editTextLastName.getText().toString());
        student.setGender(this.radioGroupGender.indexOfChild(findViewById(this.radioGroupGender.getCheckedRadioButtonId())) == 0 ? Gender.M : Gender.F);
        
        try {
        	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
			student.setDateBirth(dateFormat.parse(this.editTextDateBirth.getText().toString()));
		} catch (ParseException e) { }
        
        student.setDateRegistration(new Date());
        student.setUsername(this.editTextUsername.getText().toString());
        student.setPassword(this.editTextPass.getText().toString());
        
        this.restClient.insert(student);
        showUiMessage("Student created!");
	}
	
	@UiThread
    void showUiMessage(String message) {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
