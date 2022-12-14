package ru.smaginv.kvoting.service.dish

import ru.smaginv.kvoting.web.dto.dish.DishDto
import java.time.LocalDate

interface DishService {

    fun get(restaurantId: Long, dishId: Long): DishDto

    fun getAllByRestaurantToday(restaurantId: Long): List<DishDto>

    fun getAllByRestaurantOnDate(restaurantId: Long, date: LocalDate): List<DishDto>

    fun update(restaurantId: Long, dishId: Long, dishDto: DishDto)

    fun create(restaurantId: Long, dishDto: DishDto): DishDto

    fun delete(restaurantId: Long, dishId: Long)
}