package ru.smaginv.kvoting.web.dto.vote

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import ru.smaginv.kvoting.web.dto.restaurant.RestaurantDto
import java.time.LocalDate

@JsonPropertyOrder("id", "date", "restaurant")
@JsonIgnoreProperties(value = ["id", "date", "restaurant"], allowGetters = true)
class VoteInfoDto(
    var id: Long,
    var date: LocalDate,
    var restaurant: RestaurantDto
) {
    override fun toString(): String {
        return "VoteInfoDto(id=$id, date=$date, restaurant=$restaurant)"
    }
}