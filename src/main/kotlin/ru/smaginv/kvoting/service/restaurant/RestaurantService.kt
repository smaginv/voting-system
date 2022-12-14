package ru.smaginv.kvoting.service.restaurant

import ru.smaginv.kvoting.web.dto.restaurant.RestaurantDto
import ru.smaginv.kvoting.web.dto.restaurant.RestaurantInfoDto
import java.time.LocalDate

interface RestaurantService {

    fun get(restaurantId: Long): RestaurantDto

    fun getWithTodayMenu(restaurantId: Long): RestaurantInfoDto

    fun getWithOnDateMenu(restaurantId: Long, date: LocalDate): RestaurantInfoDto

    fun getAll(): List<RestaurantDto>

    fun update(restaurantId: Long, restaurantDto: RestaurantDto)

    fun create(restaurantDto: RestaurantDto): RestaurantDto

    fun delete(restaurantId: Long)
}