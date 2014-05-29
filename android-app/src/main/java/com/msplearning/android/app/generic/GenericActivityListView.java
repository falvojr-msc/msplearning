package com.msplearning.android.app.generic;

import java.io.Serializable;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import android.view.MenuItem;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

import com.msplearning.android.app.MSPLearningApplication;
import com.msplearning.android.app.widget.generic.AbstractListAdapter;

/**
 * The GenericListActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity
public abstract class GenericActivityListView <T extends Serializable> extends GenericAsyncAuthActivity<MSPLearningApplication> {

	protected List<T> itens;

	@AfterViews
	protected void afterViews() {
		// Register the mListView for contextual menu
		this.registerForContextMenu(this.getListView());
		// Async load itens on mListView
		this.loadListItens();
	}

	protected void loadListItens() {
		super.showLoadingProgressDialog();
		this.asyncFindListItens();
	}

	@Background
	protected void asyncFindListItens() {
		List<T> itens = this.findListItens();
		this.bindListItens(itens);
		super.dismissProgressDialog();
	}

	@UiThread
	protected void bindListItens(List<T> itens) {
		this.getListAdapter().setContent(itens);
		this.getListView().setAdapter(this.getListAdapter());
		this.getListAdapter().notifyDataSetChanged();
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		final T selectedItem = this.getListAdapter().getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
		this.onContextItemSelected(item, selectedItem);
		return true;
	}

	@UiThread
	protected void reloadItensShowingToastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		this.loadListItens();
	}

	protected abstract ListView getListView();

	protected abstract AbstractListAdapter<T, ?> getListAdapter();

	protected abstract List<T> findListItens();

	protected abstract void onContextItemSelected(MenuItem item, final T selectedItem);
}
