package it.macros.app.services;

import java.util.List;

import it.macros.app.repositories.entities.Alimento;
import it.macros.app.services.exceptions.ServiceException;


public interface AlimentoService {
	//LIST
	public List<Alimento> list() throws ServiceException;
	public List<Alimento> userList(Integer id) throws ServiceException;	
	//CRUD
	public Alimento get(Integer id) throws ServiceException;
	public void insert(Alimento alimento) throws ServiceException;
	public void update(Alimento alimento) throws ServiceException;
	public void delete(Integer id) throws ServiceException;
	
	
}
