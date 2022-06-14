package org.georges.effun.model;

import java.util.List;

import lombok.Data;

@Data
public class EcurieDetail {
    private Ecurie ecurie;
    List<Pilote> listePilotes;
    int points;
}
