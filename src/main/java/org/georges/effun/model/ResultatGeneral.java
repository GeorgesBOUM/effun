package org.georges.effun.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "resultat_general")
public class ResultatGeneral {
	private Long id;
	private String grandPrix;
	private ArrayList<ResultatPilote> listeResultatsTousPilotes;
}
