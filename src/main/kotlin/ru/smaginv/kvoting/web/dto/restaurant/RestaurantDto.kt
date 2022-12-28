package ru.smaginv.kvoting.web.dto.restaurant

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.io.Serializable

@JsonPropertyOrder("id", "title")
@JsonIgnoreProperties(value = ["id"], allowGetters = true)
class RestaurantDto(
    var id: Long?,
    @field:NotBlank
    @field:Size(min = 2, max = 256)
    var title: String?
) : Serializable {
    override fun toString(): String {
        return "RestaurantDto(id=$id, title='$title')"
    }
}