package com.msplearning.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Feature;
import com.msplearning.entity.enuns.FeatureOperator;
import com.msplearning.entity.enuns.Variability;
import com.msplearning.repository.FeatureRepository;
import com.msplearning.service.generic.BaseService;

/**
 * The FeatureService class provides the business operations of entity {@link Feature}.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("featureService")
public class FeatureService extends BaseService implements InitializingBean {

	@Autowired
	private FeatureRepository featureRepository;

	public List<Feature> getAll() {
		return this.featureRepository.findAll();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.createFeaturesIfThereAreNo();
	}

	/**
	 * Checks for {@link Feature}'s in the database for, if applicable, create standards {@link Feature}'s.
	 */
	private void createFeaturesIfThereAreNo() {
		if (this.featureRepository.findAll().size() == 0) {

			// Pedagogical concrete features:
			Feature pedagogical = new Feature(1L, "Pedagogical", FeatureOperator.A, true, false, false);
			pedagogical.setChildren(new HashSet<Feature>());
			pedagogical.getChildren().add(new Feature(5L, "Content Management", null, true, false, false));
			pedagogical.getChildren().add(new Feature(6L, "Educational Activities", null, true, false, false));
			pedagogical.getChildren().add(new Feature(Variability.INTERACTIVITY.getId(), "Interactivity", null, false, false, false));
			Feature multimediaResources = new Feature(8L, "Multimedia Resources", FeatureOperator.O, true, false, false);
			multimediaResources.setChildren(new HashSet<Feature>());
			multimediaResources.getChildren().add(new Feature(Variability.TEXT.getId(), "Text", null, false, false, false));
			multimediaResources.getChildren().add(new Feature(Variability.IMAGE.getId(), "Image", null, false, false, false));
			multimediaResources.getChildren().add(new Feature(Variability.VIDEO.getId(), "Video", null, false, false, false));
			pedagogical.getChildren().add(multimediaResources);

			// Usability abstract features:
			Feature usability = new Feature(2L, "Usability", FeatureOperator.A, true, true, false);
			usability.setChildren(new HashSet<Feature>());
			usability.getChildren().add(new Feature(9L, "Accessibility", null, true, true, false));
			usability.getChildren().add(new Feature(10L, "Attractiveness", null, true, true, false));
			usability.getChildren().add(new Feature(11L, "Intelligibility", null, true, true, false));
			usability.getChildren().add(new Feature(12L, "Learnability", null, true, true, false));
			usability.getChildren().add(new Feature(13L, "Operability", null, true, true, false));

			// Compatibility concrete features:
			Feature compatibility = new Feature(3L, "Compatibility", FeatureOperator.A, true, false, false);
			compatibility.setChildren(new HashSet<Feature>());
			compatibility.getChildren().add(new Feature(14L, "Coexistence", null, true, true, false));
			compatibility.getChildren().add(new Feature(15L, "Interoperability", null, true, false, false));

			// Security concrete features:
			Feature security = new Feature(4L, "Security", FeatureOperator.A, true, false, false);
			security.setChildren(new HashSet<Feature>());
			security.getChildren().add(new Feature(16L, "Authenticity", null, true, false, false));
			security.getChildren().add(new Feature(17L, "Confidentiality", null, true, false, false));
			security.getChildren().add(new Feature(18L, "Integrity", null, true, false, false));

			this.featureRepository.insert(pedagogical);
			this.featureRepository.insert(usability);
			this.featureRepository.insert(compatibility);
			this.featureRepository.insert(security);
		}
	}

}
