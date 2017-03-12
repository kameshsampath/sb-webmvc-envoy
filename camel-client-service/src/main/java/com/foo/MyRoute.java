package com.foo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Use Camel error handler to perform redelivery when calling the service fails
 */
@Component
public class MyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:foo?period=10000")
                .setHeader("Host", constant("ipaddress-service"))
                .to("log:headers?showAll=true")
                .to("http4://localhost:9001/?preserveHostHeader=true")
                .log("${body}");
    }
}



