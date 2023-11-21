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
import it.macros.app.controllers.beans.requests.PiattoRequest;
import it.macros.app.controllers.beans.responses.GenericResponse;
import it.macros.app.controllers.beans.responses.PiattoListResponse;
import it.macros.app.controllers.beans.responses.PiattoResponse;
import it.macros.app.controllers.constants.ControllerMaps;
import it.macros.app.repositories.entities.Piatto;
import it.macros.app.services.PiattoService;
import it.macros.app.services.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
public class PiattoController extends BaseController {

	@Autowired
	private PiattoService piattoService;
	
	@RequestMapping(value = "/piatti", method = RequestMethod.GET, produces = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<PiattoListResponse> list() {

		HttpEntity<PiattoListResponse> httpEntity = null;

		PiattoListResponse piattoListResponse = new PiattoListResponse();

		try {
		log.info("START invocation getAll() of controller layer");

		List<Piatto> piatti = piattoService.list();

		piattoListResponse.setList(piatti);
		piattoListResponse.setEsito(new Esito());
		
		httpEntity = new HttpEntity<PiattoListResponse>(piattoListResponse);

		log.info("END invocation list() of controller layer");
		
		} catch (ServiceException e) {
			piattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<PiattoListResponse>(piattoListResponse);
		}
		return httpEntity;
	}
	
	@RequestMapping(value = "/piatti", method = RequestMethod.POST, consumes = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> insert(@RequestBody PiattoRequest piattoRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		
		try {
			log.info("START invocation insert(piatto) of controller layer");
			
			Piatto piatto = piattoRequest.getPiatto();
			piattoService.insert(piatto);
			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			log.info("END invocation insert(piatto) of controller layer");
		} catch (ServiceException e) {
				genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
				httpEntity = new HttpEntity<GenericResponse>(genericResponse);
			}
		return httpEntity;
	}
	
	@RequestMapping(value = "/piatti/{id}", method = RequestMethod.GET, produces = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<PiattoResponse> get(@PathVariable Integer id) {
		
		HttpEntity<PiattoResponse> httpEntity = null;

		PiattoResponse piattoResponse = new PiattoResponse();
		
		
		try {
			log.info("START invocation detail() of controller layer");

			Piatto piatto = piattoService.get(id);

			piattoResponse.setPiatto(piatto);
			piattoResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<PiattoResponse>(piattoResponse);

			log.info("END invocation detail() of controller layer");
		} catch (ServiceException e) {
				piattoResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
				httpEntity = new HttpEntity<PiattoResponse>(piattoResponse);
			}
		return httpEntity;
	}
	
	@RequestMapping(value = "/piatti", method = RequestMethod.PUT, consumes = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> update(@RequestBody PiattoRequest piattoRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
			log.info("START invocation update(piatto) of controller layer");
			
			piattoService.update(piattoRequest.getPiatto());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			log.info("END invocation update(piatto) of controller layer");
		}	catch (ServiceException e) {
				genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
				httpEntity = new HttpEntity<GenericResponse>(genericResponse);
			}
		return httpEntity;
	}

	
	@RequestMapping(value = "/piatti/{id}", method = RequestMethod.DELETE, produces = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable Integer id) {
		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

	try {
			log.info("START invocation delete() of controller layer");

			piattoService.delete(id);

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
