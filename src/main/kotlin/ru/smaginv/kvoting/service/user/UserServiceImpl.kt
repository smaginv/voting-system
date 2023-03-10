package ru.smaginv.kvoting.service.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.smaginv.kvoting.entity.Role
import ru.smaginv.kvoting.entity.User
import ru.smaginv.kvoting.repository.user.UserRepository
import ru.smaginv.kvoting.util.mapping.UserMapper
import ru.smaginv.kvoting.web.dto.user.UserDto

@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    val userRepository: UserRepository,
    val userMapper: UserMapper
) : UserService {

    override fun get(userId: Long): UserDto {
        return userMapper.mapDto(getUser(userId))
    }

    override fun getByUsername(username: String): UserDto {
        return userMapper.mapDto(userRepository.getByUsername(username))
    }

    override fun getByEmail(email: String): UserDto {
        return userMapper.mapDto(userRepository.getByEmail(email))
    }

    override fun getAll(): List<UserDto> {
        return userMapper.mapDtos(userRepository.getAll())
    }

    @Transactional
    override fun update(userId: Long, userDto: UserDto) {
        val user = getUser(userId)
        userMapper.update(userDto, user)
        userRepository.save(user)
    }

    @Transactional
    override fun setRole(userId: Long, role: Role): UserDto {
        val user = getUser(userId).apply {
            roles?.let { roles ->
                if (roles.contains(role))
                    throw IllegalArgumentException("user already has a role: ${role.name}")
                else
                    roles.add(role)
            }
        }
        return userMapper.mapDto(userRepository.save(user))
    }

    @Transactional
    override fun create(userDto: UserDto): UserDto {
        val user = userMapper.map(userDto)
        user.roles = mutableSetOf(Role.USER)
        return userMapper.mapDto(userRepository.save(user))
    }

    @Transactional
    override fun delete(userId: Long) {
        userRepository.delete(userId)
    }

    private fun getUser(userId: Long): User {
        return userRepository.get(userId)
    }
}