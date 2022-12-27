package ru.smaginv.kvoting.config

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import java.time.LocalTime

@Configuration
class PropertiesConfig {

    @Bean
    @ConfigurationProperties(prefix = "format")
    fun format(): Format = Format()

    @Bean
    @ConfigurationProperties(prefix = "voting")
    fun voting(): Voting = Voting()

    @Validated
    class Format {
        @field:NotBlank
        lateinit var date: String

        @field:NotBlank
        lateinit var dateTime: String
    }

    @Validated
    class Voting {
        @field:NotNull
        lateinit var endVoteTime: LocalTime
    }
}