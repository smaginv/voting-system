package ru.smaginv.kvoting.util.exception

class UnauthorizedException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}