package com.server.coinbase.controller;

import com.server.coinbase.service.external.CryptoService;
import com.server.coinbase.service.external.impl.CryptoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final CryptoService cryptoService;

    @GetMapping("/{currency}")
    public ResponseEntity<String> getPrice(@PathVariable String currency) {
        return ResponseEntity.ok(cryptoService.getCryptoPrice(currency));
    }
}