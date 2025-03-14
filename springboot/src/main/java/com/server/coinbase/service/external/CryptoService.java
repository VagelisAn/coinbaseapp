package com.server.coinbase.service.external;

import com.server.coinbase.dto.CryptoResponseDTO;

import java.util.List;
import java.util.Map;

public interface CryptoService {
    String getCoinbaseData(String endpoint, Map<String, String> params);

    List<CryptoResponseDTO> getCoinGeckoData(Map<String, String> params);
}
