package org.georges.effun.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ecurie")
public class Ecurie {
	private Long id;
	private String nom;
	private String nationalite;
}
