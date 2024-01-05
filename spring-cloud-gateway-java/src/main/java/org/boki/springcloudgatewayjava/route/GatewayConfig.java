//package org.boki.springcloudgatewayjava.route;
//
//import org.boki.springcloudgatewayjava.config.CustomGatewayProperties;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GatewayConfig {
//
//    //    final String SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ewogICAgInJvbGUiOiAiYW5vbiIsCiAgICAiaXNzIjogInN1cGFiYXNlIiwKICAgICJpYXQiOiAxNjkwOTAyMDAwLAogICAgImV4cCI6IDE4NDg3NTQ4MDAKfQ.Ntrfqioi7UjzDZ6oX86p5JnNvm-pOM6qFKR8aLT0zU0";
////        final String SUPABASE_BASE_URL = "http://supabase-supabase-kong.supabase.svc.cluster.local:8000";
////        final String REWRITE_PATH = "/rest/v1";
//
//    private final String baseURL;
//    private final String apiKey;
//
//    public GatewayConfig(CustomGatewayProperties gatewayProperties) {
//        this.baseURL = gatewayProperties.getBaseURL();
//        this.apiKey = gatewayProperties.getApiKey();
//    }
//
//    @Bean
//    public RouteLocator patientRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                // path: /patients
//                .route(
//                        "patient_route",
//                        r -> r.path("/patients")
//                                .filters(f -> f.rewritePath("/patients", "/rest/v1/api/patients")
//                                        .addRequestHeader("apikey", apiKey))
//                                .uri(baseURL))
//
//                // path: /patients/{id}
//                .route(
//                        "patient_id_route",
//                        r -> r.path("/patients/**")
//                                .filters(f -> f.rewritePath("/patients/(?<segment>.*)", "/rest/v1/api/patients/${segment}")
//                                        .addRequestHeader("apikey", apiKey))
//                                .uri(baseURL))
//
//                .build();
//    }
//
//    @Bean
//    public RouteLocator deviceRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                // path: /device
//                .route(
//                        "device_route",
//                        r -> r.path("/devices")
//                                .filters(f -> f.rewritePath("/devices", "/rest/v1/api/devices")
//                                        .addRequestHeader("apikey", apiKey))
//                                .uri(baseURL))
//
//                // path: /devices/{id}
//                .route(
//                        "devices_id_route",
//                        r -> r.path("/devices/**")
//                                .filters(f -> f.rewritePath("/devices/(?<segment>.*)", "/rest/v1/api/devices/${segment}")
//                                        .addRequestHeader("apikey", apiKey))
//                                .uri(baseURL))
//
//                .build();
//    }
//}
