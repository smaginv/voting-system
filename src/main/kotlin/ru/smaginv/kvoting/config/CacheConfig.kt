package ru.smaginv.kvoting.config

import org.ehcache.config.builders.CacheConfigurationBuilder
import org.ehcache.config.builders.ExpiryPolicyBuilder
import org.ehcache.config.builders.ResourcePoolsBuilder
import org.ehcache.config.units.MemoryUnit
import org.ehcache.jsr107.Eh107Configuration
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration
import javax.cache.CacheManager
import javax.cache.Caching
import javax.cache.spi.CachingProvider

@Configuration
@EnableCaching
class CacheConfig(
    propertiesConfig: PropertiesConfig
) {

    private val entries = propertiesConfig.cache().entries
    private val heapSize = propertiesConfig.cache().heapSize
    private val duration = propertiesConfig.cache().duration
    private val values = propertiesConfig.cache().values

    @Bean
    fun ehCacheManager(): CacheManager {
        val cachingProvider: CachingProvider = Caching.getCachingProvider()
        val cacheManager = cachingProvider.cacheManager
        val cacheConfiguration = CacheConfigurationBuilder
            .newCacheConfigurationBuilder(
                String::class.java,
                Any::class.java,
                ResourcePoolsBuilder.heap(entries).offheap(heapSize, MemoryUnit.MB)
            )
            .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(duration)))
            .build()
        values.forEach { cache ->
            cacheManager.createCache(cache, Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfiguration))
        }
        return cacheManager
    }
}