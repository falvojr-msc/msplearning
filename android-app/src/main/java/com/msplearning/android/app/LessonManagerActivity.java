package com.msplearning.android.app;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.widget.EditText;

import com.msplearning.android.app.generic.GenericAsyncAuthActivity;
import com.msplearning.android.app.rest.LessonRestClient;
import com.msplearning.entity.Lesson;

/**
 * The DisciplineManagerActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_lesson_manager)
public class LessonManagerActivity extends GenericAsyncAuthActivity<MSPLearningApplication> {

	@ViewById(R.id.discipline_name)
	protected EditText mName;

	@RestService
	protected LessonRestClient mLessonRestClient;

	private Lesson currentLesson;

	@AfterViews
	public void afterViews() {
		this.currentLesson = (Lesson) this.getIntent().getSerializableExtra(LessonListActivity.EXTRA_KEY_LESSON);
		if(this.currentLesson == null) {
			Long idDiscipline = this.getIntent().getLongExtra(DisciplineListActivity.EXTRA_KEY_ID_DISCIPLINE, 0L);
			this.currentLesson = new Lesson();
			this.currentLesson.setIdDiscipline(idDiscipline);
		} else {
			this.mName.setText(this.currentLesson.getName());
		}
		this.getIntent().removeExtra(LessonListActivity.EXTRA_KEY_LESSON);
		this.getIntent().removeExtra(DisciplineListActivity.EXTRA_KEY_ID_DISCIPLINE);
	}

	@Click(R.id.button_save)
	public void onDisciplineSave() {
		super.showLoadingProgressDialog();

		this.currentLesson.setName(this.mName.getText().toString());

		this.saveDiscipline();
	}

	@Background
	public void saveDiscipline() {
		try {
			if (this.currentLesson.getId() == null) {
				this.mLessonRestClient.insert(this.currentLesson);
			} else {
				this.mLessonRestClient.update(this.currentLesson);
			}
			this.currentLesson = null;
		} catch (Exception exception) {
			super.showDialogAlert(exception.getMessage(), null);
		} finally {
			super.dismissProgressDialog();
		}
		this.setResult(RESULT_OK);
		this.finish();
	}
}
