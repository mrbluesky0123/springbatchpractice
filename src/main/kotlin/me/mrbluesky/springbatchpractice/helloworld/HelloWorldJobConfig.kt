package me.mrbluesky.springbatchpractice.helloworld

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HelloWorldJobConfig(val jobBuilderFactory: JobBuilderFactory,
                          val stepBuilderFactory: StepBuilderFactory,
                          val simpleTasklet: SimpleTasklet) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(HelloWorldJobConfig::class.java) as Logger
    }

    @Bean
    fun helloWorldJob(): Job {
        log.trace("helloWorldJob")
        return jobBuilderFactory.get("helloWorldJob")
            .incrementer(RunIdIncrementer())
            .start(helloWorldStep())
            .next(helloWorldStep2())
            .build()
    }

    @Bean
    fun helloWorldStep(): Step {
        log.trace("##############@@@")
        return stepBuilderFactory.get("helloWorldStep")
            .tasklet { contribution, chunkContext ->
                log.error("#####123#######")
                log.error("Hello, World! Spring Batch!")
                RepeatStatus.FINISHED
            }
            .build()
    }
    @Bean
    fun helloWorldStep2(): Step {
        log.trace("##############@22@@")
        return stepBuilderFactory.get("helloWorldStep")
            .tasklet { contribution, chunkContext ->
                log.error("#####123#######")
                log.error("Hello, World! Spring Batch!")
                RepeatStatus.FINISHED
            }
            .build()
    }

}