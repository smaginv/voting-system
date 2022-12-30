package ru.smaginv.kvoting.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@SecurityScheme(
    name = "basicAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "basic"
)
@OpenAPIDefinition(
    info = Info(
        title = "REST API documentation",
        version = "1.0",
        description = "Voting System"
    ),
    security = [SecurityRequirement(name = "basicAuth")]
)
class OpenApiConfig {

    @Bean
    fun adminApi(): GroupedOpenApi {
        val includedPaths = arrayOf("/restaurants/**", "/profile", "/votes/**", "/admin/**")
        val excludedPaths = arrayOf("/user/**")
        return GroupedOpenApi
            .builder()
            .displayName("Administrator")
            .group("admin")
            .pathsToMatch(*includedPaths)
            .pathsToExclude(*excludedPaths)
            .build()
    }

    @Bean
    fun userApi(): GroupedOpenApi {
        val includedPaths = arrayOf("/restaurants/{restaurantId}/menu/**", "/profile", "/user/**")
        val excludedPaths = arrayOf("/profile/register")
        return GroupedOpenApi
            .builder()
            .displayName("User")
            .group("user")
            .pathsToMatch(*includedPaths)
            .pathsToExclude(*excludedPaths)
            .build()
    }

    @Bean
    fun anonymousApi(): GroupedOpenApi {
        return GroupedOpenApi
            .builder()
            .displayName("Unregistered")
            .group("unregistered")
            .pathsToMatch("/profile/register")
            .build()
    }
}