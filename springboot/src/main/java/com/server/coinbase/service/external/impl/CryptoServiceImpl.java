package com.server.coinbase.service.external.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.coinbase.dto.CryptoResponseDTO;
import com.server.coinbase.service.external.CryptoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CryptoServiceImpl implements CryptoService {

    public static final String SPOT = "/spot";
    @Value("${crypto.coin.base.url}")
    private String apiUrlCoinBase;

    @Value("${crypto.coin.gecko.url}")
    private String apiUrlGecko;

    private final WebClient webClient;

    public CryptoServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Cacheable(value = "coinbaseData", key = "#endpoint + #params.toString()")
    public String getCoinbaseData(String endpoint, Map<String, String> params) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrlCoinBase + endpoint+ SPOT);

        params.forEach((key, value) -> {
            if (value != null && !value.isEmpty()) {
                uriBuilder.queryParam(key, value);
            }
        });

        URI uri = uriBuilder.build().toUri();

        log.info("Request URL: " + uri);

        // Perform the request
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public List<CryptoResponseDTO> getCoinGeckoData(Map<String, String> params) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrlGecko);

        params.forEach((key, value) -> {
            if (value != null && !value.isEmpty()) {
                uriBuilder.queryParam(key, value);
            }
        });

        log.info("params: " + params);

        URI uri = uriBuilder.build().toUri();

        log.info("Request URL: " + uri);
        String response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("response: " + response);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, CryptoResponseDTO.class));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
