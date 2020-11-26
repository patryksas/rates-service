package com.demo.api.dto;

import lombok.Data;

@Data
public class RateDto {

    private int amount;
    private String base;
    private String date;
    private RatesDto rates;
}
