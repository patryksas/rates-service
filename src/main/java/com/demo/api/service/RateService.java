package com.demo.api.service;

import com.demo.api.dto.RateDto;
import com.demo.api.dto.RateHistoryDto;
import com.demo.api.mapper.DtoToEntityMapper;
import com.demo.api.mapper.EntityToDtoMapper;
import com.demo.model.dao.RateDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.NoArgsConstructor;

@RequestScoped
@NoArgsConstructor
public class RateService {

    private DtoToEntityMapper dtoToEntityMapper;
    private EntityToDtoMapper entityToDtoMapper;
    private RateDao rateDao;
    private ObjectMapper objectMapper;
    private WebTarget target;

    @Inject
    public RateService(DtoToEntityMapper dtoToEntityMapper, EntityToDtoMapper entityToDtoMapper, RateDao rateDao) {
        this.dtoToEntityMapper = dtoToEntityMapper;
        this.entityToDtoMapper = entityToDtoMapper;
        this.rateDao = rateDao;
        this.objectMapper = new ObjectMapper();
    }

    @PostConstruct
    protected void init() throws NoSuchAlgorithmException {
        Client client = ClientBuilder.newBuilder().sslContext(SSLContext.getDefault()).build();
        target = client.target("https://api.frankfurter.app");
    }

    public RateDto getRate(String date) throws JsonProcessingException, RateException {
        Response response = target.path(date)
            .queryParam("to", "usd")
            .request(MediaType.APPLICATION_JSON)
            .get();
        if (response.getStatus() != 200) {
            throw new RateException("Failed to retrieve rate. Status code:" + response.getStatus());
        }
        RateDto rateDto = objectMapper.readValue(response.readEntity(String.class), RateDto.class);
        rateDao.save(dtoToEntityMapper.rateDtoToRate(rateDto));
        return rateDto;

    }

    public List<RateHistoryDto> getHistory() {
        return rateDao.getAll().stream()
            .map(rate -> entityToDtoMapper.rateToRateHistoryDto(rate))
            .collect(Collectors.toList());
    }

    public static class RateException extends Exception {

        public RateException(String message) {
            super(message);
        }
    }
}
