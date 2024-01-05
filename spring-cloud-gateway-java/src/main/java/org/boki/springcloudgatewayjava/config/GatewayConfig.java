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

        final String baseURL = "http://localhost:9091";
        final String apiKey = "boki";
//        final String SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ewogICAgInJvbGUiOiAiYW5vbiIsCiAgICAiaXNzIjogInN1cGFiYXNlIiwKICAgICJpYXQiOiAxNjkwOTAyMDAwLAogICAgImV4cCI6IDE4NDg3NTQ4MDAKfQ.Ntrfqioi7UjzDZ6oX86p5JnNvm-pOM6qFKR8aLT0zU0";
//        final String SUPABASE_BASE_URL = "http://supabase-supabase-kong.supabase.svc.cluster.local:8000";
//        final String REWRITE_PATH = "/rest/v1";

        return builder.routes()
                // path: /patients
                .route(
                        "patient_route",
                        r -> r.path("/patients")
                                .filters(f -> f.rewritePath("/patients", "/rest/v1/api/patients")
                                        .addRequestHeader("apikey", apiKey))
//                                .uri("http://supabase-supabase-kong.supabase.svc.cluster.local:8000/rest/v1"))
                                .uri(baseURL))

                // path: /patients/{id}
                .route(
                        "patient_id_route",
                        r -> r.path("/patients/**")
                                .filters(f -> f.rewritePath("/patients/(?<segment>.*)", "/rest/v1/api/patients/${segment}")
                                        .addRequestHeader("apikey", apiKey))
//                                .uri("http://supabase-supabase-kong.supabase.svc.cluster.local:8000/rest/v1"))
                                .uri(baseURL))

                .build();
    }
}
