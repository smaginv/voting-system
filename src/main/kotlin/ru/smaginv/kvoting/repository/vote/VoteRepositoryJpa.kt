package ru.smaginv.kvoting.repository.vote

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.smaginv.kvoting.entity.Vote
import java.time.LocalDateTime

interface VoteRepositoryJpa : JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v WHERE v.id = :voteId")
    fun get(@Param("voteId") voteId: Long): Vote?

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId AND v.timeStamp >= :startOfDay AND v.timeStamp < :endOfDay")
    fun getByUserOnDate(
        @Param("userId") userId: Long,
        @Param("startOfDay") startOfDay: LocalDateTime,
        @Param("endOfDay") endOfDay: LocalDateTime
    ): Vote?

    @Query("SELECT v FROM Vote v WHERE v.timeStamp >= :startOfDay AND v.timeStamp < :endOfDay")
    fun getAllOnDate(
        @Param("startOfDay") startOfDay: LocalDateTime,
        @Param("endOfDay") endOfDay: LocalDateTime
    ): List<Vote>

    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id = :voteId")
    fun delete(@Param("voteId") voteId: Long): Int
}