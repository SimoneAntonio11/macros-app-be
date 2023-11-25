package it.macros.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.macros.app.controllers.beans.Esito;
import it.macros.app.controllers.beans.requests.AlimentoRequest;
import it.macros.app.controllers.beans.responses.AlimentoListResponse;
import it.macros.app.controllers.beans.responses.AlimentoResponse;
import it.macros.app.controllers.beans.responses.GenericResponse;
import it.macros.app.controllers.constants.ControllerMaps;
import it.macros.app.repositories.entities.Alimento;
import it.macros.app.services.AlimentoService;
import it.macros.app.services.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
public class AlimentoController extends BaseController {

	@Autowired
	private AlimentoService alimentoService;
	
	@RequestMapping(value = "/alimenti/list", method = RequestMethod.GET, produces = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<AlimentoListResponse> list() {

		HttpEntity<AlimentoListResponse> httpEntity = null;

		AlimentoListResponse alimentoListResponse = new AlimentoListResponse();

		try {
		log.info("START invocation list() of controller layer");

		List<Alimento> alimenti = alimentoService.list();

		alimentoListResponse.setList(alimenti);
		alimentoListResponse.setEsito(new Esito());
		
		httpEntity = new HttpEntity<AlimentoListResponse>(alimentoListResponse);

		log.info("END invocation list() of controller layer");
		} catch (ServiceException e) {
			alimentoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AlimentoListResponse>(alimentoListResponse);
		}

		return httpEntity;
	}
	
	@RequestMapping(value = "/alimenti/list/{id}", method = RequestMethod.GET, produces = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<AlimentoListResponse> userList(@PathVariable Integer id) {

		HttpEntity<AlimentoListResponse> httpEntity = null;

		AlimentoListResponse alimentoListResponse = new AlimentoListResponse();

		try {
		log.info("START invocation list() of controller layer");

		List<Alimento> alimenti = alimentoService.userList(id);

		alimentoListResponse.setList(alimenti);
		alimentoListResponse.setEsito(new Esito());
		
		httpEntity = new HttpEntity<AlimentoListResponse>(alimentoListResponse);

		log.info("END invocation list() of controller layer");
		} catch (ServiceException e) {
			alimentoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AlimentoListResponse>(alimentoListResponse);
		}

		return httpEntity;
	}
	
	@RequestMapping(value = "/alimenti", method = RequestMethod.POST, consumes = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> insert(@RequestBody AlimentoRequest alimentoRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		try {
			log.info("START invocation insert(alimento) of controller layer");
			
			Alimento alimento = alimentoRequest.getAlimento();
			alimentoService.insert(alimento);
			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			log.info("END invocation insert(alimento) of controller layer");
	} catch (ServiceException e) {
		genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
		httpEntity = new HttpEntity<GenericResponse>(genericResponse);
	}
		return httpEntity;
	}
	
	@RequestMapping(value = "/alimenti/{id}", method = RequestMethod.GET, produces = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<AlimentoResponse> get(@PathVariable Integer id) {
		
		HttpEntity<AlimentoResponse> httpEntity = null;

		AlimentoResponse alimentoResponse = new AlimentoResponse();
		
		try {
			log.info("START invocation detail() of controller layer");

			Alimento alimento = alimentoService.get(id);

			alimentoResponse.setAlimento(alimento);
			alimentoResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AlimentoResponse>(alimentoResponse);

			log.info("END invocation detail() of controller layer");
	} catch (ServiceException e) {
		alimentoResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
		httpEntity = new HttpEntity<AlimentoResponse>(alimentoResponse);
	}

		return httpEntity;
	}
	
	@RequestMapping(value = "/alimenti", method = RequestMethod.PUT, consumes = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> update(@RequestBody AlimentoRequest alimentoRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		try {	
			log.info("START invocation update(contratto) of controller layer");
			
			alimentoService.update(alimentoRequest.getAlimento());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			log.info("END invocation update(alimento) of controller layer");
			
	} catch (ServiceException e) {
		genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
		httpEntity = new HttpEntity<GenericResponse>(genericResponse);
	}
		return httpEntity;
	}

	
	@RequestMapping(value = "/alimenti/{id}", method = RequestMethod.DELETE, produces = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable Integer id) {
		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

	try {
			log.info("START invocation delete() of controller layer");

			alimentoService.delete(id);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			log.info("END invocation delete() of controller layer");
			
	} catch (ServiceException e) {
		genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
		httpEntity = new HttpEntity<GenericResponse>(genericResponse);
	}
			return httpEntity;
	}
}
