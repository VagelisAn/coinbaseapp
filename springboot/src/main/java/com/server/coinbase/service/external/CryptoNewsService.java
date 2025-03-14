package com.server.coinbase.service.external;

import com.server.coinbase.dto.CryptoNewsResponseDTO;

import java.util.Map;

public interface CryptoNewsService {
    CryptoNewsResponseDTO getCryptoNews(Map<String, String> params);
}
