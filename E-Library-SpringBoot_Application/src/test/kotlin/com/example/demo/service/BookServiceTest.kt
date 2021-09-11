package com.example.demo.service

import com.example.demo.model.Author
import com.example.demo.model.Book
import com.example.demo.repository.AuthorRepository
import com.example.demo.repository.BookRepository
import com.example.demo.repository.Item
import com.example.demo.repository.ItemDao
import com.example.demo.service.impl.BookServiceImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDateTime


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceTest {
    @MockK
    lateinit var bookRepository: BookRepository
    @MockK
    lateinit var itemRepository: ItemDao
    @MockK
    lateinit var authorRepository: AuthorRepository

    @InjectMockKs
    lateinit var bookService: BookServiceImpl

    @BeforeAll
    fun setUp() = MockKAnnotations.init(this)

    fun getAuthorInstance()= Author(
        id = 1,
        "newAuthor"
    )
    fun getBookInstance(): Book = Book (
        1,
        "newBook9",
        670,
        10,
        "Thriller",
        added_on = LocalDateTime.now(),
        author = getAuthorInstance()
    )


    @Test
    fun testSaveBook(){
        val bookObj=getBookInstance()

        every {
            bookRepository.save(
                any()
            )
        } returns bookObj
        every {
            itemRepository.save(
                any()
            )
        } returns Item(bookObj.id,bookObj.bookName)
        val result = bookService.saveBook(bookObj)
        Assertions.assertEquals(result, bookObj)
    }

    @Test
    fun testDeleteBook() {

        val bookObj=getBookInstance()
        val id:Long = 1
        every {
            bookRepository.findByIdOrNull(
                any()
            )
        } returns bookObj

        every {
            bookRepository.delete(
                any()
            )
        } returns Unit
        every {
            itemRepository.deleteItem(
                any()
            )
        } returns Unit.toString()
        val result = bookService.deleteBook(id)
        Assertions.assertEquals(result, bookObj)
    }

    @Test
    fun testGetAllBook(){
        val bookObj=getBookInstance()
        every {
            val paging:Pageable = PageRequest.of(0,2)
            bookRepository.findAll(paging).content
        } returns listOf(bookObj)
        val result = bookService.getAllBook(0,2)
        Assertions.assertEquals(result, listOf(bookObj))
    }

    @Test
    fun testGetBookById(){
        val bookObj=getBookInstance()
        every {
            itemRepository.findItemById(
                any()
            )
        } returns bookObj
        val result = bookService.getBookById(bookObj.id)
        Assertions.assertEquals(result, bookObj)
    }

    @Test
    fun testGetBookByBookName(){
        val bookObj=getBookInstance()
        every {
            bookRepository.findByBookName(
                any()
            )
        } returns listOf(bookObj)
        val result = bookService.getDetails(bookObj.bookName)
        Assertions.assertEquals(result, listOf(bookObj))
    }

    @Test
    fun testGetBookByAuthorId(){
        val bookObj=getBookInstance()
        every {
            bookRepository.findByAuthorId(
                any()
            )
        } returns listOf(bookObj)
        val result = bookService.getBookByAuthor(bookObj.id)
        Assertions.assertEquals(result, listOf(bookObj))
    }

    @Test
    fun testGetBookByAuthorName(){
        val bookObj=getBookInstance()
        val authorObj=getAuthorInstance()
        every {
            authorRepository.findByAuthName(any())
        }returns listOf(authorObj)
        every {
            bookRepository.findByAuthor(authorObj)
        } returns listOf(bookObj)
        val result = bookService.getBookByAuthorName(authorObj.authName)
        Assertions.assertEquals(result, listOf(bookObj))
    }

    @Test
    fun testTextualSearch(){
        val bookObj=getBookInstance()
        every {
            bookRepository.applyTextualSearchBook(
                any(),
                any()
            ).content

        } returns listOf(bookObj)
        val result = bookService.getTextualSearch("Harry",0,2)
        Assertions.assertEquals(result, listOf(bookObj))
    }







}