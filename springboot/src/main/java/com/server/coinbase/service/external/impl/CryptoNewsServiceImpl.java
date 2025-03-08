package com.server.coinbase.service.external.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.server.coinbase.service.external.CryptoNewsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Service
public class CryptoNewsServiceImpl implements CryptoNewsService {

    @Value("${crypto.panic.url}")
    private String apiUrl;

    @Value("${crypto.panic.key}")
    private String apiToken;

    private final WebClient webClient;

    public CryptoNewsServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Cacheable(value = "cryptoNews", key = "#params.toString()")
    public String getCryptoNews(Map<String, String> params) {
        // Build the dynamic URL with query parameters
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("auth_token", apiToken);

        params.forEach((key, value) -> {
            if (value != null && !value.isEmpty()) {
                uriBuilder.queryParam(key, value);
            }
        });

        URI uri = uriBuilder.build().toUri();

        System.out.println("Request URL: " + uri);

        // Perform the request
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Blocking call to get the result synchronously
    }
}

