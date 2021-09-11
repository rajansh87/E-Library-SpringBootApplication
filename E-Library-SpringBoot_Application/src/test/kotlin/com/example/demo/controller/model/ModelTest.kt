package com.example.demo.controller.model

import com.example.demo.model.Author
import com.example.demo.model.Book
import io.mockk.MockKAnnotations
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.LocalDateTime


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ModelTest {
    @BeforeAll
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun getAuthorInstance(){
        val id:Long=1
        val authName="abcd"
        val author=Author(id,authName)
        Assertions.assertEquals(id,author.id)
        Assertions.assertEquals(authName,author.authName)
    }
    @Test
    fun getBoookInstance(){
        val id:Long =1
        val bookName="newBook9"
        val noPage:Int =670
        val isbn:Int =10
        val category="Thriller"
        val added_on = LocalDateTime.now()
        val authId:Long=1
        val authName="abcd"
        val author=Author(authId,authName)

        val book= Book(id,bookName,noPage=noPage,isbn,category,added_on,author)
        Assertions.assertEquals(id,book.id)
        Assertions.assertEquals(bookName,book.bookName)
    }
    @Test
    fun getItemInstance(){
        val id:Long =1
        val bookName="newBook9"
        val noPage:Int =670
        val isbn:Int =10
        val category="Thriller"
        val added_on = LocalDateTime.now()
        val authId:Long=1
        val authName="abcd"
        val author=Author(authId,authName)

        val book= Book(id,bookName,noPage,isbn,category,added_on,author)

        Assertions.assertEquals(id,book.id)
        Assertions.assertEquals(bookName,book.bookName)
        Assertions.assertEquals(noPage,book.noPage)
        Assertions.assertEquals(isbn,book.isbn)
        Assertions.assertEquals(category,book.category)
        Assertions.assertEquals(added_on,book.added_on)
        Assertions.assertEquals(author,book.author)



    }





}