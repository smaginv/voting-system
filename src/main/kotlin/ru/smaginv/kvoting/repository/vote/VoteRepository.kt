package ru.smaginv.kvoting.repository.vote

import ru.smaginv.kvoting.entity.Vote
import java.time.LocalDate

interface VoteRepository {

    fun get(userId: Long, voteId: Long): Vote

    fun getAllOnDate(date: LocalDate): List<Vote>

    fun save(vote: Vote): Vote

    fun delete(userId: Long, voteId: Long): Int
}