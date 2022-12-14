package ru.smaginv.kvoting.util.mapping

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import ru.smaginv.kvoting.entity.Dish
import ru.smaginv.kvoting.web.dto.dish.DishDto

@Mapper(componentModel = "spring")
interface DishMapper {

    @Mapping(target = "restaurant", ignore = true)
    fun map(dishDto: DishDto): Dish

    fun mapDto(dish: Dish): DishDto

    fun mapDtos(dishes: List<Dish>): List<DishDto>

    @Mapping(target = "restaurant", ignore = true)
    fun update(dishDto: DishDto, @MappingTarget dish: Dish)
}