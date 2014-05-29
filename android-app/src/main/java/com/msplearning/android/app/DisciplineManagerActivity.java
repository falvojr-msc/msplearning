package com.msplearning.android.app;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.widget.EditText;

import com.msplearning.android.app.generic.GenericAsyncAuthActivity;
import com.msplearning.android.app.rest.DisciplineRestClient;
import com.msplearning.entity.Discipline;

/**
 * The DisciplineManagerActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_discipline_manager)
public class DisciplineManagerActivity extends GenericAsyncAuthActivity<MSPLearningApplication> {

	@ViewById(R.id.discipline_name)
	protected EditText mName;
	@ViewById(R.id.discipline_description)
	protected EditText mDescription;

	@RestService
	protected DisciplineRestClient mDisciplineRestClient;

	private Discipline currentDiscipline;

	@AfterViews
	public void afterViews() {
		this.currentDiscipline = (Discipline) this.getIntent().getSerializableExtra(DisciplineListActivity.EXTRA_KEY_DISCIPLINE);
		if(this.currentDiscipline == null) {
			this.currentDiscipline = new Discipline();
		} else {
			this.mName.setText(this.currentDiscipline.getName());
			this.mDescription.setText(this.currentDiscipline.getDescription());
		}
		this.getIntent().removeExtra(DisciplineListActivity.EXTRA_KEY_DISCIPLINE);
	}

	@Click(R.id.button_save)
	public void onDisciplineSave() {
		super.showLoadingProgressDialog();

		this.currentDiscipline.setName(this.mName.getText().toString());
		this.currentDiscipline.setDescription(this.mDescription.getText().toString());
		this.currentDiscipline.setIdApp(super.getApplicationContext().getAppSettings().getApp().getId());
		this.currentDiscipline.setIdCreator(super.getApplicationContext().getAppSettings().getUser().getId());

		this.saveDiscipline();
	}

	@Background
	public void saveDiscipline() {
		try {
			if (this.currentDiscipline.getId() == null) {
				this.mDisciplineRestClient.insert(this.currentDiscipline);
			} else {
				this.mDisciplineRestClient.update(this.currentDiscipline);
			}
			this.currentDiscipline = null;
		} catch (Exception exception) {
			super.showDialogAlert(exception.getMessage(), null);
		} finally {
			super.dismissProgressDialog();
		}
		this.setResult(RESULT_OK);
		this.finish();
	}
}
