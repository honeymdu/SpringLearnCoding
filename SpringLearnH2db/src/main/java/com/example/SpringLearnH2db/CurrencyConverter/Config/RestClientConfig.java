package com.example.SpringLearnH2db.CurrencyConverter.Config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${freecurrencyapi.base.url}")
    private String baseUrl;

    @Value("${freecurrencyapi.api.key}")
    private String API_KEY;

    @Bean
    @Qualifier("freecurrencyapiRestClient")
    RestClient createRestClinet() {
        return RestClient.builder()
                .baseUrl(baseUrl+"?apikey="+API_KEY)
                .defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res) -> {
					throw new RuntimeException("Server error occurred");
				})
                .build();

    }
    
}
