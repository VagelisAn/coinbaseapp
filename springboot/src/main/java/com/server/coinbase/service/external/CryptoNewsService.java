package com.server.coinbase.service.external;

import java.util.Map;

public interface CryptoNewsService {
    String getCryptoNews(Map<String, String> params);
}
