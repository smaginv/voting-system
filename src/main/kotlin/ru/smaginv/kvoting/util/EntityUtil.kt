package ru.smaginv.kvoting.util

import jakarta.persistence.EntityNotFoundException

fun <T : Any?, I> checkNotFound(entity: T, identifier: I): T & Any {
    return entity ?: throw EntityNotFoundException("Not found entity with identifier: $identifier")
}