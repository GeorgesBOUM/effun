package org.georges.effun.controller;

import java.util.List;
import java.util.Optional;

import org.georges.effun.model.Pilote;
import org.georges.effun.service.GrandPrixService;
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
    @Autowired
    private GrandPrixService rgs;

    @GetMapping("/pilotes")
    public List<Pilote> getAllPilotes() {
	return ps.getAllPilotes();
    }

    @PostMapping("/pilotes")
    public ResponseEntity<Integer> addPilote(Pilote p) {
	Pilote piloteAjoute = ps.ajoutPilote(p);
	return new ResponseEntity<Integer>(piloteAjoute.getId(), HttpStatus.OK);
    }

    @GetMapping("/pilotes/{piloteId}")
    public Optional<Pilote> getPiloteById(@PathVariable Integer id) {
	return ps.getPiloteById(id);
    }

    @GetMapping("/pilotes:{piloteId}/victoire")
    public Long nombreVictoiresPilote(@PathVariable Integer id) {
	return rgs.getAllResultatsGeneraux()
		  .stream()
		  .filter(rgl -> rgl.getListeResultatsTousPilotes()
			            .stream()
			            .anyMatch(rpl -> rpl.getPilote().getId().equals(id) && rpl.getPlace().equals(1)))
		  .count();

    }
}
