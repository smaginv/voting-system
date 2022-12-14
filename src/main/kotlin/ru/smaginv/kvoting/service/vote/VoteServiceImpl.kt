package ru.smaginv.kvoting.service.vote

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.smaginv.kvoting.entity.Vote
import ru.smaginv.kvoting.repository.restaurant.RestaurantRepository
import ru.smaginv.kvoting.repository.vote.VoteRepository
import ru.smaginv.kvoting.util.mapping.VoteMapper
import ru.smaginv.kvoting.web.dto.vote.VoteDto
import ru.smaginv.kvoting.web.dto.vote.VoteInfoDto
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class VoteServiceImpl(
    @Autowired val voteRepository: VoteRepository,
    @Autowired val voteMapper: VoteMapper,
    @Autowired val restaurantRepository: RestaurantRepository
) : VoteService {

    override fun get(voteId: Long): VoteInfoDto {
        return voteMapper.mapInfoDto(getVote(voteId))
    }

    override fun getByUserToday(userId: Long): VoteInfoDto {
        return voteMapper.mapInfoDto(voteRepository.getByUserOnDate(userId, LocalDate.now()))
    }

    override fun getByUserOnDate(userId: Long, date: LocalDate): VoteInfoDto {
        return voteMapper.mapInfoDto(voteRepository.getByUserOnDate(userId, date))
    }

    override fun getAllToday(): List<VoteInfoDto> {
        return voteMapper.mapInfoDtos(voteRepository.getAllOnDate(LocalDate.now()))
    }

    override fun getAllOnDate(date: LocalDate): List<VoteInfoDto> {
        return voteMapper.mapInfoDtos(voteRepository.getAllOnDate(date))
    }

    @Transactional
    override fun update(restaurantId: Long, voteId: Long, voteDto: VoteDto) {
        val updated = getVote(voteId)
        updated.restaurant = restaurantRepository.getReferenceById(restaurantId)
        voteMapper.update(voteDto, updated)
        voteRepository.save(updated)
    }

    @Transactional
    override fun create(restaurantId: Long, voteDto: VoteDto): VoteDto {
        val created = voteMapper.map(voteDto)
        created.restaurant = restaurantRepository.getReferenceById(restaurantId)
        return voteMapper.mapDto(voteRepository.save(created))
    }

    @Transactional
    override fun delete(voteId: Long) {
        voteRepository.delete(voteId)
    }

    private fun getVote(voteId: Long): Vote {
        return voteRepository.get(voteId)
    }
}