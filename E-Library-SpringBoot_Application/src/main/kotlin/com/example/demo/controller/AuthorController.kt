package com.example.demo.controller

import com.example.demo.model.Author
import com.example.demo.service.AuthorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/")
class AuthorController(val authorService: AuthorService) {
    @GetMapping("/getAuthorDetails/{authName}")
    fun findByName(@PathVariable("authName") authName:String):ResponseEntity<List<Author>>{
        return ResponseEntity<List<Author>>(authorService.getDetails(authName),HttpStatus.FOUND)
    }


    @GetMapping("/getAllAuthorDetails")
    fun getAllAuthorDetails():ResponseEntity<List<Author>>{
        return ResponseEntity<List<Author>>(authorService.getAllAuthors(),HttpStatus.FOUND)
    }


/*
    @GetMapping("/getAllAuthorDetails/pagination")
    fun getAllAuthorDetailsWithPagination(@RequestParam(name="start")start:Int,@RequestParam(name="size")size:Int):List<Author>{
        return authorService.getAllAuthorsWithPagination(start,size)
    }

 */


    /*
     @GetMapping("/getAuthorDetailsByDB/{authName}")
    fun findByNameByDB(@PathVariable("authName") authName:String):ResponseEntity<List<Author>>{    // implement paginiation
        return ResponseEntity<List<Author>>(authorService.getAuthorDetailsByDB(authName),HttpStatus.FOUND)
    }
     */




}