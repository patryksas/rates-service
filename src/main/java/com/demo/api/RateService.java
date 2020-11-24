package com.demo.api;

import com.demo.dto.RateDto;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class RateService {

    private WebTarget target;

    @PostConstruct
    protected void init() throws NoSuchAlgorithmException {
        Client client = ClientBuilder.newBuilder().sslContext(SSLContext.getDefault()).build();
        target = client.target("https://api.frankfurter.app");
    }

    public RateDto getRate(String date) {
        return target.path(date)
            .queryParam("to", "usd")
            .request(MediaType.APPLICATION_JSON)
            .get(RateDto.class);
    }
}
