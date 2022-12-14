package ru.smaginv.kvoting.service.vote

import ru.smaginv.kvoting.web.dto.vote.VoteDto
import ru.smaginv.kvoting.web.dto.vote.VoteInfoDto
import java.time.LocalDate

interface VoteService {

    fun get(voteId: Long): VoteInfoDto

    fun getByUserToday(userId: Long): VoteInfoDto

    fun getByUserOnDate(userId: Long, date: LocalDate): VoteInfoDto

    fun getAllToday(): List<VoteInfoDto>

    fun getAllOnDate(date: LocalDate): List<VoteInfoDto>

    fun update(restaurantId: Long, voteId: Long, voteDto: VoteDto)

    fun create(restaurantId: Long, voteDto: VoteDto): VoteDto

    fun delete(voteId: Long)
}