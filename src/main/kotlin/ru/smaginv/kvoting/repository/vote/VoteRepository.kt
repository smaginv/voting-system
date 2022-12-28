package ru.smaginv.kvoting.repository.vote

import ru.smaginv.kvoting.entity.Vote
import java.time.LocalDate

interface VoteRepository {

    fun get(userId: Long, voteId: Long): Vote

    fun getByUserToday(userId: Long): Vote?

    fun getByUserOnDate(userId: Long, date: LocalDate): Vote

    fun getAllByUser(userId: Long): List<Vote>

    fun getAllOnDate(date: LocalDate): List<Vote>

    fun save(vote: Vote): Vote

    fun delete(userId: Long)
}