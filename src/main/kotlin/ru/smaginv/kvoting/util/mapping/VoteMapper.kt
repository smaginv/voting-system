package ru.smaginv.kvoting.util.mapping

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import ru.smaginv.kvoting.entity.Vote
import ru.smaginv.kvoting.web.dto.vote.VoteDto
import ru.smaginv.kvoting.web.dto.vote.VoteInfoDto

@Mapper(componentModel = "spring")
interface VoteMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    fun map(voteDto: VoteDto): Vote

    fun mapDto(vote: Vote): VoteDto

    fun mapInfoDto(vote: Vote): VoteInfoDto

    fun mapInfoDtos(votes: List<Vote>): List<VoteInfoDto>

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    fun update(voteDto: VoteDto, @MappingTarget vote: Vote)
}