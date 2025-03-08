package com.server.coinbase.service.external.impl;

import com.server.coinbase.service.external.CryptoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Service
public class CryptoServiceImpl implements CryptoService {

    @Value("${crypto.coin.base.url}")
    private String apiUrl;

    private final WebClient webClient;

    public CryptoServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Cacheable(value = "coinbaseData", key = "#endpoint + #params.toString()")
    public String getCoinbaseData(String endpoint, Map<String, String> params) {
        // Build the dynamic URL with query parameters
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl + endpoint);

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
