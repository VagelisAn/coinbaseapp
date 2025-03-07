package com.server.coinbase.controller;

import com.server.coinbase.service.external.CryptoNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class CryptoNewsController {

    private final CryptoNewsService newsService;

    @GetMapping
    public ResponseEntity<String> getNews(@RequestParam(defaultValue = "bitcoin") String keyword) {
        String news = newsService.getCryptoNews();

        // Filtering Bitcoin News
        if (news.contains(keyword)) {
            return ResponseEntity.ok(news);
        }
        return ResponseEntity.noContent().build();
    }
}