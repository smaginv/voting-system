package ru.smaginv.kvoting.web.controller

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import ru.smaginv.kvoting.service.user.UserService
import ru.smaginv.kvoting.web.dto.user.UserDto

@RestController
@RequestMapping(
    value = ["/admin/users"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class AdminController(
    val userService: UserService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/{userId}")
    fun get(@PathVariable userId: Long): ResponseEntity<UserDto> {
        logger.info("get user with id: {}", userId)
        return ResponseEntity.ok(userService.get(userId))
    }

    @GetMapping("/username")
    fun getByUsername(@RequestParam username: String): ResponseEntity<UserDto> {
        logger.info("get user by username: {}", username)
        return ResponseEntity.ok(userService.getByUsername(username))
    }

    @GetMapping("/email")
    fun getByEmail(@RequestParam email: String): ResponseEntity<UserDto> {
        logger.info("get user by email: {}", email)
        return ResponseEntity.ok(userService.getByEmail(email))
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<UserDto>> {
        logger.info("get all users")
        return ResponseEntity.ok(userService.getAll())
    }

    @PatchMapping("/{userId}")
    fun <T> update(@PathVariable userId: Long, @RequestBody @Valid userDto: UserDto): ResponseEntity<T> {
        logger.info("update user: {} with id: {}", userDto, userId)
        userService.update(userId, userDto)
        return ResponseEntity.noContent().build()
    }

    @PostMapping
    fun create(@RequestBody @Valid userDto: UserDto): ResponseEntity<UserDto> {
        logger.info("create user: {}", userDto)
        val created = userService.create(userDto)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{userId}")
            .buildAndExpand(created.id)
            .toUri()
        return ResponseEntity.created(location).body(created)
    }

    @DeleteMapping("/{userId}")
    fun <T> delete(@PathVariable userId: Long): ResponseEntity<T> {
        logger.info("delete user with id: {}", userId)
        userService.delete(userId)
        return ResponseEntity.noContent().build()
    }
}