package org.georges.effun.model;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "resultat_general")
public class GrandPrix {
	private Long id;
	private String nom;
	private ArrayList<ResultatPilote> listeResultatsTousPilotes;
	private LocalDate date;
}
