package com.boki.springcloudgateway.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class GlobalFilter : AbstractGatewayFilterFactory<GlobalFilter.Config>(Config::class.java) {
    data class Config(
        var baseMessage: String? = null,
        var preLogger: Boolean = false,
        var postLogger: Boolean = false
    )

    companion object {
        private val log = LoggerFactory.getLogger(GlobalFilter::class.java)
    }

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request = exchange.request
            val response = exchange.response

            log.info("Global Filter baseMessage: {}", config.baseMessage)
            if (config.preLogger) {
                log.info("Global Filter Start: request id -> {}", request.id)
            }

            chain.filter(exchange)
                .then(Mono.fromRunnable<Void> {
                    if (config.postLogger) {
                        log.info("Global Filter End: response code -> {}", response.statusCode)
                    }
                })
        }
    }
}