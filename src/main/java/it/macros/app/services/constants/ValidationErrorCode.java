package it.macros.app.services.constants;

public enum ValidationErrorCode {

	VALIDATION_ERROR_GENERIC(-50),
	VALIDATION_ERROR_MANDATORY_FIELD_MISSING(-51),
	VALIDATION_ERROR_WRONG_FIELD_DIMENSIONS(-52),
	VALIDATION_ERROR_WRONG_FIELD_PATTERNS(-53),
	VALIDATION_ERROR_DUPLICATE_CF(-54),
	VALIDATION_REPOSITORY_ERROR(-55);
	
	private Integer code;

	public int getCode() {
		return code;
	}

	private ValidationErrorCode(Integer code) {
		this.code = code;
	}
	
}
