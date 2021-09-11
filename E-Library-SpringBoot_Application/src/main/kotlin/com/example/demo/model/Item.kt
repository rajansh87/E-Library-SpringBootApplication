package com.example.demo.repository

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

@RedisHash("Item")
data class Item(@Id val bookId:Long, val bookName:String):Serializable