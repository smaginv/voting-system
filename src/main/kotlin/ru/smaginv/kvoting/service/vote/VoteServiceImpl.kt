package ru.smaginv.kvoting.service.vote

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
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

    @Cacheable(
        value = ["vote"],
        key = "#userId + '_today'"
    )
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

    @Cacheable(
        value = ["vote"],
        key = "'all'"
    )
    override fun getAllToday(): List<VoteInfoDto> {
        return voteMapper.mapInfoDtos(voteRepository.getAllOnDate(LocalDate.now()))
    }

    override fun getAllOnDate(date: LocalDate): List<VoteInfoDto> {
        return voteMapper.mapInfoDtos(voteRepository.getAllOnDate(date))
    }

    @Caching(
        evict = [
            CacheEvict(value = ["vote"], key = "#userId + '_today'"),
            CacheEvict(value = ["vote"], key = "'all'")
        ]
    )
    @Transactional
    override fun update(userId: Long, restaurantId: Long) {
        votingUtil.checkVoteTime()
        val vote = voteRepository.getByUserOnDate(userId, LocalDate.now()).apply {
            votingUtil.checkReVoting(restaurantId, restaurant.id)
            restaurant = restaurantRepository.getReferenceById(restaurantId)
        }
        voteRepository.save(vote)
    }

    @Caching(
        evict = [
            CacheEvict(value = ["vote"], key = "#userId + '_today'"),
            CacheEvict(value = ["vote"], key = "'all'")
        ]
    )
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

    @Caching(
        evict = [
            CacheEvict(value = ["vote"], key = "#userId + '_today'"),
            CacheEvict(value = ["vote"], key = "'all'")
        ]
    )
    @Transactional
    override fun delete(userId: Long) {
        voteRepository.delete(userId)
    }
}