package it.macros.app.services;

import java.util.List;

import it.macros.app.repositories.entities.Piatto;
import it.macros.app.services.exceptions.ServiceException;

public interface PiattoService {
	
	public List<Piatto> list() throws ServiceException;
	public void insert(Piatto piatto) throws ServiceException;
	public void delete(Integer id) throws ServiceException;
	public void update(Piatto piatto) throws ServiceException;
	public Piatto get(Integer id) throws ServiceException;

}
