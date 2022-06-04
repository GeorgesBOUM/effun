package org.georges.effun.utils;

import java.util.Comparator;

import org.georges.effun.model.ResultatPilote;

public class ComparaisonMeilleurTemps implements Comparator<ResultatPilote> {

    @Override
    public int compare(ResultatPilote o1, ResultatPilote o2) {
	return o1.getMeilleurTemps().compareTo(o2.getMeilleurTemps());
    }

}
