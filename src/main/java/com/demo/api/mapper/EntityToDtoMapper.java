package com.demo.api.mapper;

import com.demo.api.dto.RateHistoryDto;
import com.demo.model.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityToDtoMapper {

    RateHistoryDto rateToRateHistoryDto(Rate rate);
}
