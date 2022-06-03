package org.georges.effun.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.georges.effun.model.Pilote;
import org.georges.effun.service.PiloteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PiloteController {
	
	@Autowired
	private PiloteService ps;
	
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
	public Optional<Pilote> getPiloteById(@PathVariable Integer id){
		return ps.getPiloteById(id);
	}
}