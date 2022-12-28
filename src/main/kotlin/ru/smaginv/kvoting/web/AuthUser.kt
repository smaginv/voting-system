package ru.smaginv.kvoting.web

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import ru.smaginv.kvoting.util.exception.UnauthorizedException
import ru.smaginv.kvoting.web.dto.user.UserDto

class AuthUser(userDto: UserDto) : User(
    userDto.username,
    userDto.password,
    userDto.roles?.let {
        it.asSequence()
            .map { role ->
                SimpleGrantedAuthority("ROLE_$role")
            }
            .toSet()
    }
) {
    val id: Long

    init {
        this.id = userDto.id ?: throw UnauthorizedException("Unauthorized user")
    }
}