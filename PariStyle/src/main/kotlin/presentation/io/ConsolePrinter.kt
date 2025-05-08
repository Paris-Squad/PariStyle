package org.example.presentation.io

class ConsolePrinter : Printer {
    override fun displayMsg(input: Any?) {
        println("\u001B[33m$input\u001B[33m")
    }

    override fun displayLn(input: Any?) {
        println(input)
    }

    override fun displayErr(input: Any?) {
        System.err.println(input)
    }
}