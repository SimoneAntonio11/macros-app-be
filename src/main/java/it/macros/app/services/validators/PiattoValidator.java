package it.macros.app.services.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.macros.app.repositories.PiattoRepository;
import it.macros.app.repositories.entities.Piatto;
import it.macros.app.services.constants.ValidationErrorCode;
import it.macros.app.services.exceptions.ServiceValidationException;


@Component
public class PiattoValidator {
	
	@Autowired
    private PiattoRepository piattoRepository;

	
	public Boolean validate(Piatto piatto, Boolean isNew) throws ServiceValidationException {
        boolean result;

        if (!(result = piatto != null)) {
            return !result;
        }
        result |= attributesEmptyCheck(piatto, isNew);
       
       
        if (!result) {
            throw new ServiceValidationException(ValidationErrorCode.VALIDATION_REPOSITORY_ERROR);
        }
        return true;
    }
 
 private Boolean attributesEmptyCheck(Piatto piatto, Boolean isNew) throws ServiceValidationException {
        boolean empty = false;

        if (isNew && isEmpty(piatto.getId())) {
            empty = false;
        }
        if (isEmpty(piatto.getNome()) ||
            isEmpty(piatto.getUtente().getId()) ||
            isEmpty(piatto.getDescrizione()))           

        if (empty) {
            throw new ServiceValidationException(ValidationErrorCode.VALIDATION_ERROR_MANDATORY_FIELD_MISSING);
        }

        return empty;
    }
 
 private Boolean isEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }

    private Boolean isEmpty(Object object) {
        return object == null;
    }
}
