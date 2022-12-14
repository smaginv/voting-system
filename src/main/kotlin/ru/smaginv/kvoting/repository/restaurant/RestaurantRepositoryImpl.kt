package ru.smaginv.kvoting.repository.restaurant

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import ru.smaginv.kvoting.entity.Restaurant
import ru.smaginv.kvoting.util.checkNotFound

@Repository
class RestaurantRepositoryImpl(
    @Autowired val restaurantRepository: RestaurantRepositoryJpa
) : RestaurantRepository {
    override fun get(restaurantId: Long): Restaurant {
        return checkNotFound(restaurantRepository.get(restaurantId), restaurantId)
    }

    override fun getReferenceById(restaurantId: Long): Restaurant {
        return checkNotFound(restaurantRepository.getReferenceById(restaurantId), restaurantId)
    }

    override fun getAll(): List<Restaurant> {
        return restaurantRepository.getAll()
    }

    override fun save(restaurant: Restaurant): Restaurant {
        return restaurantRepository.save(restaurant)
    }

    override fun delete(restaurantId: Long) {
        checkNotFound(restaurantRepository.delete(restaurantId), restaurantId)
    }
}