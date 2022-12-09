package ru.smaginv.kvoting.repository.restaurant

import ru.smaginv.kvoting.entity.Restaurant

interface RestaurantRepository {

    fun get(restaurantId: Long): Restaurant

    fun getAll(): List<Restaurant>

    fun save(restaurant: Restaurant): Restaurant

    fun delete(restaurantId: Long): Int
}