package org.georges.effun.service;

import java.util.ArrayList;

import org.georges.effun.model.ResultatGeneral;
import org.georges.effun.repository.ResultatGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class ResultatGeneralService {
	
	@Autowired
	private ResultatGeneralRepository rgr;
	public ArrayList<ResultatGeneral> getAllResultatsGeneraux(){
		return Lists.newArrayList(rgr.findAll());
	}
}
