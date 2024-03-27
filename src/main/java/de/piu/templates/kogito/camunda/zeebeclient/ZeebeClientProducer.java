package de.piu.templates.kogito.camunda.zeebeclient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.camunda.zeebe.client.ZeebeClient;

@ApplicationScoped
public class ZeebeClientProducer {

    @Inject
    @ConfigProperty(name ="zeebe.client.gatewayAddress")
    private String zebeeGateway;

    @Produces
    @Singleton
    public ZeebeClient createZeebeClient() {
        return ZeebeClient.newClientBuilder()
                .gatewayAddress(zebeeGateway)
                .usePlaintext()
                .build();
    }
}