package com.example.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
@EnableRedisRepositories
class RedisConfig {

    @Bean
    fun connectionFactory(): JedisConnectionFactory? {
        val configuration = RedisStandaloneConfiguration()
        configuration.hostName = "localhost"
        configuration.port = 6379
        return JedisConnectionFactory(configuration)
    }

    @Bean
    fun template(): RedisTemplate<String, Any>? {
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(connectionFactory()!!)
        template.keySerializer = StringRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.hashKeySerializer = JdkSerializationRedisSerializer()
        template.valueSerializer = JdkSerializationRedisSerializer()
        template.setEnableTransactionSupport(true)
        template.afterPropertiesSet()
        return template
    }



}