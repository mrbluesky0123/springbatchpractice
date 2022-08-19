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
class HelloWorldJobDupConfig(val jobBuilderFactory: JobBuilderFactory,
                             val stepBuilderFactory: StepBuilderFactory,
                             val simpleTasklet: SimpleTasklet) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(HelloWorldJobDupConfig::class.java) as Logger
    }

    @Bean
    fun helloWorldJob3(): Job {
        log.info("helloWorldJob3")
        return jobBuilderFactory.get("helloWorldJob3")
            .incrementer(RunIdIncrementer())
            .start(helloWorldStep3())
            .next(helloWorldStep4())
            .build()
    }

    @Bean
    fun helloWorldStep3(): Step {
        log.info("##############@@@")
        return stepBuilderFactory.get("helloWorldStep3")
            .tasklet { contribution, chunkContext ->
                log.error("#####123#######")
                log.error("Hello, World! Spring Batch!")
                RepeatStatus.FINISHED
            }
            .build()
    }
    @Bean
    fun helloWorldStep4(): Step {
        log.info("##############@3@@")
        return stepBuilderFactory.get("helloWorldStep33")
            .tasklet { contribution, chunkContext ->
                log.error("#####123#######")
                log.error("Hello, World! Spring Batch!")
                RepeatStatus.FINISHED
            }
            .build()
    }

}