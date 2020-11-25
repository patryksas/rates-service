package com.demo.api;

import com.demo.api.service.RateService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("rate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RateController {

    @Inject
    private RateService rateService;

    @GET
    @Path("{date}")
    public Response getRate(@PathParam("date") String date) {
        return Response.ok(rateService.getRate(date).getRates().getUsd()).build();
    }

    @GET
    @Path("history")
    public Response getAll() {
        return Response.ok(rateService.getHistory()).build();
    }
}
