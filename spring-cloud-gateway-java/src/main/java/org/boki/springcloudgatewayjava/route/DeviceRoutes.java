package org.boki.springcloudgatewayjava.route;

import org.boki.springcloudgatewayjava.config.CustomGatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceRoutes {
    private final CustomGatewayProperties gatewayProperties;

    public DeviceRoutes(CustomGatewayProperties gatewayProperties) {
        this.gatewayProperties = gatewayProperties;
    }

    @Bean
    public RouteLocator deviceRouteLocator(RouteLocatorBuilder builder) {
        String baseURL = gatewayProperties.getBaseURL();
        String apiKey = gatewayProperties.getApiKey();

        return builder.routes()
                // path: /device
                .route(
                        "device_route",
                        r -> r.path("/devices")
                                .filters(f -> f.rewritePath("/devices", "/rest/v1/api/devices")
                                        .addRequestHeader("apikey", apiKey))
                                .uri(baseURL))

                // path: /devices/{id}
                .route(
                        "devices_id_route",
                        r -> r.path("/devices/**")
                                .filters(f -> f.rewritePath("/devices/(?<segment>.*)", "/rest/v1/api/devices/${segment}")
                                        .addRequestHeader("apikey", apiKey))
                                .uri(baseURL))

                .build();
    }
}
