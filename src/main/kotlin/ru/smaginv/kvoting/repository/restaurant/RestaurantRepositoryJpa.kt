package ru.smaginv.kvoting.repository.restaurant

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.smaginv.kvoting.entity.Restaurant

interface RestaurantRepositoryJpa : JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE r.id = :restaurantId")
    fun get(@Param("restaurantId") restaurantId: Long): Restaurant?

    @Query("SELECT r FROM Restaurant r ORDER BY r.id")
    fun getAll(): List<Restaurant>

    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id = :restaurantId")
    fun delete(@Param("restaurantId") restaurantId: Long): Int
}