package it.macros.app.repositories.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "piatti")
public class Piatto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "immagine")
	private String immagine;
	
	@Column(name = "tot_energia_KJ")
	private Double totEnergiaKJ;
	
	@Column(name = "tot_energia_kCal")
	private Double totEnergiaKCal;
	
	@Column(name = "tot_grassi")
	private Double totGrassi;
	
	@Column(name = "tot_grassi_saturi")
	private Double totGrassiSaturi;
	
	@Column(name = "tot_carboidrati")
	private Double totCarboidrati;
	
	@Column(name = "tot_zuccheri")
	private Double totZuccheri;
	
	@Column(name = "tot_fibre")
	private Double totFibre;
	
	@Column(name = "tot_proteine")
	private Double totProteine;
	
	@Column(name = "tot_sali")
	private Double totSali;
	
	@Column(name = "tot_ferro")
	private Double totFerro;
	
	@Column(name = "tot_zinco")
	private Double totZinco;
	
	@Column(name = "tot_potassio")
	private Double totPotassio;
	
	@Column(name = "tot_sodio")
	private Double totSodio;

}
