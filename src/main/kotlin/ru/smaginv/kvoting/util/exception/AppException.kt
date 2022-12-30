package ru.smaginv.kvoting.util.exception

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

interface AppException {

    fun getHttpStatus(): HttpStatus

    fun getTimestamp(): LocalDateTime

    fun getErrorMessage(): String
}