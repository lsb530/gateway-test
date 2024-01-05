package org.boki.springcloudgatewayjava.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "boki.gateway.api")
public class CustomGatewayProperties {
    private String baseURL;
    private String apiKey;

    public String getBaseURL() {
        return baseURL;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}

