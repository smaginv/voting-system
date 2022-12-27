package ru.smaginv.kvoting.repository.vote

import org.springframework.stereotype.Repository
import ru.smaginv.kvoting.entity.Vote
import ru.smaginv.kvoting.util.checkNotFound
import java.time.LocalDate

@Repository
class VoteRepositoryImpl(
    val voteRepository: VoteRepositoryJpa
) : VoteRepository {
    override fun get(userId: Long, voteId: Long): Vote {
        return checkNotFound(voteRepository.get(userId, voteId), voteId)
    }

    override fun getByUserToday(userId: Long): Vote? {
        return voteRepository.getByUserOnDate(userId, LocalDate.now())
    }

    override fun getByUserOnDate(userId: Long, date: LocalDate): Vote {
        return checkNotFound(voteRepository.getByUserOnDate(userId, date), date)
    }

    override fun getAllByUser(userId: Long): List<Vote> {
        return voteRepository.getAllByUser(userId)
    }

    override fun getAllOnDate(date: LocalDate): List<Vote> {
        return voteRepository.getAllOnDate(date)
    }

    override fun save(vote: Vote): Vote {
        return voteRepository.save(vote)
    }

    override fun delete(userId: Long, voteId: Long) {
        checkNotFound(voteRepository.delete(userId, voteId), voteId)
    }
}

