package com.server.coinbase.config.caching;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.metrics.cache.CacheMetricsRegistrar;
import org.springframework.boot.actuate.metrics.cache.CacheMeterBinderProvider;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CacheMetricsConfig {

    @Bean
    public CacheMetricsRegistrar cacheMetricsRegistrar(MeterRegistry meterRegistry, List<CacheMeterBinderProvider<?>> cacheMeterBinderProviders) {
        return new CacheMetricsRegistrar(meterRegistry, cacheMeterBinderProviders);
    }
}