package ru.smaginv.kvoting.util

import ru.smaginv.kvoting.util.exception.NotFoundException

fun <T : Any?, I> checkNotFound(entity: T, identifier: I): T & Any {
    return entity ?: throw NotFoundException("Not found entity with identifier: $identifier")
}

fun <ID> checkNotFound(found: Int, id: ID) {
    if (found == 0)
        throw NotFoundException("Not found entity with id: $id")
}