package ru.smaginv.kvoting.web.dto.restaurant

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import ru.smaginv.kvoting.web.dto.dish.DishDto
import java.io.Serializable

@JsonPropertyOrder("id", "title", "menu")
class RestaurantInfoDto(
    var id: Long,
    var title: String,
    var menu: List<DishDto>?
) : Serializable {
    override fun toString(): String {
        return "RestaurantInfoDto(id=$id, title='$title')"
    }
}