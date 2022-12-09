package ru.smaginv.kvoting.repository.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import ru.smaginv.kvoting.entity.User
import ru.smaginv.kvoting.util.checkNotFound

@Repository
class UserRepositoryImpl(
    @Autowired val userRepository: UserRepositoryJpa
) : UserRepository {
    override fun get(userId: Long): User {
        return checkNotFound(userRepository.get(userId), userId)
    }

    override fun getByUsername(username: String): User {
        return checkNotFound(userRepository.getByUsername(username), username)
    }

    override fun getByEmail(email: String): User {
        return checkNotFound(userRepository.getByEmail(email), email)
    }

    override fun getAll(): List<User> {
        return userRepository.getAll()
    }

    override fun save(user: User): User {
        return userRepository.save(user)
    }

    override fun delete(userId: Long): Int {
        return userRepository.delete(userId)
    }
}