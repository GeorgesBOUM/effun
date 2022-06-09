package org.georges.effun.service;

import java.util.List;
import java.util.Optional;

import org.georges.effun.model.GrandPrix;
import org.georges.effun.repository.GrandPrixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class GrandPrixService {

    @Autowired
    private GrandPrixRepository rgr;

    public List<GrandPrix> getAllResultatsGeneraux() {
	return Lists.newArrayList(rgr.findAll());
    }

    public Optional<GrandPrix> getGrandPrixParAnnee(int annee) {
	return this.getAllResultatsGeneraux()
		   .stream()
		   .filter(rgl -> rgl.getDate().getYear() == annee)
		   .findFirst();
    }

}
