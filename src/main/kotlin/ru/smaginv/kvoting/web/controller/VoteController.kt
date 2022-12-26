package ru.smaginv.kvoting.web.controller

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import ru.smaginv.kvoting.service.vote.VoteService
import ru.smaginv.kvoting.web.dto.vote.VoteInfoDto
import java.time.LocalDate

@RestController
@RequestMapping(
    value = ["/users"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class VoteController(
    val voteService: VoteService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/{userId}/votes/{voteId}")
    fun get(@PathVariable userId: Long, @PathVariable voteId: Long): ResponseEntity<VoteInfoDto> {
        logger.info("get vote with id: {}", voteId)
        return ResponseEntity.ok(voteService.get(userId, voteId))
    }

    @GetMapping("/{userId}/votes/today")
    fun getByUserToday(@PathVariable userId: Long): ResponseEntity<VoteInfoDto> {
        logger.info("get a user vote with an id: {} today", userId)
        return ResponseEntity.ok(voteService.getByUserToday(userId))
    }

    @GetMapping("/{userId}/votes/on-date")
    fun getByUserOnDate(@PathVariable userId: Long, @RequestParam date: LocalDate): ResponseEntity<VoteInfoDto> {
        logger.info("get a user vote with an id: {} on date: {}", userId, date)
        return ResponseEntity.ok(voteService.getByUserOnDate(userId, date))
    }

    @GetMapping("/{userId}/votes")
    fun getAllByUser(@PathVariable userId: Long): ResponseEntity<List<VoteInfoDto>> {
        logger.info("get all user votes with an id: {}", userId)
        return ResponseEntity.ok(voteService.getAllByUser(userId))
    }

    @GetMapping("/votes/today")
    fun getAllToday(): ResponseEntity<List<VoteInfoDto>> {
        logger.info("get all today votes")
        return ResponseEntity.ok(voteService.getAllToday())
    }

    @GetMapping("/votes/on-date")
    fun getAllOnDate(@RequestParam date: LocalDate): ResponseEntity<List<VoteInfoDto>> {
        logger.info("get all votes on date: {}", date)
        return ResponseEntity.ok(voteService.getAllOnDate(date))
    }

    @PatchMapping("/{userId}/votes/{voteId}")
    fun <T> update(
        @PathVariable userId: Long,
        @PathVariable voteId: Long,
        @RequestParam restaurantId: Long
    ): ResponseEntity<T> {
        logger.info("update vote with id: {}", voteId)
        voteService.update(userId, restaurantId, voteId)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{userId}/votes")
    fun create(
        @PathVariable userId: Long,
        @RequestParam restaurantId: Long
    ): ResponseEntity<VoteInfoDto> {
        logger.info("voting by a user with an id: {}", userId)
        val created = voteService.create(userId, restaurantId)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path("/{voteId}")
            .buildAndExpand(created.id)
            .toUri()
        return ResponseEntity.created(location).body(created)
    }

    @DeleteMapping("/{userId}/votes/{voteId}")
    fun <T> delete(@PathVariable userId: Long, @PathVariable voteId: Long): ResponseEntity<T> {
        logger.info("delete vote with id: {}", voteId)
        voteService.delete(userId, voteId)
        return ResponseEntity.noContent().build()
    }
}