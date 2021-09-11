package com.example.demo.repository

import com.example.demo.model.Author
import com.example.demo.model.Book
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface BookRepository : JpaRepository<Book, Long>,PagingAndSortingRepository<Book, Long>{

    fun findByBookName(Bname:String):List<Book>

    fun findByAuthorId(id:Long): List<Book>

    fun findByAuthor(author: Author):List<Book>

    @Query(value = "Select * from book_table where book_name LIKE %:str% ORDER BY '/*#pageable*/'",nativeQuery = true)
    fun applyTextualSearchBook(str: String,pageable: org.springframework.data.domain.Pageable):Page<Book>



    /*
     @Query(value = "Select * from book_table",nativeQuery = true)
    fun getAllBookByDB():List<Book>
    @Query(value = "Select * from book_table where id=:id",nativeQuery = true)
    fun getBookByIdByDB(id:Long):Book
    @Query(value = "Select * from book_table where book_name=:Bname",nativeQuery = true)
    fun findByBookNameByDB(Bname:String):List<Book>
    @Query(value = "Select * from book_table where auth_id=:id ORDER BY '/*#pageable*/'",nativeQuery = true)
    fun getBookByAuthorIdByDB(id:Long, pageable: org.springframework.data.domain.Pageable): Page<Book>
    @Query(value = "select * from book_table where auth_id=(select id from author_table where author_name=:authName) ORDER BY '/*#pageable*/'",nativeQuery = true)
    fun getBookByAuthorNameByDB(authName:String,pageable: org.springframework.data.domain.Pageable):Page<Book>
     */

}
