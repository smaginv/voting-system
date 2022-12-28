package ru.smaginv.kvoting.web.controller

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import ru.smaginv.kvoting.service.user.UserService
import ru.smaginv.kvoting.web.AuthUser
import ru.smaginv.kvoting.web.dto.user.UserDto

@RestController
@RequestMapping(
    value = ["/profile"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class UserController(
    val userService: UserService,
    val passwordEncoder: PasswordEncoder
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun get(@AuthenticationPrincipal authUser: AuthUser): ResponseEntity<UserDto> {
        logger.info("get user with username: {}", authUser.username)
        return ResponseEntity.ok(userService.getByUsername(authUser.username))
    }

    @PatchMapping
    fun <T> update(
        @AuthenticationPrincipal authUser: AuthUser,
        @RequestBody @Valid userDto: UserDto
    ): ResponseEntity<T> {
        logger.info("update user with username: {}", authUser.username)
        userDto.apply { password = passwordEncoder.encode(password) }
        userService.update(authUser.id, userDto)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/register")
    fun register(@RequestBody @Valid userDto: UserDto): ResponseEntity<UserDto> {
        logger.info("register new user with username: {}", userDto.username)
        userDto.apply { password = passwordEncoder.encode(password) }
        val user = userService.create(userDto)
        val location = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/profile")
            .build()
            .toUri()
        return ResponseEntity.created(location).body(user)
    }

    @DeleteMapping
    fun <T> delete(@AuthenticationPrincipal authUser: AuthUser): ResponseEntity<T> {
        logger.info("delete user with username: {}", authUser.username)
        userService.delete(authUser.id)
        return ResponseEntity.noContent().build()
    }
}