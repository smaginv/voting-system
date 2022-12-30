package ru.smaginv.kvoting.web.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import ru.smaginv.kvoting.service.dish.DishService
import ru.smaginv.kvoting.web.dto.dish.DishDto

@RestController
@RequestMapping(
    value = ["/restaurants/{restaurantId}"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Dish Controller")
class DishController(
    val dishService: DishService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/dishes/{dishId}")
    @Operation(summary = "Get dish by id")
    fun get(@PathVariable restaurantId: Long, @PathVariable dishId: Long): ResponseEntity<DishDto> {
        logger.info("get dish with id: {}", dishId)
        return ResponseEntity.ok(dishService.get(restaurantId, dishId))
    }

    @Operation(summary = "Update dish")
    @PatchMapping("/dishes/{dishId}")
    fun <T> update(
        @PathVariable restaurantId: Long,
        @PathVariable dishId: Long,
        @RequestBody @Valid dishDto: DishDto
    ): ResponseEntity<T> {
        logger.info("updating dish: {}, with id: {}", dishDto, dishId)
        dishService.update(restaurantId, dishId, dishDto)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/dishes")
    @Operation(summary = "Create dish")
    fun create(@PathVariable restaurantId: Long, @RequestBody @Valid dishDto: DishDto): ResponseEntity<DishDto> {
        logger.info("create dish: {}, for restaurant with id: {}", dishDto, restaurantId)
        val created = dishService.create(restaurantId, dishDto)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{dishId}")
            .buildAndExpand(created.id)
            .toUri()
        return ResponseEntity.created(location).body(created)
    }

    @DeleteMapping("/dishes/{dishId}")
    @Operation(summary = "Delete dish")
    fun <T> delete(@PathVariable restaurantId: Long, @PathVariable dishId: Long): ResponseEntity<T> {
        logger.info("delete dish with id: {}, for restaurant with id: {}", dishId, restaurantId)
        dishService.delete(restaurantId, dishId)
        return ResponseEntity.noContent().build()
    }
}