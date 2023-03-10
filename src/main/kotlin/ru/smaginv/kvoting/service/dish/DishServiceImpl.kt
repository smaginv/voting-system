package ru.smaginv.kvoting.service.dish

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.smaginv.kvoting.entity.Dish
import ru.smaginv.kvoting.repository.dish.DishRepository
import ru.smaginv.kvoting.repository.restaurant.RestaurantRepository
import ru.smaginv.kvoting.util.mapping.DishMapper
import ru.smaginv.kvoting.web.dto.dish.DishDto
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class DishServiceImpl(
    val dishRepository: DishRepository,
    val restaurantRepository: RestaurantRepository,
    val dishMapper: DishMapper
) : DishService {

    override fun get(restaurantId: Long, dishId: Long): DishDto {
        return dishMapper.mapDto(getDish(restaurantId, dishId))
    }

    override fun getAllByRestaurantToday(restaurantId: Long): List<DishDto> {
        return dishMapper.mapDtos(dishRepository.getAllByRestaurantOnDate(restaurantId, LocalDate.now()))
    }

    override fun getAllByRestaurantOnDate(restaurantId: Long, date: LocalDate): List<DishDto> {
        return dishMapper.mapDtos(dishRepository.getAllByRestaurantOnDate(restaurantId, date))
    }

    @Caching(
        evict = [
            CacheEvict(value = ["restaurant"], key = "#restaurantId + '_today'")
        ]
    )
    @Transactional
    override fun update(restaurantId: Long, dishId: Long, dishDto: DishDto) {
        val updated = getDish(restaurantId, dishId)
        dishMapper.update(dishDto, updated)
        dishRepository.save(updated)
    }

    @Caching(
        evict = [
            CacheEvict(value = ["restaurant"], key = "#restaurantId + '_today'")
        ]
    )
    @Transactional
    override fun create(restaurantId: Long, dishDto: DishDto): DishDto {
        val restaurant = restaurantRepository.getReferenceById(restaurantId)
        val dish = dishMapper.map(dishDto)
        dish.restaurant = restaurant
        return dishMapper.mapDto(dishRepository.save(dish))
    }

    @Caching(
        evict = [
            CacheEvict(value = ["restaurant"], key = "#restaurantId + '_today'")
        ]
    )
    @Transactional
    override fun delete(restaurantId: Long, dishId: Long) {
        dishRepository.delete(restaurantId, dishId)
    }

    private fun getDish(restaurantId: Long, dishId: Long): Dish {
        return dishRepository.get(restaurantId, dishId)
    }
}