package ru.smaginv.kvoting.web.dto.vote

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.io.Serializable
import java.time.LocalDate

@JsonPropertyOrder("id", "date")
@JsonIgnoreProperties(value = ["id", "date"], allowGetters = true)
class VoteDto(
    var id: Long?,
    var date: LocalDate
) : Serializable {
    override fun toString(): String {
        return "VoteDto(id=$id, date=$date)"
    }
}