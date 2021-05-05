package com.ramusthastudio.demo.teamcity;

import io.smallrye.mutiny.Multi;
import org.jboss.resteasy.reactive.RestSseElementType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.time.LocalDateTime;

@Path("/stream")
public class ExampleResource {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestSseElementType(MediaType.APPLICATION_JSON)
    public Multi<String> hello() {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(n -> String.format("Date %s", LocalDateTime.now()));
    }
}