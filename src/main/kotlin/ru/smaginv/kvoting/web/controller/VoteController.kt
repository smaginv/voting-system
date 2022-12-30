package ru.smaginv.kvoting.web.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import ru.smaginv.kvoting.service.vote.VoteService
import ru.smaginv.kvoting.web.AuthUser
import ru.smaginv.kvoting.web.dto.vote.VoteInfoDto
import java.time.LocalDate

@RestController
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Vote Controller")
class VoteController(
    val voteService: VoteService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/user/vote/today")
    @Operation(summary = "Get today's user vote")
    fun getByUserToday(@AuthenticationPrincipal authUser: AuthUser): ResponseEntity<VoteInfoDto> {
        logger.info("get a user vote with an id: {} today", authUser.id)
        return ResponseEntity.ok(voteService.getByUserToday(authUser.id))
    }

    @GetMapping("/user/vote/on-date")
    @Operation(summary = "Get the user's vote on the date")
    fun getByUserOnDate(
        @AuthenticationPrincipal authUser: AuthUser,
        @RequestParam date: LocalDate
    ): ResponseEntity<VoteInfoDto> {
        logger.info("get a user vote with an id: {} on date: {}", authUser.id, date)
        return ResponseEntity.ok(voteService.getByUserOnDate(authUser.id, date))
    }

    @GetMapping("/user/votes")
    @Operation(summary = "User voting history")
    fun getAllByUser(@AuthenticationPrincipal authUser: AuthUser): ResponseEntity<List<VoteInfoDto>> {
        logger.info("get all user votes with an id: {}", authUser.id)
        return ResponseEntity.ok(voteService.getAllByUser(authUser.id))
    }

    @PatchMapping("/user/vote")
    @Operation(summary = "Re-vote")
    fun <T> update(
        @AuthenticationPrincipal authUser: AuthUser,
        @RequestParam restaurantId: Long
    ): ResponseEntity<T> {
        logger.info("update user vote")
        voteService.update(authUser.id, restaurantId)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/user/vote")
    @Operation(summary = "Vote")
    fun create(
        @AuthenticationPrincipal authUser: AuthUser,
        @RequestParam restaurantId: Long
    ): ResponseEntity<VoteInfoDto> {
        logger.info("voting by a user with an id: {}", authUser.id)
        val created = voteService.create(authUser.id, restaurantId)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path("/today")
            .build()
            .toUri()
        return ResponseEntity.created(location).body(created)
    }

    @DeleteMapping("/user/vote")
    @Operation(summary = "Вelete today's user vote")
    fun <T> delete(@AuthenticationPrincipal authUser: AuthUser): ResponseEntity<T> {
        logger.info("delete today user vote")
        voteService.delete(authUser.id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/votes/today")
    @Operation(summary = "Get all today votes")
    fun getAllToday(): ResponseEntity<List<VoteInfoDto>> {
        logger.info("get all today votes")
        return ResponseEntity.ok(voteService.getAllToday())
    }

    @GetMapping("/votes/on-date")
    @Operation(summary = "Get all votes on date")
    fun getAllOnDate(@RequestParam date: LocalDate): ResponseEntity<List<VoteInfoDto>> {
        logger.info("get all votes on date: {}", date)
        return ResponseEntity.ok(voteService.getAllOnDate(date))
    }
}