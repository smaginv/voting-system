package ru.smaginv.kvoting.util

import org.springframework.stereotype.Component
import ru.smaginv.kvoting.config.PropertiesConfig
import ru.smaginv.kvoting.util.exception.EndOfVoteException
import java.time.LocalTime

@Component
class VotingUtil(
    propertiesConfig: PropertiesConfig
) {

    private val endVoteTime = propertiesConfig.voting().endVoteTime

    fun checkVoteTime() {
        if (!LocalTime.now().isBefore(endVoteTime))
            throw EndOfVoteException("voting is over at: $endVoteTime")
    }

    fun <ID> checkReVoting(newRestaurant: ID, oldRestaurant: ID) {
        if (newRestaurant == oldRestaurant)
            throw IllegalArgumentException("You have already voted for a restaurant with an id: $oldRestaurant")
    }
}