package ru.smaginv.kvoting.util.mapping

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import ru.smaginv.kvoting.entity.Vote
import ru.smaginv.kvoting.web.dto.vote.VoteDto
import ru.smaginv.kvoting.web.dto.vote.VoteInfoDto

@Mapper(componentModel = "spring")
interface VoteMapper {

    @Mapping(target = "timeStamp", source = "time")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    fun map(voteDto: VoteDto): Vote

    @Mapping(target = "time", source = "timeStamp")
    fun mapDto(vote: Vote): VoteDto

    @Mapping(target = "time", source = "timeStamp")
    fun mapInfoDto(vote: Vote): VoteInfoDto

    @Mapping(target = "time", source = "timeStamp")
    fun mapInfoDtos(votes: List<Vote>): List<VoteInfoDto>

    @Mapping(target = "timeStamp", source = "time")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    fun update(voteDto: VoteDto, @MappingTarget vote: Vote)
}