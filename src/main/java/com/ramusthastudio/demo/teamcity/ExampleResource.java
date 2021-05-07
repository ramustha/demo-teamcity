package com.ramusthastudio.demo.teamcity;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestSseElementType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.InetAddress;
import java.time.Duration;
import java.time.LocalDateTime;

@Path("/api")
public class ExampleResource {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestSseElementType(MediaType.APPLICATION_JSON)
    @Path("/stream")
    public Multi<String> stream() {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(n -> String.format("Date %s", LocalDateTime.now()));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/data")
    public Uni<String> data() {
        return Uni.createFrom().item(() -> {
            try {
                return String.format("Data from %s", InetAddress.getLocalHost().getHostName());
            } catch (Exception e) {
                return String.format("Data from %s", System.getenv());
            }
        });
    }
}