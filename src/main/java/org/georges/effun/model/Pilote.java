package org.georges.effun.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pilote")
public class Pilote {
	private Integer id;
	private String nom;
	private String prenom;
	private String nationalite;
	private Ecurie ecurie;
	private String acronyme;
	private Byte numero;
}
