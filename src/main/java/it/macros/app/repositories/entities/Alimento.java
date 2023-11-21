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
@Table(name = "alimenti")
public class Alimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "marchio")
	private String marchio;
	
	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "immagine")
	private String immagine;
	
	@Column(name = "energia_KJ")
	private Double energiaKJ;
	
	@Column(name = "energia_KCAL")
	private Double energiaKCal;
	
	@Column(name = "grassi")
	private Double grassi;
	
	@Column(name = "grassi_saturi")
	private Double grassiSaturi;
	
	@Column(name = "carboidrati")
	private Double carboidrati;
	
	@Column(name = "zuccheri")
	private Double zuccheri;
	
	@Column(name = "fibre")
	private Double fibre;
	
	@Column(name = "proteine")
	private Double proteine;
	
	@Column(name = "sali")
	private Double sale;
	
	@Column(name = "ferro")
	private Double ferro;
	
	@Column(name = "zinco")
	private Double zinco;
	
	@Column(name = "potassio")
	private Double potassio;
	
	@Column(name = "sodio")
	private Double sodio;
}
