package ru.smaginv.kvoting.web.dto.dish

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.validation.constraints.*
import java.time.LocalDate

@JsonPropertyOrder("id", "title", "price", "date")
@JsonIgnoreProperties(value = ["id"], allowGetters = true)
class DishDto(
    var id: Long?,
    @NotBlank
    @Size(min = 2, max = 128)
    var title: String,
    @NotNull
    @Positive
    var price: Int,
    @PastOrPresent
    var date: LocalDate
)