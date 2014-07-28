package com.msplearning.android.app;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.androidannotations.annotations.AfterInject;
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

	// Intent extra keys:
	public static final String EXTRA_KEY_ID_DISCIPLINE = "E.discipline.id";

	private static final String LESSON = "Lesson";

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
	protected AbstractListAdapter<Lesson, LessonItemView> getListAdapter() {
		return this.mLessonAdapter;
	}

	@Override
	protected List<Lesson> findListItens() {
		return Arrays.asList(this.mLessonRestClient.findByDiscipline(this.idSelectedDiscipline));
	}

	@AfterInject
	public void afterInject() {
		this.idSelectedDiscipline = this.getIntent().getLongExtra(EXTRA_KEY_ID_DISCIPLINE, 0L);
		this.getIntent().removeExtra(EXTRA_KEY_ID_DISCIPLINE);
	}

	@OptionsItem(R.id.action_refresh)
	protected void onRefresh() {
		this.loadListItens();
	}

	@OptionsItem(R.id.action_new)
	protected void onNew() {
		Intent intent = LessonManagerActivity_.intent(this).get();
		intent.putExtra(LessonManagerActivity.EXTRA_KEY_ID_DISCIPLINE, this.idSelectedDiscipline);
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
		switch(item.getItemId()){
		case R.id.action_manage_slides:
			// TODO Implement manage educational content
			break;
		case R.id.action_edit:
			Intent intentManageLesson = LessonManagerActivity_.intent(this).get();
			intentManageLesson.putExtra(LessonManagerActivity.EXTRA_KEY_LESSON, selectedItem);
			this.startActivityForResult(intentManageLesson, REQUEST_CODE_UPDATE);
			break;
		case R.id.action_discard:
			OnClickListener listenerYes = new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					LessonListActivity.this.deleteLesson(selectedItem.getId());
				}
			};
			this.showDialogConfirm( this.getString(R.string.dialog_title_discard), this.getString(R.string.dialog_message_discard, LESSON.toLowerCase(Locale.getDefault())), listenerYes, null);
			break;
		}
	}

	@OnActivityResult(REQUEST_CODE_CREATE)
	protected void onResultNew(int resultCode) {
		this.showMessageFromResult(resultCode, R.string.toast_new_success);
	}

	@OnActivityResult(REQUEST_CODE_UPDATE)
	protected void onResultEdit(int resultCode) {
		this.showMessageFromResult(resultCode, R.string.toast_edit_success);
	}

	@Background
	protected void deleteLesson(Long id) {
		this.mLessonRestClient.delete(id);
		this.reloadItensShowingToastMessage(super.getString(R.string.toast_discard_success, LESSON));
	}

	private void showMessageFromResult(int resultCode, int idResource) {
		if (resultCode == RESULT_OK) {
			super.reloadItensShowingToastMessage(super.getString(idResource, LESSON));
		}
	}
}
