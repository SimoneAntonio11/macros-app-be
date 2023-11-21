package it.macros.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.macros.app.repositories.entities.Piatto;

public interface PiattoRepository extends JpaRepository <Piatto, Integer> {
	
	@Query(value = "SELECT a FROM Piatto a ORDER BY a.nome DESC")
	public List<Piatto> list();


}
