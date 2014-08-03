package com.msplearning.android.app;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.msplearning.android.app.generic.GenericActivityListView;
import com.msplearning.android.app.rest.LessonRestClient;
import com.msplearning.android.app.widget.LessonItemView;
import com.msplearning.android.app.widget.LessonListAdapter;
import com.msplearning.android.app.widget.generic.AbstractListAdapter;
import com.msplearning.entity.Lesson;

/**
 * The LessonListActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_lesson_list)
@OptionsMenu(R.menu.actionbar)
public class LessonListActivity extends GenericActivityListView<Lesson> {

	/**
	 * EXTRA_KEY_ID_DISCIPLINE Intent extra key.
	 */
	public static final String EXTRA_KEY_ID_DISCIPLINE = "E.discipline.id";

	@ViewById(R.id.list_view_lessons)
	ListView mListView;

	@Bean
	LessonListAdapter mLessonAdapter;

	@RestService
	LessonRestClient mLessonRestClient;

	private Long idSelectedDiscipline;

	@SuppressLint("NewApi")
	@AfterViews
	@Override
	protected void afterViews() {
		if (this.idSelectedDiscipline == null) {
			this.idSelectedDiscipline = this.getIntent().getLongExtra(EXTRA_KEY_ID_DISCIPLINE, 0L);
			this.getIntent().removeExtra(EXTRA_KEY_ID_DISCIPLINE);
		}
		super.afterViews();
		super.getActionBar().setSubtitle(R.string.app_subtitle_lesson);
	}

	@Override
	protected ListView getListView() {
		return this.mListView;
	}

	@Override
	protected AbstractListAdapter<Lesson, LessonItemView> getListAdapter() {
		return this.mLessonAdapter;
	}

	@Override
	protected List<Lesson> findListItens() {
		return Arrays.asList(this.mLessonRestClient.findByDiscipline(this.idSelectedDiscipline));
	}

	@OptionsItem(R.id.action_refresh)
	void onRefresh() {
		this.loadListItens();
	}

	@OptionsItem(R.id.action_new)
	void onNew() {
		Intent intent = LessonActivity_.intent(this).get();
		intent.putExtra(LessonActivity.EXTRA_KEY_ID_DISCIPLINE, this.idSelectedDiscipline);
		this.startActivityForResult(intent, REQUEST_CODE_CREATE);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		menu.setHeaderTitle(this.getString(R.string.context_menu_title));
		super.onCreateContextMenu(menu, view, menuInfo);
		super.getMenuInflater().inflate(R.menu.contextual_lesson_list, menu);
	}

	@Override
	protected void onContextItemSelected(MenuItem item, final Lesson selectedItem) {
		switch (item.getItemId()) {
		case R.id.action_manage_educational_content:
			Intent intentEducationalContent = EducationalContentListActivity_.intent(this).get();
			intentEducationalContent.putExtra(EducationalContentListActivity.EXTRA_KEY_ID_LESSON, selectedItem.getId());
			this.startActivity(intentEducationalContent);
			break;
		case R.id.action_edit:
			Intent intent = LessonActivity_.intent(this).get();
			intent.putExtra(LessonActivity.EXTRA_KEY_LESSON, selectedItem);
			this.startActivityForResult(intent, REQUEST_CODE_UPDATE);
			break;
		case R.id.action_discard:
			OnClickListener listenerYes = new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					LessonListActivity.this.deleteLesson(selectedItem.getId());
				}
			};
			String label = super.getString(R.string.app_subtitle_lesson).toLowerCase(Locale.getDefault());
			this.showDialogConfirm(this.getString(R.string.dialog_title_confirmation), this.getString(R.string.dialog_message_discard, label), listenerYes, null);
			break;
		}
	}

	@OnActivityResult(REQUEST_CODE_CREATE)
	void onResultNew(int resultCode) {
		this.showMessageFromResult(resultCode, R.string.toast_new_success);
	}

	@OnActivityResult(REQUEST_CODE_UPDATE)
	void onResultEdit(int resultCode) {
		this.showMessageFromResult(resultCode, R.string.toast_edit_success);
	}

	@Background
	void deleteLesson(Long id) {
		this.mLessonRestClient.delete(id);
		this.reloadItensShowingToastMessage(super.getString(R.string.toast_discard_success, super.getString(R.string.app_subtitle_lesson)));
	}

	private void showMessageFromResult(int resultCode, int idResource) {
		if (resultCode == RESULT_OK) {
			super.reloadItensShowingToastMessage(super.getString(idResource, super.getString(R.string.app_subtitle_lesson)));
		}
	}
}
