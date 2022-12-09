package ru.smaginv.kvoting.repository.dish

import ru.smaginv.kvoting.entity.Dish
import java.time.LocalDate

interface DishRepository {

    fun get(restaurantId: Long, dishId: Long): Dish

    fun getAllByRestaurantOnDate(restaurantId: Long, date: LocalDate): List<Dish>

    fun save(dish: Dish): Dish

    fun delete(restaurantId: Long, dishId: Long): Int
}