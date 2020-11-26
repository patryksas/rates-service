package com.demo.api.dto;

import java.util.Date;
import lombok.Data;

@Data
public class RateHistoryDto {

    private String rateValue;
    private String dateOfRate;
    private Date created;

}
