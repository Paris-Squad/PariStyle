package org.example.presentation.io

interface Printer {
    fun displayMsg(input: Any? = "\n")
    fun displayLn(input: Any? = "\n")
    fun displayErr(input: Any? = "")
}