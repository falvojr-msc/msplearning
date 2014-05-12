package com.msplearning.android.app;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

import com.msplearning.android.app.generic.GenericAsyncAuthActivity;
import com.msplearning.android.rest.DisciplineRestClient;
import com.msplearning.android.widget.DisciplineListAdapter;
import com.msplearning.entity.Discipline;

/**
 * The DisciplineActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_discipline_list)
@OptionsMenu(R.menu.actionbar_discipline_list)
public class DisciplineListActivity extends GenericAsyncAuthActivity<MSPLearningApplication> {

	private static final int REQUEST_CODE_NEW_DISCIPLINE = 0;
	private static final int REQUEST_CODE_EDIT_DISCIPLINE = 1;
	public static final String EXTRA_KEY_DISCIPLINE = "activity.discipline";

	@ViewById(R.id.list_view_disciplines)
	protected ListView mListView;

	@Bean
	protected DisciplineListAdapter mDisciplineAdapter;

	@RestService
	protected DisciplineRestClient mDisciplineRestClient;

	@AfterViews
	protected void afterViews() {
		// Register the mListView for contextual menu
		this.registerForContextMenu(this.mListView);
		// Async load disciplines on mListView
		this.loadDisciplines();
	}

	private void loadDisciplines() {
		super.showLoadingProgressDialog();
		this.asyncFindDisciplines();
	}
	
	@Background
	protected void asyncFindDisciplines() {
		List<Discipline> disciplines = this.mDisciplineRestClient.findAll().getEntity();
		this.bindDisciplines(disciplines);
		super.dismissProgressDialog();
	}
	
	@UiThread
	protected void bindDisciplines(List<Discipline> disciplines) {
		this.mDisciplineAdapter.setContent(disciplines);
		this.mListView.setAdapter(this.mDisciplineAdapter);
		this.mDisciplineAdapter.notifyDataSetChanged();
	}

	@OptionsItem(R.id.action_refresh)
	protected void onRefresh() {
		this.loadDisciplines();
	}

	@OptionsItem(R.id.action_new)
	protected void onNew() {
		Intent intent = DisciplineManagerActivity_.intent(this).get();
		this.startActivityForResult(intent, REQUEST_CODE_NEW_DISCIPLINE);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, view, menuInfo);
		super.getMenuInflater().inflate(R.menu.contextual_discipline_list , menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		final Discipline selectedDiscipline = this.mDisciplineAdapter.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
		switch(item.getItemId()){
		case R.id.action_edit:
			Intent intent = DisciplineManagerActivity_.intent(this).get();
			intent.putExtra(EXTRA_KEY_DISCIPLINE, selectedDiscipline);
			this.startActivityForResult(intent, REQUEST_CODE_EDIT_DISCIPLINE);
			break;
		case R.id.action_discard:
			OnClickListener listenerYes = new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					asyncDiscardDiscipline(selectedDiscipline.getId());
				}
				
			};
			this.showDialogConfirm( this.getString(R.string.dialog_title_register), this.getString(R.string.dialog_message_register), listenerYes, null);
			break;
		}
		return true;
	}

	@OnActivityResult(REQUEST_CODE_NEW_DISCIPLINE)
	protected void onResultNew(int resultCode) {
		this.showMessageFromResult(resultCode, R.string.toast_new_discipline_success);
	}

	@OnActivityResult(REQUEST_CODE_EDIT_DISCIPLINE)
	protected void onResultEdit(int resultCode) {
		this.showMessageFromResult(resultCode, R.string.toast_edit_discipline_success);
	}

	private void showMessageFromResult(int resultCode, int idResource) {
		if (resultCode == RESULT_OK) {
			reloadDisciplinesShowingToastMessage(idResource);
		} else if (resultCode == RESULT_CANCELED) {
			this.showDialogAlert("Unexpected error", null);
		}
	}

	@Background
	protected void asyncDiscardDiscipline(Long id) {
		DisciplineListActivity.this.mDisciplineRestClient.delete(id);
		reloadDisciplinesShowingToastMessage(R.string.toast_discard_discipline_success);
	}
	
	@UiThread
	protected void reloadDisciplinesShowingToastMessage(int idResource) {
		Toast.makeText(this, super.getString(idResource), Toast.LENGTH_SHORT).show();
		this.loadDisciplines();
	}
}
