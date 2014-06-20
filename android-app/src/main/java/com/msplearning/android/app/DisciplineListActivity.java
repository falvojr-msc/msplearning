package com.msplearning.android.app;

import java.util.List;
import java.util.Locale;

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
import com.msplearning.android.app.rest.DisciplineRestClient;
import com.msplearning.android.app.widget.DisciplineListAdapter;
import com.msplearning.android.app.widget.generic.AbstractListAdapter;
import com.msplearning.entity.Discipline;

/**
 * The DisciplineActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_discipline_list)
@OptionsMenu(R.menu.actionbar)
public class DisciplineListActivity extends GenericActivityListView<Discipline> {

	private static final int REQUEST_CODE_NEW_DISCIPLINE = 0;
	private static final int REQUEST_CODE_EDIT_DISCIPLINE = 1;

	private static final String DISCIPLINE = "Discipline";

	public static final String EXTRA_KEY_DISCIPLINE = "activity.discipline";
	public static final String EXTRA_KEY_ID_DISCIPLINE = "activity.discipline.id";

	@ViewById(R.id.list_view_disciplines)
	protected ListView mListView;

	@Bean
	protected DisciplineListAdapter mDisciplineAdapter;

	@RestService
	protected DisciplineRestClient mDisciplineRestClient;

	@Override
	protected ListView getListView() {
		return this.mListView;
	}

	@Override
	protected AbstractListAdapter<Discipline, ?> getListAdapter() {
		return this.mDisciplineAdapter;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		menu.setHeaderTitle(this.getString(R.string.context_menu_title));
		super.onCreateContextMenu(menu, view, menuInfo);
		super.getMenuInflater().inflate(R.menu.contextual_discipline_list , menu);
	}

	@OptionsItem(R.id.action_refresh)
	protected void onRefresh() {
		this.loadListItens();
	}

	@OptionsItem(R.id.action_new)
	protected void onNew() {
		Intent intent = DisciplineManagerActivity_.intent(this).get();
		this.startActivityForResult(intent, REQUEST_CODE_NEW_DISCIPLINE);
	}

	@OnActivityResult(REQUEST_CODE_NEW_DISCIPLINE)
	protected void onResultNew(int resultCode) {
		this.showMessageFromResult(resultCode, R.string.toast_new_success);
	}

	@Override
	protected void onContextItemSelected(MenuItem item, final Discipline selectedItem) {
		switch(item.getItemId()){
		case R.id.action_manage_lessons:
			Intent intentLessons = LessonListActivity_.intent(this).get();
			intentLessons.putExtra(EXTRA_KEY_ID_DISCIPLINE, selectedItem.getId());
			this.startActivity(intentLessons);
			break;
		case R.id.action_edit:
			Intent intentManageDiscipline = DisciplineManagerActivity_.intent(this).get();
			intentManageDiscipline.putExtra(EXTRA_KEY_DISCIPLINE, selectedItem);
			this.startActivityForResult(intentManageDiscipline, REQUEST_CODE_EDIT_DISCIPLINE);
			break;
		case R.id.action_discard:
			OnClickListener listenerYes = new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					DisciplineListActivity.this.asyncDiscardDiscipline(selectedItem.getId());
				}
			};
			this.showDialogConfirm( this.getString(R.string.dialog_title_discard), this.getString(R.string.dialog_message_discard, DISCIPLINE.toLowerCase(Locale.getDefault())), listenerYes, null);
			break;
		}
	}

	@OnActivityResult(REQUEST_CODE_EDIT_DISCIPLINE)
	protected void onResultEdit(int resultCode) {
		this.showMessageFromResult(resultCode, R.string.toast_edit_success);
	}

	private void showMessageFromResult(int resultCode, int idResource) {
		if (resultCode == RESULT_OK) {
			super.reloadItensShowingToastMessage(super.getString(idResource, DISCIPLINE));
		} else if (resultCode == RESULT_CANCELED) {
			this.showDialogAlert("Unexpected error", null);
		}
	}

	@Background
	protected void asyncDiscardDiscipline(Long id) {
		this.mDisciplineRestClient.delete(id);
		this.reloadItensShowingToastMessage(super.getString(R.string.toast_discard_success, DISCIPLINE));
	}

	@Override
	protected List<Discipline> findListItens() {
		//TODO: Filter by App
		return this.mDisciplineRestClient.findAll().getEntity();
	}
}
