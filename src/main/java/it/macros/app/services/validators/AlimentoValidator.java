package it.macros.app.services.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.macros.app.repositories.AlimentoRepository;
import it.macros.app.repositories.entities.Alimento;
import it.macros.app.services.constants.ValidationErrorCode;
import it.macros.app.services.exceptions.ServiceValidationException;

@Component
public class AlimentoValidator {

	 @Autowired
	    private AlimentoRepository alimentoRepository;
	
	 
	 public Boolean validate(Alimento alimento, Boolean isNew) throws ServiceValidationException {
	        boolean result;

	        if (!(result = alimento != null)) {
	            return !result;
	        }
	        result |= attributesEmptyCheck(alimento, isNew);
	       
	       
	        if (!result) {
	            throw new ServiceValidationException(ValidationErrorCode.VALIDATION_REPOSITORY_ERROR);
	        }
	        return true;
	    }
	 
	 private Boolean attributesEmptyCheck(Alimento alimento, Boolean isNew) throws ServiceValidationException {
	        boolean empty = false;

	        if (isNew && isEmpty(alimento.getId())) {
	            empty = false;
	        }
	        if (isEmpty(alimento.getNome()) ||
	            isEmpty(alimento.getMarchio()) ||
	            isEmpty(alimento.getUtente().getId()) ||
	            isEmpty(alimento.getDescrizione()))           

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
