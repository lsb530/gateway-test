package org.boki.springcloudgatewayjava.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

@Component
public class UUIDFilter implements GatewayFilter {

    private static final Pattern UUID_PATTERN =
            Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String lastSegment = UriComponentsBuilder.fromUri(exchange.getRequest().getURI()).build().getPathSegments().stream()
                .reduce((first, second) -> second)
                .orElse("");

        if (!UUID_PATTERN.matcher(lastSegment).matches()) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid UUID format"));
        }

        return chain.filter(exchange);
    }
}
