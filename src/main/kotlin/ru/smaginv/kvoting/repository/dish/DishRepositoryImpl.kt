package ru.smaginv.kvoting.repository.dish

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import ru.smaginv.kvoting.entity.Dish
import ru.smaginv.kvoting.util.checkNotFound
import java.time.LocalDate

@Repository
class DishRepositoryImpl(
    @Autowired val dishRepository: DishRepositoryJpa
) : DishRepository {
    override fun get(restaurantId: Long, dishId: Long): Dish {
        return checkNotFound(dishRepository.get(restaurantId, dishId), dishId)
    }

    override fun getAllByRestaurantOnDate(restaurantId: Long, date: LocalDate): List<Dish> {
        return dishRepository.getAllByRestaurantOnDate(restaurantId, date)
    }

    override fun save(dish: Dish): Dish {
        return dishRepository.save(dish)
    }

    override fun delete(restaurantId: Long, dishId: Long) {
        checkNotFound(dishRepository.delete(restaurantId, dishId), dishId)
    }
}