package com.ramusthastudio.demo.teamcity;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.SecureRandom;
import java.time.Duration;

@Path("/hello")
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<Integer> hello() {
        return Uni.createFrom()
                .item(() -> new SecureRandom().nextInt(1000))
                .onItem()
                .delayIt()
                .by(Duration.ofSeconds(2));
    }
}