package com.example.demo.repository

import com.example.demo.model.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AuthorRepository: JpaRepository<Author, Long> {
       fun findByAuthName(authName:String):List<Author>

       @Query(value = "Select * from author_table ",nativeQuery = true)
       fun getAllAuthors(): List<Author>


       /*
       @Query(value = "select * from author_table where author_name=:authName",nativeQuery = true)
       fun getAuthorDetailsByDB(authName:String):List<Author>

        */


       //@Query(value = "Select * from author_table ORDER BY id OFFSET :start ROWS FETCH :size ROWS ONLY",nativeQuery = true)
       //fun getAllAuthorsWithPagination(start: Int, size: Int): List<Author>
}