package com.server.coinbase.service.external.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.server.coinbase.service.external.CryptoNewsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptoNewsServiceImpl implements CryptoNewsService {

    public static final String CURRENCIES_PARAM = "&currencies";
    @Value("${crypto.panic.url}")
    private String url;

    @Value("${crypto.panic.key}")
    private String apiKey;

    private final Cache<String, String> cache;
    private final RestTemplate restTemplate = new RestTemplate();

    private final String API_URL = url + apiKey + CURRENCIES_PARAM + "=BTC";

    public CryptoNewsServiceImpl(Cache<String, String> cache) {
        this.cache = cache;
    }

    public String getCryptoNews() {
        return cache.get("cryptoNews", key -> fetchCryptoNews());
    }

    private String fetchCryptoNews() {
        return restTemplate.getForObject(API_URL, String.class);
    }
}
