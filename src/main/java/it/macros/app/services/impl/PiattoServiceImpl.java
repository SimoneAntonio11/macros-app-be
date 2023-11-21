package it.macros.app.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import it.macros.app.repositories.PiattoRepository;
import it.macros.app.repositories.entities.Piatto;
import it.macros.app.services.PiattoService;
import it.macros.app.services.constants.ServiceErrorCode;
import it.macros.app.services.exceptions.ServiceException;
import it.macros.app.services.validators.PiattoValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PiattoServiceImpl implements PiattoService {
	
	@Autowired
	private PiattoRepository piattoRepository;
	
	@Autowired
	private PiattoValidator piattoValidator;
	
	@Override
	public List<Piatto> list() throws ServiceException{

		List<Piatto> list = null;
		try {
			list = piattoRepository.list();
	} catch (Exception e) {
		log.error("Exception occurs {}", e);
		throw new ServiceException(ServiceErrorCode.ERRORE_GENERICO);
	}
		return list;
	}
	
	@Override	
	public void insert(Piatto piatto) throws ServiceException {
		if (!piattoValidator.validate(piatto, true)) {
			log.info("Exception occurs {}", "Validation errors");
			throw new ServiceException(ServiceErrorCode.ERRORE_VALIDAZIONE);
		}
		try {
		piattoRepository.saveAndFlush(piatto);
		} catch (DataIntegrityViolationException de) {
			log.error("Exception occurs {}", de);
			throw new ServiceException(ServiceErrorCode.RECORD_ESISTENTE);
		} catch (Exception e) {
			log.error("Exception occurs {}", e);
			throw new ServiceException(ServiceErrorCode.ERRORE_GENERICO);
		}
	}
	
	@Override
	public void update(Piatto piatto) throws ServiceException  {
		if (!piattoValidator.validate(piatto, true)) {
			log.info("Exception occurs {}", "Validation errors");
			throw new ServiceException(ServiceErrorCode.ERRORE_VALIDAZIONE);
		}
		try {
			
		Piatto currentPiatto = piattoRepository.findById(piatto.getId()).get();

		currentPiatto.setId(piatto.getId());
		currentPiatto.setUtente(piatto.getUtente());;
		currentPiatto.setDescrizione(piatto.getDescrizione());
		currentPiatto.setTotEnergiaKJ(piatto.getTotEnergiaKJ());
		currentPiatto.setTotEnergiaKCal(piatto.getTotEnergiaKCal());
		currentPiatto.setTotGrassi(piatto.getTotGrassi());
		currentPiatto.setTotGrassiSaturi(piatto.getTotGrassiSaturi());
		currentPiatto.setTotCarboidrati(piatto.getTotCarboidrati());
		currentPiatto.setTotZuccheri(piatto.getTotZuccheri());
		currentPiatto.setTotFibre(piatto.getTotFibre());
		currentPiatto.setTotProteine(piatto.getTotProteine());
		currentPiatto.setTotSali(piatto.getTotSali());
		currentPiatto.setTotFerro(piatto.getTotFerro());
		currentPiatto.setTotZinco(piatto.getTotZinco());
		currentPiatto.setTotPotassio(piatto.getTotPotassio());
		currentPiatto.setTotSodio(piatto.getTotSodio());
		piattoRepository.saveAndFlush(currentPiatto);
		
		} catch (DataIntegrityViolationException de) {
			log.error("Exception occurs {}", de);
			throw new ServiceException(ServiceErrorCode.RECORD_ESISTENTE);
		} catch (Exception e) {
			log.error("Exception occurs {}", e);
			throw new ServiceException(ServiceErrorCode.ERRORE_GENERICO);
		}
	}
	
	@Override
	public Piatto get(Integer id) throws ServiceException{
		
		Piatto piatto = null;
	try {
		piatto = piattoRepository.findById(id).get();
	} catch (NoSuchElementException ne) {
		log.info("Exception occurs {}, ID {}", ne, id);
		throw new ServiceException(ServiceErrorCode.RECORD_NON_TROVATO);
	} catch (Exception e) {
		log.error("Exception occurs {}", e);
		throw new ServiceException(ServiceErrorCode.ERRORE_GENERICO);
	}
		return piatto;
	}
	
	@Override
	public void delete(Integer id) throws ServiceException  {
		
		Piatto piatto = piattoRepository.findById(id).get();
		
		try {
			//piatto.setCancellato(true);
			piattoRepository.delete(piatto);
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
