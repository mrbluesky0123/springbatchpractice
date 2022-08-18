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
import org.springframework.beans.factory.annotation.Autowired
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
    fun helloWorldJob(): Job = jobBuilderFactory.get("helloWorldJob")
        .incrementer(RunIdIncrementer())
        .start(helloWorldStep())
        .build()

    @JobScope
    @Bean
    fun helloWorldStep(): Step = stepBuilderFactory.get("helloWorldStep")
        .tasklet { contribution, chunkContext ->
            log.error("############")
            println("Hello, World! Spring Batch!")
            RepeatStatus.FINISHED
        }
        .build()

    @StepScope
    @Bean
    fun helloWorldTasklet(): Tasklet = Tasklet { contribution, chunkContext ->
        log.error("############")
        println("Hello, World! Spring Batch!")
        RepeatStatus.FINISHED
    }
//    @StepScope
//    @Bean
//    fun helloWorldTasklet(): Tasklet = Tasklet { contribution, chunkContext ->
//        log.error("############")
//        println("Hello, World! Spring Batch!")
//        RepeatStatus.FINISHED
//    }
}