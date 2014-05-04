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
		this.findDisciplines();
	}

	@Background
	protected void findDisciplines() {
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
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch(item.getItemId()){
		case R.id.action_edit:
			Intent intent = DisciplineManagerActivity_.intent(this).get();
			intent.putExtra(EXTRA_KEY_DISCIPLINE, this.mDisciplineAdapter.getItem(info.position));
			this.startActivityForResult(intent, REQUEST_CODE_EDIT_DISCIPLINE);
			break;
		case R.id.action_discard:
			// Discipline discipline = this.mDisciplineAdapter.getItem(info.position);
			// TODO: Implement async discard
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
			// Show informative message
			Toast.makeText(this, super.getString(idResource), Toast.LENGTH_SHORT).show();
			// TODO: Verify reload disciplines
			this.loadDisciplines();
		} else if (resultCode == RESULT_CANCELED) {
			this.showDialogAlert("Unexpected error", null);
		}
	}
}
