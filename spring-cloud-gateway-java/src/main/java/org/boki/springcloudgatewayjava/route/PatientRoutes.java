package org.boki.springcloudgatewayjava.route;

import org.boki.springcloudgatewayjava.config.CustomGatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PatientRoutes {

    private final CustomGatewayProperties gatewayProperties;

    public PatientRoutes(CustomGatewayProperties gatewayProperties) {
        this.gatewayProperties = gatewayProperties;
    }

    @Bean
    public RouteLocator patientRouteLocator(RouteLocatorBuilder builder) {
        String baseURL = gatewayProperties.getBaseURL();
        String apiKey = gatewayProperties.getApiKey();

        return builder.routes()
                // path: /patients
                .route(
                        "patient_route",
                        r -> r.path("/patients")
                                .filters(f -> f.rewritePath("/patients", "/rest/v1/api/patients")
                                        .addRequestHeader("apikey", apiKey))
                                .uri(baseURL))

                // path: /patients/{id}
                .route(
                        "patient_id_route",
                        r -> r.path("/patients/**")
                                .filters(f -> f.rewritePath("/patients/(?<segment>.*)", "/rest/v1/api/patients/${segment}")
                                        .addRequestHeader("apikey", apiKey))
                                .uri(baseURL))

                .build();
    }
}
