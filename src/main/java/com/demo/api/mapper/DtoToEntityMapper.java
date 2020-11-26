package com.demo.api.mapper;

import com.demo.api.dto.RateDto;
import com.demo.model.entity.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DtoToEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(source = "date", target = "dateOfRate")
    @Mapping(source = "rates.usd", target = "rateValue")
    Rate rateDtoToRate(RateDto rateDto);
}
