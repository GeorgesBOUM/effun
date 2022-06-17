package org.georges.effun.controller;

import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.georges.effun.model.Ecurie;
import org.georges.effun.model.EcurieDetail;
import org.georges.effun.model.GrandPrix;
import org.georges.effun.model.Pilote;
import org.georges.effun.model.ResultatPilote;
import org.georges.effun.service.EcurieService;
import org.georges.effun.service.GrandPrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcurieController {
    
    @Autowired
    EcurieService es;
    @Autowired
    GrandPrixService gps;
    @Autowired
    GrandPrixController gpc;
    @Autowired
    PiloteController pc;
    
    @GetMapping("/ecuries")
    public ArrayList<Ecurie> getAllEcuries() {
	return es.getAllEcuries();
    }
    
    @PostMapping("/ecuries")
    public ResponseEntity<Long> ajoutEcurie(Ecurie e) {
	Ecurie ecurie = es.ajoutEcurie(e);
	return new ResponseEntity<Long>(ecurie.getId(), HttpStatus.OK);
    }
    
    @GetMapping("/ecuries/{ecurieId}")
    public Optional<Ecurie> getEcurie(@PathVariable Long id) {
	return es.getEcurieById(id);
    }
    
    @GetMapping("/ecuries/{ecurieId}/details")
    public Optional<EcurieDetail> getEcurieDetail(@PathVariable Long id){
	/**
	 * On récupère l'écurie à partir de l'id (ecurieRecherche)
	 * On peut getGrandPrixParAnnée => on obtient un  grandPrix (dont une liste de ResultatPilote)
	 * On get listeResultatPilote
	 * Pour chaque élément de la liste obtenue:
	 * 	si pilote.getEcurie.equals(ecurieRecherche)
	 * 		On ajoute le pilote à la liste des pilotes de l'écurie
	 * 		On ajoute les points du pilote aux points de l'écurie
	 * 		==> Donc dans EcurieDetail il faudra une addPoints (int points)
	 * 		On ajoute le nombre de victoire du pilote aux victoires de l'écurie
	 * 		==> Donc dans EcurieDetail il faudra une addVictoires (int victoires)
	 * On pourra ensuite compter les points en appelant la méthode getNombreVictoires
	 * */
	EcurieDetail ed = new EcurieDetail();
	Ecurie ecurieRecherche = this.getEcurie(id).get();
	int annee = LocalDate.now().getYear();
	Map<Pilote, Integer> classementPilotes = gpc.getClassementPilotesParAnnee(annee);
	List<ResultatPilote> listeResultatPilotes = gps.getGrandPrixParAnnee(annee)
						       .get()
						       .getListeResultatsTousPilotes();
	
	listeResultatPilotes.forEach(rp -> {
	    					if (rp.getPilote().getEcurie().equals(ecurieRecherche)) {
						    ed.getListePilotes().add(rp.getPilote());
						    ed.ajoutPoints(classementPilotes.get(rp.getPilote()));
						    ed.ajoutVictoires(pc.getNombreVictoiresPilote(rp.getPilote().getId()));
						}
					});
	return Optional.ofNullable(ed);
    }
}
