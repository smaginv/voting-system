package ru.smaginv.kvoting.util.exception

class EndOfVoteException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}