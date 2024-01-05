package org.boki.springcloudgatewayjava.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedHeader("*");

        return builder.routes()
                .route(
                        "patient_route",
                        r -> r.path("/patients/**")
                                .filters(f -> f.rewritePath("/patients/(?<segment>.*)", "/rest/v1/api/patients/${segment}")
                                        .addRequestHeader("apikey", "boki"))
//                                .uri("http://supabase-supabase-kong.supabase.svc.cluster.local:8000/rest/v1"))
                                .uri("http://localhost:9091"))
                .build();
    }
}
