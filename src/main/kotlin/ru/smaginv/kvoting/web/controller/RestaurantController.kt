package ru.smaginv.kvoting.web.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import ru.smaginv.kvoting.service.restaurant.RestaurantService
import ru.smaginv.kvoting.web.dto.restaurant.RestaurantDto
import ru.smaginv.kvoting.web.dto.restaurant.RestaurantInfoDto
import java.time.LocalDate

@RestController
@RequestMapping(
    value = ["/restaurants"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Restaurant Controller")
class RestaurantController(
    val restaurantService: RestaurantService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/{restaurantId}")
    @Operation(summary = "Get restaurant by id")
    fun get(@PathVariable restaurantId: Long): ResponseEntity<RestaurantDto> {
        logger.info("get restaurant with id: {}", restaurantId)
        return ResponseEntity.ok(restaurantService.get(restaurantId))
    }

    @GetMapping("/{restaurantId}/menu/today")
    @Operation(summary = "Get restaurant with menu")
    fun getWithTodayMenu(@PathVariable restaurantId: Long): ResponseEntity<RestaurantInfoDto> {
        logger.info("get today's restaurant menu with ID: {}", restaurantId)
        return ResponseEntity.ok(restaurantService.getWithTodayMenu(restaurantId))
    }

    @GetMapping("/{restaurantId}/menu/on-date")
    @Operation(summary = "Get restaurant with menu")
    fun getWithOnDateMenu(
        @PathVariable restaurantId: Long,
        @RequestParam date: LocalDate
    ): ResponseEntity<RestaurantInfoDto> {
        logger.info("get restaurant menu with id: {}, on date: {}", restaurantId, date)
        return ResponseEntity.ok(restaurantService.getWithOnDateMenu(restaurantId, date))
    }

    @GetMapping
    @Operation(summary = "Get all restaurants")
    fun getAll(): ResponseEntity<List<RestaurantDto>> {
        logger.info("get all restaurants")
        return ResponseEntity.ok(restaurantService.getAll())
    }

    @PatchMapping("/{restaurantId}")
    @Operation(summary = "Update restaurant")
    fun <T> update(
        @PathVariable restaurantId: Long,
        @RequestBody @Valid restaurantDto: RestaurantDto
    ): ResponseEntity<T> {
        logger.info("updating restaurant: {}, with id: {}", restaurantDto, restaurantId)
        restaurantService.update(restaurantId, restaurantDto)
        return ResponseEntity.noContent().build()
    }

    @PostMapping
    @Operation(summary = "Create restaurant")
    fun create(@RequestBody @Valid restaurantDto: RestaurantDto): ResponseEntity<RestaurantDto> {
        logger.info("create restaurant: {}", restaurantDto)
        val created = restaurantService.create(restaurantDto)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{restaurantId}")
            .buildAndExpand(created.id)
            .toUri()
        return ResponseEntity.created(location).body(created)
    }

    @DeleteMapping("/{restaurantId}")
    @Operation(summary = "Delete restaurant")
    fun <T> delete(@PathVariable restaurantId: Long): ResponseEntity<T> {
        logger.info("delete restaurant with id: {}", restaurantId)
        restaurantService.delete(restaurantId)
        return ResponseEntity.noContent().build()
    }
}