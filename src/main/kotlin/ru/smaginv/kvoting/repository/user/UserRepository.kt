package ru.smaginv.kvoting.repository.user

import ru.smaginv.kvoting.entity.User

interface UserRepository {

    fun get(userId: Long): User

    fun getByUsername(username: String): User

    fun getByEmail(email: String): User

    fun getAll(): List<User>

    fun save(user: User): User

    fun delete(userId: Long)
}