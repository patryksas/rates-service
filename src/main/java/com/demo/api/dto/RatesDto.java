package com.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RatesDto {

    @JsonProperty("USD")
    public double usd;
}
