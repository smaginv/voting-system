package ru.smaginv.kvoting.service.user

import ru.smaginv.kvoting.web.dto.user.UserDto

interface UserService {

    fun get(userId: Long): UserDto

    fun getByUsername(username: String): UserDto

    fun getByEmail(email: String): UserDto

    fun getAll(): List<UserDto>

    fun update(userId: Long, userDto: UserDto)

    fun create(userDto: UserDto): UserDto

    fun delete(userId: Long)
}