package com.msplearning.android.app;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.annotation.SuppressLint;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.msplearning.android.app.generic.GenericAsyncAuthActivity;
import com.msplearning.android.app.rest.EducationalContentRestClient;
import com.msplearning.entity.AppFeature;
import com.msplearning.entity.EducationalContent;
import com.msplearning.entity.enuns.MediaType;
import com.msplearning.entity.enuns.Variability;

/**
 * The DisciplineManagerActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_educational_content)
public class EducationalContentActivity extends GenericAsyncAuthActivity<MSPLearningApplication> {

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
	TextView mLabelPageView;
	@ViewById(R.id.radio_group_multimedia_type)
	RadioGroup mGroupMultimediaView;
	@ViewById(R.id.educational_content_title)
	EditText mTitleView;
	@ViewById(R.id.educational_content_content)
	EditText mContentView;
	@ViewById(R.id.educational_content_footnote)
	EditText mFootnoteView;
	@ViewById(R.id.educational_content_page)
	SeekBar mPageView;

	@RestService
	EducationalContentRestClient mEducationalContentRestClient;

	private EducationalContent currentEducationalContent;

	@SuppressLint("NewApi")
	@AfterViews
	void afterViews() {
		super.getActionBar().setSubtitle(R.string.app_subtitle_educational_content);

		this.resolveMultimediaTypeVariability();

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
				this.mGroupMultimediaView.check(R.id.radio_text);
				break;
			case I:
				this.mGroupMultimediaView.check(R.id.radio_image);
				break;
			default:
				this.mGroupMultimediaView.check(R.id.radio_video);
				break;
			}
			this.mTitleView.setText(this.currentEducationalContent.getTitle());
			this.mContentView.setText(this.currentEducationalContent.getContent());
			this.mFootnoteView.setText(this.currentEducationalContent.getFootnote());
			this.mPageView.setProgress(this.currentEducationalContent.getPage().intValue() - 1);
		}
		// Common configurations
		this.configureLabelPage();
		this.mPageView.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				EducationalContentActivity.this.configureLabelPage();
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
				CharSequence hintBeforeConfig = EducationalContentActivity.this.mContentView.getHint();
				EducationalContentActivity.this.configureMediaType();
				if (!hintBeforeConfig.equals(EducationalContentActivity.this.mContentView.getHint())) {
					EducationalContentActivity.this.mContentView.setText("");
				}
			}
		});
		// Remove used extra keys
		this.getIntent().removeExtra(EXTRA_KEY_COUNT_EDUCATIONAL_CONTENT);
		this.getIntent().removeExtra(EXTRA_KEY_EDUCATIONAL_CONTENT);
		this.getIntent().removeExtra(EXTRA_KEY_ID_LESSON);
	}

	/**
	 * XXX Variation Point: Multimedia Resources.
	 */
	private void resolveMultimediaTypeVariability() {
		// Resolve the variation point for "Multimedia Resources" feature:
		for(AppFeature appFeature : super.getApplicationContext().getAppSettings().getApp().getAppFeatures()) {
			if (Variability.AUDIO.getId().equals(appFeature.getId().getFeature().getId())) {
				this.mGroupMultimediaView.getChildAt(0).setEnabled(appFeature.isActive());
			} else if (Variability.TEXT.getId().equals(appFeature.getId().getFeature().getId())) {
				this.mGroupMultimediaView.getChildAt(1).setEnabled(appFeature.isActive());
			} else if (Variability.IMAGE.getId().equals(appFeature.getId().getFeature().getId())) {
				this.mGroupMultimediaView.getChildAt(2).setEnabled(appFeature.isActive());
			} else if (Variability.VIDEO.getId().equals(appFeature.getId().getFeature().getId())) {
				this.mGroupMultimediaView.getChildAt(3).setEnabled(appFeature.isActive());
			}
		}
		// Configure the RadioButton selected by default:
		for (int i = 0; i < this.mGroupMultimediaView.getChildCount(); i++) {
			View child = this.mGroupMultimediaView.getChildAt(i);
			if (child.isEnabled()) {
				this.mGroupMultimediaView.check(child.getId());
				break;
			}
		}
	}

	private void configureLabelPage() {
		int currentPage = this.mPageView.getProgress() + 1;
		int maxPage = this.mPageView.getMax() + 1;
		this.mLabelPageView.setText(String.format(EducationalContentActivity.this.getString(R.string.prompt_page), currentPage, maxPage));
	}

	private void configureMediaType() {
		boolean isURL = this.isURLContent();
		int lines = isURL ? 1 : 3;
		this.mContentView.setHint(isURL ? R.string.prompt_url : R.string.prompt_description);
		this.mContentView.setInputType(isURL ? InputType.TYPE_TEXT_VARIATION_URI : InputType.TYPE_TEXT_VARIATION_NORMAL | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		this.mContentView.setSingleLine(isURL);
		this.mContentView.setMaxLines(lines);
		this.mContentView.setMinLines(lines);
	}

	private boolean isURLContent() {
		return this.mGroupMultimediaView.getCheckedRadioButtonId() != R.id.radio_text;
	}

	@Click(R.id.button_save)
	void onDisciplineSave() {
		boolean isValid = super.verifyRequiredFields(this.mTitleView, this.mContentView);
		if (this.mContentView.getError() == null && this.isURLContent()) {
			if (!Patterns.WEB_URL.matcher(this.mContentView.getText()).matches()) {
				if (isValid) {
					this.mContentView.requestFocus();
				}
				isValid = false;
				this.mContentView.setError(super.getString(R.string.error_invalid_url));
			}
		}
		if (isValid) {
			super.showLoadingProgressDialog();

			if (this.mGroupMultimediaView.getCheckedRadioButtonId() == R.id.radio_image) {
				this.currentEducationalContent.setMediaType(MediaType.I);
			} else if (this.mGroupMultimediaView.getCheckedRadioButtonId() == R.id.radio_text) {
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
	}

	@Background
	void saveEducationalContent() {
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
