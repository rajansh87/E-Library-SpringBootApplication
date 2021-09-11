package com.example.demo.service

import com.example.demo.model.Author
import com.example.demo.repository.AuthorRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service


@Service
class AuthorServiceImpl(val authorRepository: AuthorRepository): AuthorService {

    @Override
    override fun getDetails(authName: String): List<Author> {
        return authorRepository.findByAuthName(authName)
    }

    @Override
    override fun getAllAuthors(): List<Author> {
        return authorRepository.getAllAuthors()
    }


    /*
    @Override
    override fun getAllAuthorsWithPagination(start: Int, size: Int): List<Author> {
        val pageable: PageRequest = PageRequest.of(start,size)
        val listAuthor = authorRepository.findAll(pageable)
        return if (listAuthor.hasContent()) {
            listAuthor.content
        } else {
            ArrayList()
        }
    }*/

    /*

    @Override
    override fun getAuthorDetailsByDB(authName: String): List<Author> {
        return authorRepository.getAuthorDetailsByDB(authName)
    }
     */


}