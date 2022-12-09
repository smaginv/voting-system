package ru.smaginv.kvoting.util.mapping

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.smaginv.kvoting.entity.Vote
import ru.smaginv.kvoting.web.dto.vote.VoteDto

@Mapper(componentModel = "spring")
interface VoteMapper {

    @Mapping(target = "timeStamp", source = "time")
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "user", ignore = true)
    fun map(voteDto: VoteDto): Vote

    @Mapping(target = "time", source = "timeStamp")
    fun mapDto(vote: Vote): VoteDto
}