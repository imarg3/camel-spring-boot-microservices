package com.arpitram.microservices.camelmicroservicea.routes;

import com.arpitram.microservices.camelmicroservicea.beans.GetCurrentTimeBean;
import com.arpitram.microservices.camelmicroservicea.beans.SimpleLoggingProcessingComponent;
import com.arpitram.microservices.camelmicroservicea.beans.SimpleLoggingProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// @Component
public class TimerRouter extends RouteBuilder {
    @Autowired
    GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    SimpleLoggingProcessingComponent loggingProcessingComponent;

    @Override
    public void configure() throws Exception {
        from("timer:first-timer")
                .log("${body}")
                .transform().constant("Time now is : " + LocalDateTime.now())
                .log("${body}")
                .bean(getCurrentTimeBean, "getCurrentTime")
                .log("${body}")
                .bean(loggingProcessingComponent)
                .log("${body}")
                .process(new SimpleLoggingProcessor())
                .to("log:first-timer");
    }
}

