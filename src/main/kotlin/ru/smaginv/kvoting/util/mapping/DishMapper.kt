package ru.smaginv.kvoting.util.mapping

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.smaginv.kvoting.entity.Dish
import ru.smaginv.kvoting.web.dto.dish.DishDto

@Mapper(componentModel = "spring")
interface DishMapper {

    @Mapping(target = "restaurant", ignore = true)
    fun map(dishDto: DishDto): Dish

    fun mapDto(dish: Dish): DishDto
}