package org.example.presentation.io

class ConsoleReader : InputReader {

    override fun readString(): String? {
        return readlnOrNull()
    }

    override fun readDouble(): Double? {
        return readlnOrNull()?.toDoubleOrNull()
    }

    override fun readInt(): Int? {
        return readlnOrNull()?.toIntOrNull()
    }
}