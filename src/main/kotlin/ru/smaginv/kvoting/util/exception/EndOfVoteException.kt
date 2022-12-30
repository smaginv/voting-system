package ru.smaginv.kvoting.util.exception

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class EndOfVoteException(
    private val errorMessage: String,
    private val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
    private val timestamp: LocalDateTime = LocalDateTime.now()
) : RuntimeException(), AppException {

    override fun getHttpStatus() = httpStatus

    override fun getTimestamp() = timestamp

    override fun getErrorMessage() = errorMessage
}