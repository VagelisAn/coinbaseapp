package com.server.coinbase.service.external.impl;

import com.server.coinbase.service.external.CryptoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptoServiceImpl implements CryptoService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable("cryptoPrices")
    public String getCryptoPrice(String currency) {
        String url = "https://api.coinbase.com/v2/prices/" + currency + "-USD/spot";
        return restTemplate.getForObject(url, String.class);
    }
}
