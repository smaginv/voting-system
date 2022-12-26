package ru.smaginv.kvoting.repository.vote

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.smaginv.kvoting.entity.Vote
import java.time.LocalDate

interface VoteRepositoryJpa : JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.restaurant WHERE v.id = :voteId AND v.user.id = :userId")
    fun get(@Param("userId") userId: Long, @Param("voteId") voteId: Long): Vote?

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.restaurant WHERE v.user.id = :userId AND v.date = :date")
    fun getByUserOnDate(@Param("userId") userId: Long, @Param("date") date: LocalDate): Vote?

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.restaurant WHERE v.user.id = :userId")
    fun getAllByUser(@Param("userId") userId: Long): List<Vote>

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.restaurant WHERE v.date = :date")
    fun getAllOnDate(@Param("date") date: LocalDate): List<Vote>

    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id = :voteId AND v.user.id = :userId")
    fun delete(@Param("userId") userId: Long, @Param("voteId") voteId: Long): Int
}