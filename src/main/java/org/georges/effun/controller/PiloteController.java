package org.georges.effun.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.georges.effun.model.Pilote;
import org.georges.effun.model.PiloteDetail;
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
    @Autowired
    private GrandPrixController gpc;

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

    @GetMapping("/pilotes/{piloteId}/victoire")
    public Long getNombreVictoiresPilote(@PathVariable Integer id) {
	return rgs.getAllResultatsGeneraux()
		  .stream()
		  .filter(rgl -> rgl.getListeResultatsTousPilotes()
			            .stream()
			            .anyMatch(rpl -> rpl.getPilote().getId().equals(id) && rpl.getPlace().equals(1)))
		  .count();

    }
    
    @GetMapping("/pilotes/{piloteId}/details")
    public Optional<PiloteDetail> getPiloteDetails(@PathVariable Integer id){
	/**
	 * On peut getPilote à partir de l'id
	 * Une fois qu'on a le pilote, on va dans la map et on récupère ses points
	 * On pourra ensuite compter les points en appelant la méthode getNombreVictoires
	 * */
	int annee = LocalDate.now().getYear();
	Map<Pilote, Integer> classementPilotes = gpc.getClassementPilotesParAnnee(annee);
	Pilote p = ps.getPiloteById(id).get();
	PiloteDetail pd = new PiloteDetail();
	int place;
	pd.setPilote(p);
	pd.setPoints(classementPilotes.get(p));
	place = rgs.getGrandPrixParAnnee(annee)
		   .get()
		   .getListeResultatsTousPilotes()
		   .stream()
		   .filter(rp -> rp.getPilote().equals(p))
		   .findAny()
		   .get()
		   .getPlace();
	pd.setPlace(place);
	pd.setNombreVictoires(this.getNombreVictoiresPilote(id));
	return Optional.ofNullable(pd);
    }
}
