package ru.smaginv.kvoting.repository.vote

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import ru.smaginv.kvoting.entity.Vote
import ru.smaginv.kvoting.util.checkNotFound
import java.time.LocalDate
import java.time.LocalTime

@Repository
class VoteRepositoryImpl(
    @Autowired val voteRepository: VoteRepositoryJpa
) : VoteRepository {
    override fun get(voteId: Long): Vote {
        return checkNotFound(voteRepository.get(voteId), voteId)
    }

    override fun getByUserOnDate(userId: Long, date: LocalDate): Vote {
        val vote = voteRepository.getByUserOnDate(userId, date.atStartOfDay(), date.atTime(LocalTime.MAX))
        return checkNotFound(vote, date)
    }

    override fun getAllOnDate(date: LocalDate): List<Vote> {
        return voteRepository.getAllOnDate(date.atStartOfDay(), date.atTime(LocalTime.MAX))
    }

    override fun save(vote: Vote): Vote {
        return voteRepository.save(vote)
    }

    override fun delete(voteId: Long) {
        checkNotFound(voteRepository.delete(voteId), voteId)
    }
}

