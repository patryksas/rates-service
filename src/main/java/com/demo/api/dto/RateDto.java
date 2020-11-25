package com.demo.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateDto {

    private int amount;
    private String base;
    private String date;
    private RatesDto rates;
}
