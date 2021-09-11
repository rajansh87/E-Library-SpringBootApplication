package com.example.demo.repository

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@RedisHash("Item")
@Repository
class ItemDao(val templates: RedisTemplate<String,Any>) {
    val HASH_KEY="Item"
    fun save(item: Item): Item {
        templates.opsForHash<String,Any>().put(HASH_KEY, item.bookId.toString(), item)
        return item
    }
    fun findAll(): MutableList<Any> {
        return templates.opsForHash<String,Any>().values(HASH_KEY)
    }

    fun findItemById(id: String): Any? {
        return templates.opsForHash<String,Any>().get(HASH_KEY, id)
    }

    fun deleteItem(id: String): Any? {
        return templates.opsForHash<String,Any>().delete(HASH_KEY, id)
        
    }

}