package com.msplearning.android.app;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.widget.EditText;

import com.msplearning.android.app.generic.GenericAsyncActivity;
import com.msplearning.android.rest.DisciplineRestClient;
import com.msplearning.entity.Discipline;

/**
 * The DisciplineManagerActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_discipline_manager)
public class DisciplineManagerActivity extends GenericAsyncActivity<MSPLearningApplication> {

	@ViewById(R.id.discipline_name)
	protected EditText mName;
	@ViewById(R.id.discipline_description)
	protected EditText mDescription;

	@RestService
	protected DisciplineRestClient mDisciplineRestClient;

	private Discipline currentDiscipline;

	@AfterViews
	public void afterViews() {
		// Load current discipline (if functionality is editing)
		this.currentDiscipline = (Discipline) this.getIntent().getSerializableExtra(DisciplineListActivity.EXTRA_KEY_DISCIPLINE);
		// Load data if functionality is editing
		if(this.currentDiscipline != null) {
			this.mName.setText(this.currentDiscipline.getName());
			this.mDescription.setText(this.currentDiscipline.getDescription());
		}
		// Remove used Intent's extras
		this.getIntent().removeExtra(DisciplineListActivity.EXTRA_KEY_DISCIPLINE);
	}

	@Click(R.id.button_save)
	public void onDisciplineSave() {
		super.showLoadingProgressDialog();

		Discipline discipline = new Discipline();
		discipline.setName(this.mName.getText().toString());
		discipline.setDescription(this.mDescription.getText().toString());

		this.saveDiscipline(discipline);
	}

	@Background
	public void saveDiscipline(Discipline discipline) {
		try {
			if (this.currentDiscipline == null) {
				this.mDisciplineRestClient.insert(discipline);
			} else {
				this.currentDiscipline.setName(discipline.getName());
				this.currentDiscipline.setDescription(discipline.getDescription());
				this.mDisciplineRestClient.update(this.currentDiscipline);
				this.currentDiscipline = null;
			}
		} catch (Exception exception) {
			super.showDialogAlert(exception.getMessage(), null);
		} finally {
			super.dismissProgressDialog();
		}
		this.setResult(RESULT_OK);
		this.finish();
	}
}
