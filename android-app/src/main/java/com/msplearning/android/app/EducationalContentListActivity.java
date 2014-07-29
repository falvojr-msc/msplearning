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
import com.msplearning.android.app.rest.EducationalContentRestClient;
import com.msplearning.android.app.widget.EducationalContentItemView;
import com.msplearning.android.app.widget.EducationalContentListAdapter;
import com.msplearning.android.app.widget.generic.AbstractListAdapter;
import com.msplearning.entity.EducationalContent;

/**
 * The EducationalContentListActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_educational_content_list)
@OptionsMenu(R.menu.actionbar)
public class EducationalContentListActivity extends GenericActivityListView<EducationalContent> {

	/**
	 * EXTRA_KEY_ID_DISCIPLINE Intent extra key.
	 */
	public static final String EXTRA_KEY_ID_LESSON = "E.lesson.id";

	private static final String EDUCATIONAL_CONTENT = "Educational Content";

	@ViewById(R.id.list_view_educational_contents)
	protected ListView mListView;

	@RestService
	protected EducationalContentRestClient mEducationalContentRestClient;

	@Bean
	protected EducationalContentListAdapter mEducationalContentListAdapter;

	private Long idSelectedLesson;

	@AfterInject
	public void afterInject() {
		if (this.idSelectedLesson == null) {
			this.idSelectedLesson = this.getIntent().getLongExtra(EXTRA_KEY_ID_LESSON, 0L);
			this.getIntent().removeExtra(EXTRA_KEY_ID_LESSON);
		}
	}

	@Override
	protected ListView getListView() {
		return this.mListView;
	}

	@Override
	protected AbstractListAdapter<EducationalContent, EducationalContentItemView> getListAdapter() {
		return this.mEducationalContentListAdapter;
	}

	@Override
	protected List<EducationalContent> findListItens() {
		return Arrays.asList(this.mEducationalContentRestClient.findByLesson(this.idSelectedLesson));
	}

	@OptionsItem(R.id.action_refresh)
	protected void onRefresh() {
		this.loadListItens();
	}

	@OptionsItem(R.id.action_new)
	protected void onNew() {
		Intent intent = EducationalContentManagerActivity_.intent(this).get();
		intent.putExtra(EducationalContentManagerActivity.EXTRA_KEY_ID_LESSON, this.idSelectedLesson);
		intent.putExtra(EducationalContentManagerActivity.EXTRA_KEY_COUNT_EDUCATIONAL_CONTENT, this.mEducationalContentListAdapter.getCount());
		this.startActivityForResult(intent, REQUEST_CODE_CREATE);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		menu.setHeaderTitle(this.getString(R.string.context_menu_title));
		super.onCreateContextMenu(menu, view, menuInfo);
		super.getMenuInflater().inflate(R.menu.contextual_educational_content_list, menu);
	}

	@Override
	protected void onContextItemSelected(MenuItem item, final EducationalContent selectedItem) {
		switch (item.getItemId()) {
		case R.id.action_edit:
			Intent intent = EducationalContentManagerActivity_.intent(this).get();
			intent.putExtra(EducationalContentManagerActivity.EXTRA_KEY_EDUCATIONAL_CONTENT, selectedItem);
			intent.putExtra(EducationalContentManagerActivity.EXTRA_KEY_COUNT_EDUCATIONAL_CONTENT, this.mEducationalContentListAdapter.getCount());
			this.startActivityForResult(intent, REQUEST_CODE_UPDATE);
			break;
		case R.id.action_discard:
			OnClickListener listenerYes = new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					EducationalContentListActivity.this.deleteEducationalContent(selectedItem.getId());
				}
			};
			this.showDialogConfirm(this.getString(R.string.dialog_title_discard), this.getString(R.string.dialog_message_discard, EDUCATIONAL_CONTENT.toLowerCase(Locale.getDefault())), listenerYes, null);
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
	protected void deleteEducationalContent(Long id) {
		this.mEducationalContentRestClient.delete(id);
		this.reloadItensShowingToastMessage(super.getString(R.string.toast_discard_success, EDUCATIONAL_CONTENT));
	}

	private void showMessageFromResult(int resultCode, int idResource) {
		if (resultCode == RESULT_OK) {
			super.reloadItensShowingToastMessage(super.getString(idResource, EDUCATIONAL_CONTENT));
		}
	}
}
