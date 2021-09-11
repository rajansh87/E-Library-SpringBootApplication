package com.example.demo.service

import com.example.demo.model.Book

interface BookService {
    fun saveBook(book: Book): Book
    fun getAllBook(pageNo:Int, pageSize:Int): List<Book>
    fun getBookById(id: Long): Any?
    fun getDetails(bname: String): List<Book>
    fun deleteBook(id: Long): Book?
    fun getBookByAuthor(id:Long):List<Book>
    fun getBookByAuthorName(authName:String):List<Book>

    fun getTextualSearch(str: String,pageNo: Int,pageSize: Int):List<Book>
    /*
    fun getAllBookByDB():List<Book>
    fun getBookByIdByDB(id:Long):Book
    fun findByBookNameByDB(Bname:String):List<Book>
     fun getBookByAuthorIdByDB(id: Long, pageNo: Int, pageSize: Int):List<Book>
      fun getBookByAuthorNameByDB(authName:String,pageNo: Int,pageSize: Int):List<Book>
     */


}
