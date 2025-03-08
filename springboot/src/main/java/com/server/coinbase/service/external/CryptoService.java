package com.server.coinbase.service.external;

import java.util.Map;

public interface CryptoService {
    String getCoinbaseData(String endpoint, Map<String, String> params);
}
