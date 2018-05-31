package com.cg.aieecosystemapp.utility;

import com.cg.aieecosystemapp.aieexception.AieExceptionClass;
import com.cg.aieecosystemapp.aieexception.AieInvalidFieldsException;
import com.cg.aieecosystemapp.model.Partner;

public class AiePartnerUtility {

	static public void validate(Partner partner) throws AieExceptionClass {
		validateNull(partner);
		validateName(partner);
		validateFoundBy(partner);
		validateUrl(partner);
		validateLocation(partner);
		validateDescription(partner);
	}

	static public void validateNull(Partner partner) {
		if (partner == null) {
			throw new AieInvalidFieldsException("partner cannot be null!");
		}
	}

	static public void validateName(Partner partner) {
		if (partner.getName() == null || partner.getName().isEmpty()) {
			throw new AieInvalidFieldsException("name cannot be null/empty");
		}
	}

	static public void validateFoundBy(Partner partner) {
		if (partner.getFoundBy() == null || partner.getFoundBy().isEmpty()) {
			throw new AieInvalidFieldsException("found-by cannot be null/empty");
		}
	}

	static public void validateUrl(Partner partner) {
		if (partner.getUrl() == null || partner.getUrl().isEmpty()) {
			throw new AieInvalidFieldsException("url cannot be null/empty");
		}
	}

	static public void validateLocation(Partner partner) {
		if (partner.getLocation() == null || partner.getLocation().isEmpty()) {
			throw new AieInvalidFieldsException("location cannot be null/empty");
		}
	}

	static public void validateDescription(Partner partner) {
		if (partner.getDescription() == null || partner.getDescription().isEmpty()) {
			throw new AieInvalidFieldsException("description cannot be null/empty");
		}
	}

}
