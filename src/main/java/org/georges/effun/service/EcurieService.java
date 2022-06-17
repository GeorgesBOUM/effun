package org.georges.effun.service;

import java.util.ArrayList;
import java.util.Optional;

import org.georges.effun.model.Ecurie;
import org.georges.effun.repository.EcurieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class EcurieService {
    @Autowired
    EcurieRepository er;
    
    public ArrayList<Ecurie> getAllEcuries() {
	return Lists.newArrayList(er.findAll());
    }
    
    public Ecurie ajoutEcurie(Ecurie e) {
	return er.save(e);
    }
    
    public Optional<Ecurie> getEcurieById(Long id) {
	return er.findById(id);
    }
}
