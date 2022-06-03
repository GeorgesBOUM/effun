package org.georges.effun.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.georges.effun.model.Pilote;
import org.georges.effun.model.ResultatGeneral;
import org.georges.effun.service.PiloteService;
import org.georges.effun.service.ResultatGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import net.bytebuddy.build.AccessControllerPlugin;

@RestController
public class PiloteController {

	@Autowired
	private PiloteService ps;
	@Autowired
	private ResultatGeneralService rgs;

	@GetMapping("/pilotes")
	public ArrayList<Pilote> getAllPilotes() {
		return ps.getAllPilotes();
	}

	@PostMapping("/pilotes")
	public ResponseEntity<Long> addPilote(Pilote p) {
		Pilote piloteAjoute = ps.ajoutPilote(p);
		return new ResponseEntity<Long>(piloteAjoute.getEcurie().getId(), HttpStatus.OK);
	}

	@GetMapping("/pilotes/{piloteId}")
	public Optional<Pilote> getPiloteById(@PathVariable Integer id) {
		return ps.getPiloteById(id);
	}

	@GetMapping("/pilotes:{piloteId}/victoire")
	public int nombreVictoiresPilote(@PathVariable Integer id) {
		/**
		 * Etapes pour récupérer le total des victoires d'un pilote, ayant son id
		 * Recupérer tous les résultats généraux => une liste (rgl)
		 * Chaque élément de rgl contient une liste de résultats des pilotes (rpl)
		 * Pour chaque rpl, je vérifie si l'id du pilote est celui recherché
		 * Si oui, et si place = 1, alors on comptabilise une victoire pour le pilote
		 * */
		rgs.getAllResultatsGeneraux().forEach(rgl -> rgl.getListeResultatsTousPilotes().stream()
																					   .filter(rpl -> rpl.getPilote().getId().equals(id))
																					   .filter(rpl -> rpl.getPlace().equals(1))
																					   .count());
		return 0;
	}
}
