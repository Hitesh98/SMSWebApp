package com.flipkart.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld {
    @GET
    @Path("/msg")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessage() {
        return "Hello world!";
    }
}