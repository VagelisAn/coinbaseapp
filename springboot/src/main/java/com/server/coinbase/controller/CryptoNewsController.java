package com.server.coinbase.controller;

import com.server.coinbase.dto.CryptoNewsResponseDTO;
import com.server.coinbase.service.external.CryptoNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class CryptoNewsController {

    private final CryptoNewsService newsService;

    @GetMapping
    public CryptoNewsResponseDTO getNews(@RequestParam Map<String, String> params) {
        return newsService.getCryptoNews(params);
    }
}