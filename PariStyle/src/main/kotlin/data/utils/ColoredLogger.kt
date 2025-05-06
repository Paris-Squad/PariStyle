package org.example.data.utils

import io.ktor.client.plugins.logging.Logger

object ColoredLogger : Logger {
    private const val ANSI_RESET = "\u001B[0m"
    private const val ANSI_BLUE = "\u001B[34m"
    private const val ANSI_GREEN = "\u001B[32m"
    private const val ANSI_YELLOW = "\u001B[33m"
    private const val ANSI_RED = "\u001B[31m"

    override fun log(message: String) {
        when {
            message.startsWith("REQUEST") -> println("$ANSI_BLUE$message$ANSI_RESET")
            message.startsWith("RESPONSE") -> println("$ANSI_GREEN$message$ANSI_RESET")
            message.contains("error", ignoreCase = true) -> println("$ANSI_RED$message$ANSI_RESET")
            else -> println("$ANSI_YELLOW$message$ANSI_RESET")
        }
    }
}