package ru.smaginv.kvoting.util.exception

import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

class ErrorInfo private constructor(
    val timestamp: LocalDateTime,
    val httpStatus: HttpStatus,
    val message: String,
    val details: List<String>,
    val path: String
) {
    companion object {
        fun createErrorInfo(ex: AppException, request: WebRequest): ErrorInfo {
            return ErrorInfo(
                timestamp = ex.getTimestamp(),
                httpStatus = ex.getHttpStatus(),
                message = ex.getErrorMessage(),
                details = emptyList(),
                path = getPath(request)
            )
        }

        fun createErrorInfo(status: HttpStatus, ex: Exception, request: WebRequest): ErrorInfo {
            return ErrorInfo(
                timestamp = LocalDateTime.now(),
                httpStatus = status,
                message = ex.message ?: String(),
                details = emptyList(),
                path = getPath(request)
            )
        }

        fun createErrorInfo(status: HttpStatus, throwable: Throwable?, request: WebRequest): ErrorInfo {
            return ErrorInfo(
                timestamp = LocalDateTime.now(),
                httpStatus = status,
                message = throwable?.message ?: String(),
                details = emptyList(),
                path = getPath(request)
            )
        }

        fun createErrorInfo(status: HttpStatus, message: String, ex: BindException, request: WebRequest): ErrorInfo {
            return ErrorInfo(
                timestamp = LocalDateTime.now(),
                httpStatus = status,
                message = message,
                details = getDetails(ex),
                path = getPath(request)
            )
        }

        private fun getPath(request: WebRequest): String {
            val sb = StringBuilder(request.getDescription(false))
            return sb.substring(sb.indexOf("/"))
        }

        private fun getDetails(ex: BindException): List<String> {
            return ex.bindingResult.fieldErrors
                .asSequence()
                .map { error ->
                    String.format(
                        "'%s' %s",
                        error.field,
                        error.defaultMessage
                    )
                }
                .toList()
        }
    }
}