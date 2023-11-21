package it.macros.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.macros.app.repositories.entities.Ricetta;

public interface RicettaRepository extends JpaRepository <Ricetta, Integer> {
 
}
