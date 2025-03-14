package com.server.coinbase.service.external.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.coinbase.dto.CryptoNewsDTO;
import com.server.coinbase.dto.CryptoNewsResponseDTO;
import com.server.coinbase.dto.CurrencyDTO;
import com.server.coinbase.service.external.CryptoNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
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
    public CryptoNewsResponseDTO getCryptoNews(Map<String, String> params) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("auth_token", apiToken);

        params.forEach((key, value) -> {
            if (value != null && !value.isEmpty()) {
                uriBuilder.queryParam(key, value);
            }
        });

        URI uri = uriBuilder.build().toUri();

        System.out.println("Request URL: " + uri);

        String response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("response {}" , response);

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(response, CryptoNewsResponseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

