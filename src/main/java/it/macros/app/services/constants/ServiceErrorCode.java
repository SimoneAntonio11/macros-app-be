package it.macros.app.services.constants;

public enum ServiceErrorCode {

	ERRORE_GENERICO(-1),
	ERRORE_INTEGRITA_DATI(-2),
	RECORD_NON_TROVATO(-3),
	RECORD_ESISTENTE(-4),
	ERRORE_VALIDAZIONE(-5);

	private Integer code;

	public int getCode() {
		return code;
	}

	private ServiceErrorCode(Integer code) {
		this.code = code;
	}
	
	ServiceErrorCode(ValidationErrorCode code) {
		this.code = code.getCode();
	}
	
}