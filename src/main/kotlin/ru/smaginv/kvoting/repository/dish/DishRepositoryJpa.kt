package ru.smaginv.kvoting.repository.dish

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.smaginv.kvoting.entity.Dish
import java.time.LocalDate

interface DishRepositoryJpa : JpaRepository<Dish, Long> {

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :restaurantId AND d.id = :dishId")
    fun get(@Param("restaurantId") restaurantId: Long, @Param("dishId") dishId: Long): Dish?

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :restaurantId AND d.date = :date")
    fun getAllByRestaurantOnDate(
        @Param("restaurantId") restaurantId: Long,
        @Param("date") date: LocalDate
    ): List<Dish>

    @Modifying
    @Query("DELETE FROM Dish d WHERE d.restaurant.id = :restaurantId AND d.id = :dishId")
    fun delete(@Param("restaurantId") restaurantId: Long, @Param("dishId") dishId: Long): Int
}