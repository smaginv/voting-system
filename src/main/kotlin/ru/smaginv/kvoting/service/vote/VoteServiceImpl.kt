package ru.smaginv.kvoting.service.vote

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.smaginv.kvoting.entity.Vote
import ru.smaginv.kvoting.repository.restaurant.RestaurantRepository
import ru.smaginv.kvoting.repository.user.UserRepository
import ru.smaginv.kvoting.repository.vote.VoteRepository
import ru.smaginv.kvoting.util.VotingUtil
import ru.smaginv.kvoting.util.mapping.VoteMapper
import ru.smaginv.kvoting.web.dto.vote.VoteInfoDto
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class VoteServiceImpl(
    val voteRepository: VoteRepository,
    val voteMapper: VoteMapper,
    val restaurantRepository: RestaurantRepository,
    val userRepository: UserRepository,
    val votingUtil: VotingUtil
) : VoteService {

    override fun get(userId: Long, voteId: Long): VoteInfoDto {
        return voteMapper.mapInfoDto(voteRepository.get(userId, voteId))
    }

    override fun getByUserToday(userId: Long): VoteInfoDto {
        val today = LocalDate.now()
        val vote = voteRepository.getByUserOnDate(userId, today)
        return voteMapper.mapInfoDto(vote)
    }

    override fun getByUserOnDate(userId: Long, date: LocalDate): VoteInfoDto {
        val vote = voteRepository.getByUserOnDate(userId, date)
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
        votingUtil.checkVoteTime()
        val vote = voteRepository.getByUserOnDate(userId, LocalDate.now()).apply {
            votingUtil.assureIdConsistent(id, voteId)
            votingUtil.checkReVoting(restaurantId, restaurant.id)
            restaurant = restaurantRepository.getReferenceById(restaurantId)
        }
        voteRepository.save(vote)
    }

    @Transactional
    override fun create(userId: Long, restaurantId: Long): VoteInfoDto {
        votingUtil.checkVoteTime()
        val vote = voteRepository.getByUserToday(userId)?.apply {
            votingUtil.checkReVoting(restaurantId, restaurant.id)
            restaurant = restaurantRepository.getReferenceById(restaurantId)
        } ?: Vote(
            id = null,
            date = LocalDate.now(),
            restaurant = restaurantRepository.getReferenceById(restaurantId),
            user = userRepository.getReferenceById(userId)
        )
        return voteMapper.mapInfoDto(voteRepository.save(vote))
    }

    @Transactional
    override fun delete(userId: Long, voteId: Long) {
        voteRepository.delete(userId, voteId)
    }
}