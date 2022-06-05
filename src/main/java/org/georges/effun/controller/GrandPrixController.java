package org.georges.effun.controller;

import org.georges.effun.service.GrandPrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrandPrixController {

    @Autowired
    private GrandPrixService rgs;

    @GetMapping("/standings/pilotes/{annee}")
    public void getClassementPilotesParAnnee(@PathVariable int annee) {
	/**
	 * Le type de retour ne sera évidemment pas void. C'est pour éviter les erreurs syntaxiques
	 * 
	 * Je veux le classement des pilotes par années => une sorted list ?
	 * Il faut calculer les points par pilote (distribués selon la place du pilote)
	 * Plus un point bonus pour le meilleur tour si dans les 10 premiers
	 * Si même nombre de points, avantage à la meilleure place
	 * Si même nombre de points et même place, avantage aux plus de victoires
	 * Chaque pilote devrait donc embarquer un compteur de point
	 * => Il faut donc un résultat général par pilote et par année
	 */
	
	rgs.getResultatGeneralParAnnee(annee)
	   .get() // peut être catcher le NoSuchElementException ?
	   .getListeResultatsTousPilotes()
	   .stream(); // stream en chantier
    }    
    
}
