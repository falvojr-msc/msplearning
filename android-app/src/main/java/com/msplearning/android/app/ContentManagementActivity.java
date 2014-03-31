package com.msplearning.android.app;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.widget.ListView;

import com.msplearning.android.app.generic.GenericAsyncRestActivity;
import com.msplearning.android.rest.DisciplineRestClient;
import com.msplearning.android.widget.DisciplineListAdapter;
import com.msplearning.entity.Discipline;

/**
 * The DisciplineActivity class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_content_management)
public class ContentManagementActivity extends GenericAsyncRestActivity<MSPLearningApplication> {

	@ViewById
	protected ListView mListView;

	@RestService
	protected DisciplineRestClient mDisciplineRestClient;

	@Bean
	protected DisciplineListAdapter mDisciplineAdapter;

	@AfterViews
	protected void init() {
		super.showLoadingProgressDialog();
		this.loadDisciplines();
	}

	@Background
	protected void loadDisciplines() {
		List<Discipline> disciplines = this.mDisciplineRestClient.findAll().getEntity();
		this.refreshDisciplines(disciplines);
		super.dismissProgressDialog();
	}

	@UiThread
	protected void refreshDisciplines(List<Discipline> disciplines) {
		this.mDisciplineAdapter.setContent(disciplines);
		this.mListView.setAdapter(this.mDisciplineAdapter);
	}

}
