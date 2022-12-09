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
    override fun get(userId: Long, voteId: Long): Vote {
        return checkNotFound(voteRepository.get(userId, voteId), voteId)
    }

    override fun getAllOnDate(date: LocalDate): List<Vote> {
        return voteRepository.getAllOnDate(date.atTime(LocalTime.MAX), date.atStartOfDay())
    }

    override fun save(vote: Vote): Vote {
        return voteRepository.save(vote)
    }

    override fun delete(userId: Long, voteId: Long): Int {
        return voteRepository.delete(userId, voteId)
    }
}

