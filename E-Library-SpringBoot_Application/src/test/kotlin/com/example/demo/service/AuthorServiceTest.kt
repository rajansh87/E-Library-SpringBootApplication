package com.example.demo.service

import com.example.demo.controller.AuthorController
import com.example.demo.model.Author
import com.example.demo.repository.AuthorRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthorServiceTest {
    @MockK
    lateinit var authorRepository: AuthorRepository

    @InjectMockKs
    lateinit var authorService:AuthorServiceImpl

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
            authorRepository.getAllAuthors(
            )
        } returns listOf(authorObj)
        val result = authorService.getAllAuthors()
        Assertions.assertEquals(result, listOf(authorObj))
    }

    @Test
    fun testGetAuthorsByAuthorName(){
        val authorObj=getAuthorInstance()
        every {
            authorRepository.findByAuthName(
                any()
            )
        } returns listOf(authorObj)
        val result = authorService.getDetails(authorObj.authName)
        Assertions.assertEquals(result, listOf(authorObj))
    }




}