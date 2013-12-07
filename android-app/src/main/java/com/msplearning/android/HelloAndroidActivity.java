package com.msplearning.android;

import java.util.Date;

import android.widget.Button;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.msplearning.android.compatibility.interoperability.StudentRESTfulClient;
import com.msplearning.entity.Gender;
import com.msplearning.entity.Student;

/**
 * The HelloAndroidActivity class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_tests)
public class HelloAndroidActivity extends SherlockActivity {

	private Long idStudent;
	
	@ViewById(R.id.buttonInsert)
	Button btnInsert;
	@ViewById(R.id.buttonUpdate)
	Button btnUdate;	
	@ViewById(R.id.buttonDelete)
	Button btnDelete;
	
	@RestService
	StudentRESTfulClient restClient;

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater();
        return true;
    }

	@Click(R.id.buttonInsert)
	void onInsertClick() {
		Student student = new Student();
        student.setDateBirth(new Date());
        student.setDateLastLogin(new Date());
        student.setDateRegistration(new Date());
        student.setFirstName("Venilton");
        student.setGender(Gender.M);
        student.setLastName("Falvo Jr.");
        student.setPassword("android");
        student.setUsername("veniltonjr");
		
		asyncInsert(student);
	}
	
	@Click(R.id.buttonUpdate)
	void onUpdateClick() {	
		asyncUpdate();
	}
	
    @Click(R.id.buttonDelete)
	void onDeleteClick() {	
		asyncDelete();
	}
	
    @Background
    void asyncInsert(Student student) {
    	this.idStudent = this.restClient.insert(student);
        showUiMessage("Student created!");
    }

    @Background
    void asyncUpdate() {
		Student student = this.restClient.findById(this.idStudent);
		student.setFirstName("Alter...");
		
    	this.restClient.update(student); 	
        showUiMessage("Student updated!");
    }
    
    @Background
    void asyncDelete() {
		this.restClient.delete(this.idStudent);
        showUiMessage("Student deleted!");
    }
    
    @UiThread
    void showUiMessage(String message) {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}
