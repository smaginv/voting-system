package ru.smaginv.kvoting.util.mapping

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import ru.smaginv.kvoting.entity.Restaurant
import ru.smaginv.kvoting.web.dto.restaurant.RestaurantDto
import ru.smaginv.kvoting.web.dto.restaurant.RestaurantInfoDto

@Mapper(componentModel = "spring")
interface RestaurantMapper {

    fun map(restaurantDto: RestaurantDto): Restaurant

    fun map(restaurantInfoDto: RestaurantInfoDto): Restaurant

    fun mapDto(restaurant: Restaurant): RestaurantDto

    @Mapping(target = "menu", ignore = true)
    fun mapInfoDto(restaurant: Restaurant): RestaurantInfoDto

    fun mapDtos(restaurants: List<Restaurant>): List<RestaurantDto>

    fun update(restaurantDto: RestaurantDto, @MappingTarget restaurant: Restaurant)
}