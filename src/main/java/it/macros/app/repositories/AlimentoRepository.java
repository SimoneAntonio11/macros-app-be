package it.macros.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.macros.app.repositories.entities.Alimento;

public interface AlimentoRepository extends JpaRepository <Alimento, Integer> {
	
	@Query(value = "SELECT a FROM Alimento a ORDER BY a.nome DESC")
	public List<Alimento> list();

}
