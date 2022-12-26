package ru.smaginv.kvoting.service.vote

import ru.smaginv.kvoting.web.dto.vote.VoteInfoDto
import java.time.LocalDate

interface VoteService {

    fun get(userId: Long, voteId: Long): VoteInfoDto

    fun getByUserToday(userId: Long): VoteInfoDto

    fun getByUserOnDate(userId: Long, date: LocalDate): VoteInfoDto

    fun getAllByUser(userId: Long): List<VoteInfoDto>

    fun getAllToday(): List<VoteInfoDto>

    fun getAllOnDate(date: LocalDate): List<VoteInfoDto>

    fun update(userId: Long, restaurantId: Long, voteId: Long)

    fun create(userId: Long, restaurantId: Long): VoteInfoDto

    fun delete(userId: Long, voteId: Long)
}