package ru.smaginv.kvoting.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import ru.smaginv.kvoting.entity.Role
import ru.smaginv.kvoting.service.user.UserService
import ru.smaginv.kvoting.web.AuthUser

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val userService: UserService
) {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun userDetailsService() = UserDetailsService { username ->
        AuthUser(userService.getByUsername(username))
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests()
            .requestMatchers("/restaurants/{restaurantId}/menu/**").hasAnyRole(Role.ADMIN.name, Role.USER.name)
            .requestMatchers("/restaurants/**").hasRole(Role.ADMIN.name)
            .requestMatchers("/profile/register").anonymous()
            .requestMatchers("/profile").hasAnyRole(Role.ADMIN.name, Role.USER.name)
            .requestMatchers("/user/**").hasRole(Role.USER.name)
            .requestMatchers("/votes/**").hasRole(Role.ADMIN.name)
            .requestMatchers("/admin/**").hasRole(Role.ADMIN.name)
            .and().httpBasic()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().csrf().disable()
            .build()
    }
}