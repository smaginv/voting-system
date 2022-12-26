package ru.smaginv.kvoting.service.vote

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.smaginv.kvoting.entity.Vote
import ru.smaginv.kvoting.repository.restaurant.RestaurantRepository
import ru.smaginv.kvoting.repository.user.UserRepository
import ru.smaginv.kvoting.repository.vote.VoteRepository
import ru.smaginv.kvoting.util.checkNotFound
import ru.smaginv.kvoting.util.mapping.VoteMapper
import ru.smaginv.kvoting.web.dto.vote.VoteInfoDto
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class VoteServiceImpl(
    val voteRepository: VoteRepository,
    val voteMapper: VoteMapper,
    val restaurantRepository: RestaurantRepository,
    val userRepository: UserRepository
) : VoteService {

    override fun get(userId: Long, voteId: Long): VoteInfoDto {
        return voteMapper.mapInfoDto(getVote(userId, voteId))
    }

    override fun getByUserToday(userId: Long): VoteInfoDto {
        val today = LocalDate.now()
        val vote = checkNotFound(voteRepository.getByUserOnDate(userId, today), today)
        return voteMapper.mapInfoDto(vote)
    }

    override fun getByUserOnDate(userId: Long, date: LocalDate): VoteInfoDto {
        val vote = checkNotFound(voteRepository.getByUserOnDate(userId, date), date)
        return voteMapper.mapInfoDto(vote)
    }

    override fun getAllByUser(userId: Long): List<VoteInfoDto> {
        return voteMapper.mapInfoDtos(voteRepository.getAllByUser(userId))
    }

    override fun getAllToday(): List<VoteInfoDto> {
        return voteMapper.mapInfoDtos(voteRepository.getAllOnDate(LocalDate.now()))
    }

    override fun getAllOnDate(date: LocalDate): List<VoteInfoDto> {
        return voteMapper.mapInfoDtos(voteRepository.getAllOnDate(date))
    }

    @Transactional
    override fun update(userId: Long, restaurantId: Long, voteId: Long) {
        val updated = getVote(userId, voteId).apply {
            restaurant = restaurantRepository.getReferenceById(restaurantId)
        }
        voteRepository.save(updated)
    }

    @Transactional
    override fun create(userId: Long, restaurantId: Long): VoteInfoDto {
        val user = userRepository.getReferenceById(userId)
        val restaurant = restaurantRepository.getReferenceById(restaurantId)
        val today = LocalDate.now()
        val vote = voteRepository.getByUserOnDate(userId, today) ?: Vote(null, today, restaurant, user)
        return voteMapper.mapInfoDto(voteRepository.save(vote))
    }

    @Transactional
    override fun delete(userId: Long, voteId: Long) {
        voteRepository.delete(userId, voteId)
    }

    private fun getVote(userId: Long, voteId: Long): Vote {
        return voteRepository.get(userId, voteId)
    }
}