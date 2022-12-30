package ru.smaginv.kvoting.web.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import ru.smaginv.kvoting.entity.Role
import ru.smaginv.kvoting.service.user.UserService
import ru.smaginv.kvoting.web.dto.user.UserDto

@RestController
@RequestMapping(
    value = ["/admin/users"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Administrator controller")
class AdminController(
    val userService: UserService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by id")
    fun get(@PathVariable userId: Long): ResponseEntity<UserDto> {
        logger.info("get user with id: {}", userId)
        return ResponseEntity.ok(userService.get(userId))
    }

    @GetMapping("/username")
    @Operation(summary = "Get user by username")
    fun getByUsername(@RequestParam username: String): ResponseEntity<UserDto> {
        logger.info("get user by username: {}", username)
        return ResponseEntity.ok(userService.getByUsername(username))
    }

    @GetMapping("/email")
    @Operation(summary = "Get user by email")
    fun getByEmail(@RequestParam email: String): ResponseEntity<UserDto> {
        logger.info("get user by email: {}", email)
        return ResponseEntity.ok(userService.getByEmail(email))
    }

    @GetMapping
    @Operation(summary = "Get all users")
    fun getAll(): ResponseEntity<List<UserDto>> {
        logger.info("get all users")
        return ResponseEntity.ok(userService.getAll())
    }

    @PatchMapping("/{userId}")
    @Operation(summary = "Set the role to the user")
    fun setRole(@PathVariable userId: Long, @RequestParam role: Role): ResponseEntity<UserDto> {
        logger.info("set user role: {} with id: {}", role, userId)
        val user = userService.setRole(userId, role)
        return ResponseEntity.ok(user)
    }

    @PostMapping
    @Operation(summary = "Create user")
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
    @Operation(summary = "Delete user by id")
    fun <T> delete(@PathVariable userId: Long): ResponseEntity<T> {
        logger.info("delete user with id: {}", userId)
        userService.delete(userId)
        return ResponseEntity.noContent().build()
    }
}