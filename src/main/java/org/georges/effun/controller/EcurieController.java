package org.georges.effun.controller;

import java.util.ArrayList;

import org.georges.effun.model.Ecurie;
import org.georges.effun.service.EcurieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcurieController {
    
    @Autowired
    EcurieService es;
    
    @GetMapping("/ecuries")
    public ArrayList<Ecurie> getAllEcuries() {
	return es.getAllEcuries();
    }
    
    @PostMapping("/ecuries")
    public ResponseEntity<Long> ajoutEcurie(Ecurie e) {
	Ecurie ecurie = es.ajoutEcurie(e);
	return new ResponseEntity<Long>(ecurie.getId(), HttpStatus.OK);
    }
}
