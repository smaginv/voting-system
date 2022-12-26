package ru.smaginv.kvoting.web.controller

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import ru.smaginv.kvoting.service.dish.DishService
import ru.smaginv.kvoting.web.dto.dish.DishDto
import java.time.LocalDate

@RestController
@RequestMapping(
    value = ["/restaurants/{restaurantId}"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class DishController(
    val dishService: DishService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/dishes/{dishId}")
    fun get(@PathVariable restaurantId: Long, @PathVariable dishId: Long): ResponseEntity<DishDto> {
        logger.info("get dish with id: {}", dishId)
        return ResponseEntity.ok(dishService.get(restaurantId, dishId))
    }

    @GetMapping("/menu/today")
    fun getAllByRestaurantToday(@PathVariable restaurantId: Long): ResponseEntity<List<DishDto>> {
        logger.info("get today's restaurant menu with id: {}", restaurantId)
        return ResponseEntity.ok(dishService.getAllByRestaurantToday(restaurantId))
    }

    @GetMapping("/menu/on-date")
    fun getAllByRestaurantOnDate(
        @PathVariable restaurantId: Long,
        @RequestParam date: LocalDate
    ): ResponseEntity<List<DishDto>> {
        logger.info("get the restaurant menu on the date {}, with id: {}", date, restaurantId)
        return ResponseEntity.ok(dishService.getAllByRestaurantOnDate(restaurantId, date))
    }

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
    fun <T> delete(@PathVariable restaurantId: Long, @PathVariable dishId: Long): ResponseEntity<T> {
        logger.info("delete dish with id: {}, for restaurant with id: {}", dishId, restaurantId)
        dishService.delete(restaurantId, dishId)
        return ResponseEntity.noContent().build()
    }
}