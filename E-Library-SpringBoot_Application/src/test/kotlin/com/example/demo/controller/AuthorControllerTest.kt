package com.example.demo.controller

import com.example.demo.model.Author
import com.example.demo.service.AuthorService
import com.example.demo.service.AuthorServiceImpl
import org.junit.jupiter.api.TestInstance
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks

import org.mockito.Mock
import org.springframework.http.HttpStatus

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthorControllerTest {
    @MockK
    lateinit var authorService:AuthorServiceImpl

    @InjectMockKs
    lateinit var authorController: AuthorController

    @BeforeAll
    fun setUp() = MockKAnnotations.init(this)

    fun getAuthorInstance()= Author(
        id = 1,
        "newAuthor"
    )

    @Test
    fun testGetAllAuthors(){
        val authorObj=getAuthorInstance()
        every {
            authorService.getAllAuthors(
            )
        } returns listOf(authorObj)
        val result = authorController.getAllAuthorDetails()
        Assertions.assertEquals(result.statusCode, HttpStatus.FOUND)
        println("$result.body + ${listOf(authorObj)}")
        Assertions.assertEquals(result.body, listOf(authorObj))
    }

    @Test
    fun testGetAuthorsByAuthorName(){
        val authorObj=getAuthorInstance()
        every {
            authorService.getDetails(
                any()
            )
        } returns listOf(authorObj)
        val result = authorController.findByName(authorObj.authName)
        Assertions.assertEquals(result.statusCode, HttpStatus.FOUND)
        Assertions.assertEquals(result.body, listOf(authorObj))
    }




}