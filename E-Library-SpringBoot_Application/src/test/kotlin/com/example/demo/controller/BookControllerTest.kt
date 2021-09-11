package com.example.demo.controller

import com.example.demo.model.Author
import com.example.demo.model.Book
import com.example.demo.service.impl.BookServiceImpl
import io.mockk.MockKAnnotations
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.*
import io.mockk.impl.annotations.InjectMockKs

import io.mockk.every
import org.springframework.http.HttpStatus
import java.time.LocalDateTime


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookControllerTest {

    @MockK
    lateinit var bookService:BookServiceImpl

    @InjectMockKs
    lateinit var bookController: BookController

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

    @Test
    fun testSaveBook(){
        val bookObj=getBookInstance()
        every {
            bookService.saveBook(
                any()
            )
        } returns bookObj
        val result = bookController.saveBook(bookObj)
        Assertions.assertEquals(result.statusCode, HttpStatus.CREATED)
        Assertions.assertEquals(result.body,bookObj)
    }

    @Test
    fun testDeleteBook(){
        val bookObj=getBookInstance()
        every {
            bookService.deleteBook(
                any()
            )
        } returns bookObj
        val result = bookController.deleteBook(bookObj.id)
        Assertions.assertEquals(result.statusCode,HttpStatus.ACCEPTED)
        Assertions.assertEquals(result.body,bookObj)
    }

    @Test
    fun testGetAllBook(){
        val bookObj=getBookInstance()
        every {
            bookService.getAllBook(
                any(),
                any()
            )
        } returns listOf(bookObj)
        val result = bookController.getAllBook(0,2)
        Assertions.assertEquals(result.statusCode,HttpStatus.FOUND)
        Assertions.assertEquals(result.body, listOf(bookObj))
    }

    @Test
    fun testGetBookById(){
        val bookObj=getBookInstance()
        every {
            bookService.getBookById(
                any()
            )
        } returns bookObj
        val result = bookController.getBookById(bookObj.id)
        Assertions.assertEquals(result.statusCode,HttpStatus.FOUND)
        Assertions.assertEquals(result.body, bookObj)
    }

    @Test
    fun testGetBookByBookName(){
        val bookObj=getBookInstance()
        every {
            bookService.getDetails(
                any()
            )
        } returns listOf(bookObj)
        val result = bookController.findByName(bookObj.bookName)
        Assertions.assertEquals(result.statusCode,HttpStatus.FOUND)
        Assertions.assertEquals(result.body, listOf(bookObj))
    }

    @Test
    fun testGetBookByAuthorId(){
        val bookObj=getBookInstance()
        every {
            bookService.getBookByAuthor(
                any()
            )
        } returns listOf(bookObj)
        val result = bookController.findByAuthorId(bookObj.id)
        Assertions.assertEquals(result.statusCode,HttpStatus.FOUND)
        Assertions.assertEquals(result.body, listOf(bookObj))
    }

    @Test
    fun testGetBookByAuthorName(){
        val bookObj=getBookInstance()
        val authorObj=getAuthorInstance()
        every {
            bookService.getBookByAuthorName(
                any()
            )
        } returns listOf(bookObj)
        val result = bookController.findByAuthorName(authorObj.authName)
        Assertions.assertEquals(result.statusCode,HttpStatus.FOUND)
        Assertions.assertEquals(result.body, listOf(bookObj))
    }

    @Test
    fun testTextualSearch(){
        val bookObj=getBookInstance()
        every {
            bookService.getTextualSearch(
                any(),
                any(),
                any()
            )
        } returns listOf(bookObj)
        val result = bookController.textualSearchBook("Harry",0,2)
        Assertions.assertEquals(result.statusCode,HttpStatus.FOUND)
        Assertions.assertEquals(result.body, listOf(bookObj))
    }

}