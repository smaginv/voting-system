package ru.smaginv.kvoting.repository.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.smaginv.kvoting.entity.User

interface UserRepositoryJpa : JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :userId")
    fun get(@Param("userId") userId: Long): User?

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = :username")
    fun getByUsername(@Param("username") username: String): User?

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = :email")
    fun getByEmail(@Param("email") email: String): User?

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.id")
    fun getAll(): List<User>

    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :userId")
    fun delete(@Param("userId") userId: Long): Int
}