package com.demo.api.service;

import com.demo.api.mapper.RateMapper;
import com.demo.dao.RateDao;
import com.demo.api.dto.RateDto;
import com.demo.model.Rate;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class RateService {

    @Inject
    private RateMapper mapper;
    @Inject
    private RateDao rateDao;
    private WebTarget target;

    @PostConstruct
    protected void init() throws NoSuchAlgorithmException {
        Client client = ClientBuilder.newBuilder().sslContext(SSLContext.getDefault()).build();
        target = client.target("https://api.frankfurter.app");
    }

    public RateDto getRate(String date) {
        RateDto rateDto = target.path(date)
            .queryParam("to", "usd")
            .request(MediaType.APPLICATION_JSON)
            .get(RateDto.class);
        Rate rate = mapper.rateDtoToRate(rateDto);
        rateDao.save(rate);
        return rateDto;

    }

    public List<Rate> getHistory() {
        return rateDao.getAll();
    }
}
