package com.example.demo.service

import com.example.demo.model.Author
import com.example.demo.model.Book
import com.example.demo.repository.Item
import com.example.demo.repository.ItemDao
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeAll
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.*

import org.springframework.data.redis.core.RedisTemplate
import java.time.LocalDateTime
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemDaoTest {
    @MockK
    lateinit var templates: RedisTemplate<String,Any>
    @InjectMockKs
    private lateinit var itemRepository: ItemDao

    @BeforeAll
    fun setUp() = MockKAnnotations.init(this)

    fun getAuthorInstance()=Author(
        id = 1,
        "newAuthor"
    )
    fun getBookInstance()= Book (
        1,
        "newBook9",
        670,
        10,
        "Thriller",
        added_on = LocalDateTime.now(),
        author = getAuthorInstance()
    )

    val authorObj=getAuthorInstance()
    val bookObj=getBookInstance()

    @Test
    fun itemDaoSave() {
        val item = Item(bookObj.id,bookObj.bookName)
        every {
            templates.opsForHash<String,Any>().put(
                any(),
                any(),
                any()
            )
        } returns Unit
        val result = itemRepository.save(item)
        Assertions.assertEquals(result, item)
    }
    @Test
    fun itemDaoFindAll() {
        val item =Item(bookObj.id,bookObj.bookName)
        every {
            templates.opsForHash<String,Any>().values(
                any(),
            )
        } returns listOf(item)
        val result = itemRepository.findAll()
        Assertions.assertEquals(result, listOf(item))
    }
    @Test
    fun itemDaoFindItemById() {
        val item =Item(bookObj.id,bookObj.bookName)
        every {
            templates.opsForHash<String,Any>().get(
                any(),
                any()
            )
        } returns item
        val result = itemRepository.findItemById(item.bookId.toStr())
        Assertions.assertEquals(result, item)
    }
    @Test
    fun itemDaoDeleteItem() {
        val item =Item(bookObj.id,bookObj.bookName)
        every {
            templates.opsForHash<String,Any>().delete(
                any(),
                any()
            )
        } returns item.bookId
        val result = itemRepository.deleteItem(item.bookId.toString())
        Assertions.assertEquals(result, item.bookId)
    }
}