package com.msplearning.android.app;

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

	protected static final int REQUEST_CODE_NEW_LESSON = 0;
	protected static final int REQUEST_CODE_EDIT_LESSON = 1;

	private static final String LESSON = "Lesson";

	public static final String EXTRA_KEY_LESSON = "activity.lesson";
	public static final String EXTRA_KEY_ID_LESSON = "activity.lesson.id";

	@ViewById(R.id.list_view_lessons)
	protected ListView mListView;

	@Bean
	protected LessonListAdapter mLessonAdapter;

	@RestService
	protected LessonRestClient mLessonRestClient;

	private Long idSelectedDiscipline;

	@Override
	protected ListView getListView() {
		return this.mListView;
	}

	@Override
	protected AbstractListAdapter<Lesson, ?> getListAdapter() {
		return this.mLessonAdapter;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		menu.setHeaderTitle(this.getString(R.string.context_menu_title));
		super.onCreateContextMenu(menu, view, menuInfo);
		super.getMenuInflater().inflate(R.menu.contextual_lesson_list, menu);
	}

	@Override
	@AfterViews
	public void afterViews() {
		this.idSelectedDiscipline = this.getIntent().getLongExtra(DisciplineListActivity.EXTRA_KEY_ID_DISCIPLINE, 0L);
		super.afterViews();
		this.getIntent().removeExtra(DisciplineListActivity.EXTRA_KEY_DISCIPLINE);
	}

	@OptionsItem(R.id.action_refresh)
	protected void onRefresh() {
		this.loadListItens();
	}

	@OptionsItem(R.id.action_new)
	protected void onNew() {
		Intent intent = LessonManagerActivity_.intent(this).get();
		intent.putExtra(DisciplineListActivity.EXTRA_KEY_ID_DISCIPLINE, this.idSelectedDiscipline);
		this.startActivityForResult(intent, REQUEST_CODE_NEW_LESSON);
	}

	@OnActivityResult(REQUEST_CODE_NEW_LESSON)
	protected void onResultNew(int resultCode) {
		this.showMessageFromResult(resultCode, R.string.toast_new_success);
	}

	@Override
	protected void onContextItemSelected(MenuItem item, final Lesson selectedItem) {
		switch(item.getItemId()){
		case R.id.action_manage_slides:
			Intent intentSlides = SlideListActivity_.intent(this).get();
			intentSlides.putExtra(EXTRA_KEY_ID_LESSON, selectedItem.getId());
			this.startActivity(intentSlides);
			break;
		case R.id.action_edit:
			Intent intentManageLesson = LessonManagerActivity_.intent(this).get();
			intentManageLesson.putExtra(EXTRA_KEY_LESSON, selectedItem);
			this.startActivityForResult(intentManageLesson, REQUEST_CODE_EDIT_LESSON);
			break;
		case R.id.action_discard:
			OnClickListener listenerYes = new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					LessonListActivity.this.asyncDiscardLesson(selectedItem.getId());
				}
			};
			this.showDialogConfirm( this.getString(R.string.dialog_title_discard), this.getString(R.string.dialog_message_discard, LESSON.toLowerCase(Locale.getDefault())), listenerYes, null);
			break;
		}
	}

	@OnActivityResult(REQUEST_CODE_EDIT_LESSON)
	protected void onResultEdit(int resultCode) {
		this.showMessageFromResult(resultCode, R.string.toast_edit_success);
	}

	private void showMessageFromResult(int resultCode, int idResource) {
		if (resultCode == RESULT_OK) {
			super.reloadItensShowingToastMessage(super.getString(idResource, LESSON));
		} else if (resultCode == RESULT_CANCELED) {
			this.showDialogAlert("Unexpected error", null);
		}
	}

	@Background
	protected void asyncDiscardLesson(Long id) {
		this.mLessonRestClient.delete(id);
		this.reloadItensShowingToastMessage(super.getString(R.string.toast_discard_success, LESSON));
	}

	@Override
	protected List<Lesson> findListItens() {
		return this.mLessonRestClient.findByDiscipline(this.idSelectedDiscipline).getEntity();
	}
}
