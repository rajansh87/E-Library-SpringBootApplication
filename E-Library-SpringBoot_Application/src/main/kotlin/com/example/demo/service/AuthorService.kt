package com.example.demo.service

import com.example.demo.model.Author

interface AuthorService {

    fun getDetails(authName:String): List<Author>
    fun getAllAuthors():List<Author>
    //fun getAllAuthorsWithPagination(start:Int,size:Int):List<Author>

    //fun getAuthorDetailsByDB(authName:String):List<Author>

}