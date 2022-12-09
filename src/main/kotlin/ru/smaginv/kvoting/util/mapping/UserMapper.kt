package ru.smaginv.kvoting.util.mapping

import org.mapstruct.Mapper
import ru.smaginv.kvoting.entity.User
import ru.smaginv.kvoting.web.dto.user.UserDto

@Mapper(componentModel = "spring")
interface UserMapper {

    fun map(userDto: UserDto): User

    fun mapDto(user: User): UserDto
}