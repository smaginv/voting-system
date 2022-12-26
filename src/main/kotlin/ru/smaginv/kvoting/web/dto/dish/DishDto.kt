package ru.smaginv.kvoting.web.dto.dish

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.validation.constraints.*
import java.time.LocalDate

@JsonPropertyOrder("id", "title", "price", "date")
@JsonIgnoreProperties(value = ["id"], allowGetters = true)
class DishDto(
    var id: Long?,
    @field:NotBlank
    @field:Size(min = 2, max = 128)
    var title: String?,
    @field:NotNull
    @field:Positive
    var price: Int?,
    @field:PastOrPresent
    var date: LocalDate?
) {
    override fun toString(): String {
        return "DishDto(id=$id, title='$title', price=$price, date=$date)"
    }
}