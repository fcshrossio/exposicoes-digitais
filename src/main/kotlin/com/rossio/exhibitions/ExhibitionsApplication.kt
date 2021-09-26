package com.rossio.exhibitions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ExhibitionsApplication

fun main(args: Array<String>) {
    runApplication<ExhibitionsApplication>(*args)
}
