package org.boki.springcloudgatewayjava.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(-2) // WebFlux의 기본 오류 핸들러보다 우선 순위가 높도록 설정
public class CustomGlobalErrorHandler implements WebExceptionHandler {

    private final ObjectMapper objectMapper;

    public CustomGlobalErrorHandler() {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public Mono<Void> handle(@NonNull ServerWebExchange exchange, @NonNull Throwable ex) {
        if (ex instanceof ResponseStatusException) {
            ResponseStatusException e = (ResponseStatusException) ex;
            Map<String, Object> errorAttributes = new HashMap<>();
            errorAttributes.put("timestamp", LocalDateTime.now());
            errorAttributes.put("path", exchange.getRequest().getPath().toString());
            errorAttributes.put("status", e.getStatusCode().value());
            errorAttributes.put("error", e.getReason());

            exchange.getResponse().setStatusCode(e.getStatusCode());
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            try {
                return exchange.getResponse().writeWith(
                        Mono.just(exchange.getResponse().bufferFactory().wrap(objectMapper.writeValueAsBytes(errorAttributes)))
                );
            } catch (JsonProcessingException jsonException) {
                return Mono.error(jsonException);
            }

        }
        // 다른 종류의 예외에 대한 처리
        return Mono.error(ex);
    }
}
