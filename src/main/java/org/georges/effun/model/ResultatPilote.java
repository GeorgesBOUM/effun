package org.georges.effun.model;

import java.time.Duration;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "resultat_pilote")
public class ResultatPilote {
	private Integer id;
	private Pilote pilote;
	private Duration meilleurTemps;
	private Integer place;
}
