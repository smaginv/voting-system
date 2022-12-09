package ru.smaginv.kvoting.web.dto.restaurant

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import ru.smaginv.kvoting.web.dto.dish.DishDto

@JsonPropertyOrder("id", "title", "menu")
@JsonIgnoreProperties(value = ["id"], allowGetters = true)
class RestaurantInfoDto(
    var id: Long?,
    @NotBlank
    @Size(min = 2, max = 256)
    var title: String,
    @NotBlank
    var menu: List<DishDto>
)