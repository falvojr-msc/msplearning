package com.msplearning.android.app;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.msplearning.android.app.generic.GenericAsyncAuthActivity;
import com.msplearning.android.app.rest.EducationalContentRestClient;
import com.msplearning.entity.EducationalContent;
import com.msplearning.entity.enuns.MediaType;

/**
 * The DisciplineManagerActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_educational_content_manager)
public class EducationalContentManagerActivity extends GenericAsyncAuthActivity<MSPLearningApplication> {

	/**
	 * EXTRA_KEY_EDUCATIONAL_CONTENT Intent extra key.
	 */
	public static final String EXTRA_KEY_EDUCATIONAL_CONTENT = "E.educationalContent";
	/**
	 * EXTRA_KEY_ID_LESSON Intent extra key.
	 */
	public static final String EXTRA_KEY_ID_LESSON = "E.lesson.id";
	/**
	 * EXTRA_KEY_COUNT_EDUCATIONAL_CONTENT Intent extra key.
	 */
	public static final String EXTRA_KEY_COUNT_EDUCATIONAL_CONTENT = "E.educationalContents.count";

	@ViewById(R.id.educational_content_label_page)
	protected TextView mLabelPageView;
	@ViewById(R.id.radio_group_multimedia_type)
	protected RadioGroup mGroupMultimediaView;
	@ViewById(R.id.radio_text)
	protected RadioButton mMultimediaTextView;
	@ViewById(R.id.radio_image)
	protected RadioButton mMultimediaImageView;
	@ViewById(R.id.radio_video)
	protected RadioButton mMultimediaVideoView;
	@ViewById(R.id.educational_content_title)
	protected EditText mTitleView;
	@ViewById(R.id.educational_content_content)
	protected EditText mContentView;
	@ViewById(R.id.educational_content_footnote)
	protected EditText mFootnoteView;
	@ViewById(R.id.educational_content_page)
	protected SeekBar mPageView;

	@RestService
	protected EducationalContentRestClient mEducationalContentRestClient;

	private EducationalContent currentEducationalContent;

	@AfterViews
	public void afterViews() {
		// Configure page limit
		Integer countEducationalContents = this.getIntent().getIntExtra(EXTRA_KEY_COUNT_EDUCATIONAL_CONTENT, 0);
		this.mPageView.setMax(countEducationalContents);
		// Insert and edit configurations
		this.currentEducationalContent = (EducationalContent) this.getIntent().getSerializableExtra(EXTRA_KEY_EDUCATIONAL_CONTENT);
		if (this.currentEducationalContent == null) {
			Long idLesson = this.getIntent().getLongExtra(EXTRA_KEY_ID_LESSON, 0L);
			this.currentEducationalContent = new EducationalContent();
			this.currentEducationalContent.setIdLesson(idLesson);
			this.mPageView.setProgress(this.mPageView.getMax() + 1);
		} else {
			// Disregards the edited element
			this.mPageView.setMax(this.mPageView.getMax() - 1);
			// Fill the fields
			switch (this.currentEducationalContent.getMediaType()) {
			case T:
				this.mMultimediaTextView.setChecked(true);
				break;
			case I:
				this.mMultimediaImageView.setChecked(true);
				break;
			default:
				this.mMultimediaVideoView.setChecked(true);
				break;
			}
			this.mTitleView.setText(this.currentEducationalContent.getTitle());
			this.mContentView.setText(this.currentEducationalContent.getContent());
			this.mFootnoteView.setText(this.currentEducationalContent.getFootnote());
			this.mPageView.setProgress(this.currentEducationalContent.getPage().intValue());
		}
		// Common configurations
		this.configureLabelPage();
		this.mPageView.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				EducationalContentManagerActivity.this.configureLabelPage();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) { }

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) { }
		});
		this.configureMediaType();
		this.mGroupMultimediaView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				EducationalContentManagerActivity.this.mContentView.setText("");
				EducationalContentManagerActivity.this.configureMediaType();
			}
		});
		// Remove used extra keys
		this.getIntent().removeExtra(EXTRA_KEY_COUNT_EDUCATIONAL_CONTENT);
		this.getIntent().removeExtra(EXTRA_KEY_EDUCATIONAL_CONTENT);
		this.getIntent().removeExtra(EXTRA_KEY_ID_LESSON);
	}

	private void configureLabelPage() {
		int currentPage = this.mPageView.getProgress() + 1;
		int maxPage = this.mPageView.getMax() + 1;
		this.mLabelPageView.setText(String.format(EducationalContentManagerActivity.this.getString(R.string.prompt_page), currentPage, maxPage));
	}

	protected void configureMediaType() {
		boolean hasURL = this.mMultimediaImageView.isChecked() || this.mMultimediaVideoView.isChecked();
		this.mContentView.setHint(hasURL ? R.string.prompt_url : R.string.prompt_description);
		this.mContentView.setSingleLine(hasURL);
		this.mContentView.setMaxLines(hasURL ? 1 : 3);
		this.mContentView.setMinLines(hasURL ? 1 : 3);
	}

	@Click(R.id.button_save)
	public void onDisciplineSave() {
		super.showLoadingProgressDialog();

		if (this.mMultimediaImageView.isChecked()) {
			this.currentEducationalContent.setMediaType(MediaType.I);
		} else if (this.mMultimediaTextView.isChecked()) {
			this.currentEducationalContent.setMediaType(MediaType.T);
		} else {
			this.currentEducationalContent.setMediaType(MediaType.V);
		}
		this.currentEducationalContent.setTitle(this.mTitleView.getText().toString());
		this.currentEducationalContent.setContent(this.mContentView.getText().toString());
		this.currentEducationalContent.setFootnote(this.mFootnoteView.getText().toString());
		this.currentEducationalContent.setPage(Long.valueOf(this.mPageView.getProgress() + 1));

		this.saveEducationalContent();
	}

	@Background
	public void saveEducationalContent() {
		try {
			if (this.currentEducationalContent.getId() == null) {
				this.mEducationalContentRestClient.insert(this.currentEducationalContent);
			} else {
				this.mEducationalContentRestClient.update(this.currentEducationalContent);
			}
			this.currentEducationalContent = null;
		} catch (Exception exception) {
			super.showDialogAlert(exception.getMessage(), null);
		} finally {
			super.dismissProgressDialog();
		}
		this.setResult(RESULT_OK);
		this.finish();
	}
}
