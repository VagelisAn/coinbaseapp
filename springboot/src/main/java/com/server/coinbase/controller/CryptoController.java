package com.server.coinbase.controller;

import com.server.coinbase.service.external.CryptoService;
import com.server.coinbase.service.external.impl.CryptoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final CryptoService cryptoService;

    @GetMapping("/{endpoint}")
    public String getCoinbaseData(@PathVariable String endpoint, @RequestParam Map<String, String> params) {
        return cryptoService.getCoinbaseData(endpoint, params);
    }
}