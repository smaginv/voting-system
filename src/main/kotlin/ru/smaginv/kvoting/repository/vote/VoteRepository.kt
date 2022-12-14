package ru.smaginv.kvoting.repository.vote

import ru.smaginv.kvoting.entity.Vote
import java.time.LocalDate

interface VoteRepository {

    fun get(voteId: Long): Vote

    fun getByUserOnDate(userId: Long, date: LocalDate): Vote

    fun getAllOnDate(date: LocalDate): List<Vote>

    fun save(vote: Vote): Vote

    fun delete(voteId: Long)
}