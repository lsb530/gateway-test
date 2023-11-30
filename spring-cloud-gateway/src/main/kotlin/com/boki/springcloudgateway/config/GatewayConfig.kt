package com.boki.springcloudgateway.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayConfig {

    @Bean
    fun routes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route("angular-route") {
                it.header("FE-APP-NAME", "angular")
                    .and()
                    .path("/**")
                    .uri("http://localhost:9091")
            }
            .route("react-route") {
                it.header("FE-APP-NAME", "react")
                    .and()
                    .path("/**")
                    .uri("http://localhost:9092")
            }
            // 여기에 다른 라우트를 추가할 수 있습니다.
            .build()
    }
}
