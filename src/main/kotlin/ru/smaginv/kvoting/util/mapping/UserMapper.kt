package ru.smaginv.kvoting.util.mapping

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import ru.smaginv.kvoting.entity.User
import ru.smaginv.kvoting.web.dto.user.UserDto

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    fun map(userDto: UserDto): User

    fun mapDto(user: User): UserDto

    fun mapDtos(users: List<User>): List<UserDto>

    @Mapping(target = "roles", ignore = true)
    fun update(userDto: UserDto, @MappingTarget user: User)
}