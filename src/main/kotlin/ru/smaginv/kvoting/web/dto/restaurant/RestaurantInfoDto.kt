package ru.smaginv.kvoting.web.dto.restaurant

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import ru.smaginv.kvoting.web.dto.dish.DishDto

@JsonPropertyOrder("id", "title", "menu")
class RestaurantInfoDto(
    var id: Long,
    var title: String,
    var menu: List<DishDto>?
) {
    override fun toString(): String {
        return "RestaurantInfoDto(id=$id, title='$title')"
    }
}