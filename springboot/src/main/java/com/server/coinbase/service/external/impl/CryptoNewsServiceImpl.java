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
        String response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Blocking call to get the result synchronously

        log.info("response {}" , response);
        String jsonPayload = null;
        CryptoNewsResponseDTO cryptoNewsResponseDTO = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            cryptoNewsResponseDTO = objectMapper.readValue(response, CryptoNewsResponseDTO.class);

            jsonPayload = objectMapper.writeValueAsString(cryptoNewsResponseDTO);

            log.info("jsonPayload {}" , jsonPayload);

            List<CryptoNewsDTO> newsList = cryptoNewsResponseDTO.getResults();
            for (CryptoNewsDTO news : newsList) {
                System.out.println("Title: " + news.getTitle());
                System.out.println("Published At: " + news.getPublished_at());
                System.out.println("URL: " + news.getUrl());
                if (news.getCurrencies() != null) {
                    for (CurrencyDTO currencyDTO : news.getCurrencies()) {
                        System.out.println("Currency: " + currencyDTO.getTitle() + " (Code: " + currencyDTO.getCode() + ")");
                        System.out.println("Currency URL: " + currencyDTO.getUrl());
                    }
                }

                System.out.println("=========================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cryptoNewsResponseDTO;
    }
}

