package ru.smaginv.kvoting.util.exception

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.smaginv.kvoting.util.exception.ErrorInfo.Companion.createErrorInfo

@RestControllerAdvice
class AppExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [EndOfVoteException::class, UnauthorizedException::class])
    fun handleAppException(ex: AppException, request: WebRequest): ResponseEntity<ErrorInfo> {
        val errorInfo = createErrorInfo(ex, request)
        return ResponseEntity.status(ex.getHttpStatus()).body(errorInfo)
    }

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun handleIllegalArgumentException(ex: IllegalArgumentException, request: WebRequest): ResponseEntity<ErrorInfo> {
        val status = HttpStatus.BAD_REQUEST
        val errorInfo: ErrorInfo = createErrorInfo(status, ex, request)
        return ResponseEntity.status(status).body(errorInfo)
    }

    @ExceptionHandler(value = [DataIntegrityViolationException::class])
    fun handleDataIntegrityViolationException(
        ex: DataIntegrityViolationException,
        request: WebRequest
    ): ResponseEntity<ErrorInfo> {
        val status = HttpStatus.CONFLICT
        val errorInfo = createErrorInfo(status, ex.rootCause, request)
        return ResponseEntity.status(status).body(errorInfo)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        code: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val status = HttpStatus.resolve(code.value()) ?: HttpStatus.BAD_REQUEST
        val errorInfo = createErrorInfo(status, "VALIDATION FAILED", ex, request)
        return ResponseEntity.status(status).body(errorInfo)
    }
}