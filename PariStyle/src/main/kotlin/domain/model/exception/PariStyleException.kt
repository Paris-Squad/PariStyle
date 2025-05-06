package org.example.domain.model.exception

abstract class PariStyleException(message: String? = null) : Exception(message) {

    class InvalidInputException(message: String?) : PariStyleException(message)
    class NotFoundException(message: String?) : PariStyleException(message)
}