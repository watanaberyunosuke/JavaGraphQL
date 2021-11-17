package com.example.javagraphql.model;

import lombok.Data;


/**
 * Repository Layer
 */

@Data
public class StockDetails {
    private String ticker;
    private String stockValue;
    private String volume;
    private String averageVolume;
    private String PE_Ratio;
    private String EPS;
    private String dividend;
    private String marketCap;
    private String previousOpen;
    private String previousClose;
}
