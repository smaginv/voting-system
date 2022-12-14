package ru.smaginv.kvoting.util

import jakarta.persistence.EntityNotFoundException

fun <T : Any?, I> checkNotFound(entity: T, identifier: I): T & Any {
    return entity ?: throw EntityNotFoundException("Not found entity with identifier: $identifier")
}

fun <ID> checkNotFound(found: Int, id: ID) {
    if (found == 0)
        throw EntityNotFoundException("Not found entity with id: $id")
}