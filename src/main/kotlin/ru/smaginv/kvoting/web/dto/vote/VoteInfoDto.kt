package ru.smaginv.kvoting.web.dto.vote

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import ru.smaginv.kvoting.web.dto.restaurant.RestaurantDto
import ru.smaginv.kvoting.web.dto.user.UserDto
import java.time.LocalDateTime

@JsonPropertyOrder("id", "time", "user", "restaurant")
@JsonIgnoreProperties(value = ["id"], allowGetters = true)
class VoteInfoDto(
    var id: Long?,
    @NotNull
    @PastOrPresent
    var time: LocalDateTime,
    @NotNull
    var restaurant: RestaurantDto,
    @NotNull
    var user: UserDto
)