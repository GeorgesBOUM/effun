package org.georges.effun.service;

import java.util.ArrayList;
import java.util.Optional;

import org.georges.effun.model.Pilote;
import org.georges.effun.repository.PiloteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class PiloteService {

	@Autowired
	private PiloteRepository pr;

	public ArrayList<Pilote> getAllPilotes() {
		return Lists.newArrayList(pr.findAll());
	}

	public Pilote ajoutPilote(Pilote p) {
		return pr.save(p);
	}

	public Optional<Pilote> getPiloteById(final Integer id) {
		return pr.findById(id);
	}
}
