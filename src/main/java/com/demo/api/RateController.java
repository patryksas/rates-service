package com.demo.api;

import com.demo.api.service.RateService;
import com.demo.api.service.RateService.RateException;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.NoArgsConstructor;

@RequestScoped
@Path("rate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@NoArgsConstructor
public class RateController {

    private RateService rateService;

    @Inject
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GET
    @Path("{date}")
    public Response getRate(@PathParam("date") String date) {
        try {
            return Response.ok(rateService.getRate(date).getRates().getUsd()).build();
        } catch (JsonProcessingException | RateException e) {
           return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("history")
    public Response getAll() {
        return Response.ok(rateService.getHistory()).build();
    }
}
