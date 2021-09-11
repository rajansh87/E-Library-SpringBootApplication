package com.example.demo

import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.runApplication

internal class SpringBootFirstAppApplicationTest{
    @Test
    fun TestMain(){
        val obj = SpringBootFirstAppApplication()
    }
    @Test
    fun TesRunApplication(){
        val obj = runApplication<SpringBootFirstAppApplication>(
            "abc"
        )
    }

}