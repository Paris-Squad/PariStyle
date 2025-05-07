package org.example.domain.model.exception

abstract class PariStyleException(message: String? = null) : Exception(message) {
    class NetWorkException(message: String? = null) : PariStyleException(message)
    class InvalidInputException(message: String? = null) : PariStyleException(message)
    class NotFoundException(message: String? = null) : PariStyleException(message)
}