package org.georges.effun.model;

import lombok.Data;

@Data
public class PiloteDetail {
    private Pilote pilote;
    private int points;
    private int place;
    private Long nombreVictoires;
}