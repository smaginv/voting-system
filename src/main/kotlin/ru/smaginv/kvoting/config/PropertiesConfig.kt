package ru.smaginv.kvoting.config

import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PropertiesConfig {

    @Bean
    @ConfigurationProperties(prefix = "format")
    fun format(): Format = Format()

    class Format {
        @NotBlank
        lateinit var date: String

        @NotBlank
        lateinit var dateTime: String
    }
}