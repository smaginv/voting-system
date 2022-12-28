package ru.smaginv.kvoting.service.restaurant

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.smaginv.kvoting.entity.Restaurant
import ru.smaginv.kvoting.repository.restaurant.RestaurantRepository
import ru.smaginv.kvoting.service.dish.DishService
import ru.smaginv.kvoting.util.mapping.RestaurantMapper
import ru.smaginv.kvoting.web.dto.restaurant.RestaurantDto
import ru.smaginv.kvoting.web.dto.restaurant.RestaurantInfoDto
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class RestaurantServiceImpl(
    val restaurantRepository: RestaurantRepository,
    val restaurantMapper: RestaurantMapper,
    val dishService: DishService
) : RestaurantService {

    override fun get(restaurantId: Long): RestaurantDto {
        return restaurantMapper.mapDto(getRestaurant(restaurantId))
    }

    @Cacheable(
        value = ["restaurant"],
        key = "#restaurantId + '_today'"
    )
    override fun getWithTodayMenu(restaurantId: Long): RestaurantInfoDto {
        val restaurantInfoDto = restaurantMapper.mapInfoDto(restaurantRepository.get(restaurantId))
        restaurantInfoDto.menu = dishService.getAllByRestaurantToday(restaurantId)
        return restaurantInfoDto
    }

    override fun getWithOnDateMenu(restaurantId: Long, date: LocalDate): RestaurantInfoDto {
        val restaurantInfoDto = restaurantMapper.mapInfoDto(getRestaurant(restaurantId))
        restaurantInfoDto.menu = dishService.getAllByRestaurantOnDate(restaurantId, date)
        return restaurantInfoDto
    }

    @Cacheable(
        value = ["restaurant"],
        key = "'all'"
    )
    override fun getAll(): List<RestaurantDto> {
        return restaurantMapper.mapDtos(restaurantRepository.getAll())
    }

    @Caching(
        evict = [
            CacheEvict(value = ["restaurant"], key = "#restaurantId + '_today'"),
            CacheEvict(value = ["restaurant"], key = "'all'")
        ]
    )
    @Transactional
    override fun update(restaurantId: Long, restaurantDto: RestaurantDto) {
        val updated = getRestaurant(restaurantId)
        restaurantMapper.update(restaurantDto, updated)
        restaurantRepository.save(updated)
    }

    @Caching(
        evict = [
            CacheEvict(value = ["restaurant"], key = "'all'")
        ]
    )
    @Transactional
    override fun create(restaurantDto: RestaurantDto): RestaurantDto {
        val restaurant = restaurantMapper.map(restaurantDto)
        return restaurantMapper.mapDto(restaurantRepository.save(restaurant))
    }

    @Caching(
        evict = [
            CacheEvict(value = ["restaurant"], key = "#restaurantId + '_today'"),
            CacheEvict(value = ["restaurant"], key = "'all'")
        ]
    )
    @Transactional
    override fun delete(restaurantId: Long) {
        restaurantRepository.delete(restaurantId)
    }

    private fun getRestaurant(restaurantId: Long): Restaurant {
        return restaurantRepository.get(restaurantId)
    }
}