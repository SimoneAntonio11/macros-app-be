package it.macros.app.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import it.macros.app.repositories.AlimentoRepository;
import it.macros.app.repositories.entities.Alimento;
import it.macros.app.services.AlimentoService;
import it.macros.app.services.constants.ServiceErrorCode;
import it.macros.app.services.exceptions.ServiceException;
import it.macros.app.services.validators.AlimentoValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlimentoServiceImpl implements AlimentoService{
	
	@Autowired
	private AlimentoRepository alimentoRepository;
	
	@Autowired
	private AlimentoValidator alimentoValidator;
	
	@Override
	public List<Alimento> list() throws ServiceException{

		List<Alimento> list = null;
		
		try {
			list = alimentoRepository.list();
	} catch (Exception e) {
		log.error("Exception occurs {}", e);
		throw new ServiceException(ServiceErrorCode.ERRORE_GENERICO);
	}
		return list;
	}
	
	@Override
	public List<Alimento> userList(Integer id) throws ServiceException{

		List<Alimento> list = null;
		
		try {
			list = alimentoRepository.userList(id);
	} catch (Exception e) {
		log.error("Exception occurs {}", e);
		throw new ServiceException(ServiceErrorCode.ERRORE_GENERICO);
	}
		return list;
	}
	
	@Override	
	public void insert(Alimento alimento) throws ServiceException {
		if (!alimentoValidator.validate(alimento, true)) {
			log.info("Exception occurs {}", "Validation errors");
			throw new ServiceException(ServiceErrorCode.ERRORE_VALIDAZIONE);
		}
		try {
		alimentoRepository.saveAndFlush(alimento);
		} catch (DataIntegrityViolationException de) {
			log.error("Exception occurs {}", de);
			throw new ServiceException(ServiceErrorCode.RECORD_ESISTENTE);
		} catch (Exception e) {
			log.error("Exception occurs {}", e);
			throw new ServiceException(ServiceErrorCode.ERRORE_GENERICO);
		}
	}
	
	@Override
	public void update(Alimento alimento) throws ServiceException  {	

		if (!alimentoValidator.validate(alimento, true)) {
			log.info("Exception occurs {}", "Validation errors");
			throw new ServiceException(ServiceErrorCode.ERRORE_VALIDAZIONE);
		}
		
		try {
			
		Alimento currentAlimento = alimentoRepository.findById(alimento.getId()).get();
			
		currentAlimento.setId(alimento.getId());
		currentAlimento.setUtente(alimento.getUtente());;
		currentAlimento.setDescrizione(alimento.getDescrizione());
		currentAlimento.setEnergiaKJ(alimento.getEnergiaKJ());
		currentAlimento.setEnergiaKCal(alimento.getEnergiaKCal());
		currentAlimento.setGrassi(alimento.getGrassi());
		currentAlimento.setGrassiSaturi(alimento.getGrassiSaturi());
		currentAlimento.setCarboidrati(alimento.getCarboidrati());
		currentAlimento.setZuccheri(alimento.getZuccheri());
		currentAlimento.setFibre(alimento.getFibre());
		currentAlimento.setProteine(alimento.getProteine());
		currentAlimento.setSale(alimento.getSale());
		currentAlimento.setFerro(alimento.getFerro());
		currentAlimento.setZinco(alimento.getZinco());
		currentAlimento.setPotassio(alimento.getPotassio());
		currentAlimento.setSodio(alimento.getSodio());
		alimentoRepository.saveAndFlush(currentAlimento);
		} catch (DataIntegrityViolationException de) {
			log.error("Exception occurs {}", de);
			throw new ServiceException(ServiceErrorCode.RECORD_ESISTENTE);
		} catch (Exception e) {
			log.error("Exception occurs {}", e);
			throw new ServiceException(ServiceErrorCode.ERRORE_GENERICO);
		}

	}
	
	@Override
	public Alimento get(Integer id) throws ServiceException{
		
		Alimento alimento = null;
	try {
		alimento = alimentoRepository.findById(id).get();
	} catch (NoSuchElementException ne) {
		log.info("Exception occurs {}, ID {}", ne, id);
		throw new ServiceException(ServiceErrorCode.RECORD_NON_TROVATO);
	} catch (Exception e) {
		log.error("Exception occurs {}", e);
		throw new ServiceException(ServiceErrorCode.ERRORE_GENERICO);
	}
		return alimento;
	}
	
	@Override
	public void delete(Integer id) throws ServiceException  {
		
		try {
			Alimento alimento = alimentoRepository.findById(id).get();

			//alimento.setCancellato(true);
			alimentoRepository.delete(alimento);
	} catch (NoSuchElementException ne) {
		log.info("Exception occurs {}, ID {}", ne, id);
		throw new ServiceException(ServiceErrorCode.RECORD_NON_TROVATO);
	} catch (DataIntegrityViolationException de) {
		log.info("Exception occurs {}", de);
		throw new ServiceException(ServiceErrorCode.ERRORE_INTEGRITA_DATI);
	} catch (Exception e) {
		log.info("Exception occurs {}", e);
		throw new ServiceException(ServiceErrorCode.ERRORE_GENERICO);
	}
	}
}
