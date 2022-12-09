package ru.smaginv.kvoting.web.dto.vote

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import java.time.LocalDateTime

@JsonPropertyOrder("id, time")
@JsonIgnoreProperties(value = ["id"], allowGetters = true)
class VoteDto(
    var id: Long,
    @NotNull
    @PastOrPresent
    var time: LocalDateTime
)