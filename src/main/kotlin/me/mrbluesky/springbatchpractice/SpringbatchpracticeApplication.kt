package me.mrbluesky.springbatchpractice

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableBatchProcessing
@SpringBootApplication
class SpringbatchpracticeApplication

fun main(args: Array<String>) {
    runApplication<SpringbatchpracticeApplication>(*args)
}
