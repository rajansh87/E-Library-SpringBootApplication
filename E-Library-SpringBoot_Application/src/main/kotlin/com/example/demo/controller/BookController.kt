package com.example.demo.controller

import com.example.demo.model.Book
import com.example.demo.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/")
class BookController(val bookService: BookService) {//remove by db



    @PostMapping("/save-book")
    fun saveBook(@RequestBody book:Book):ResponseEntity<Book>{
        return ResponseEntity<Book>(bookService.saveBook(book),HttpStatus.CREATED)
    }

    //@CacheEvict(value= ["books"],key="#id")
    @DeleteMapping("/delete-book/{id}")
    fun deleteBook(@PathVariable("id") id:Long): ResponseEntity<Book>{
        return ResponseEntity<Book>(bookService.deleteBook(id),HttpStatus.ACCEPTED)
    }


    @GetMapping("/get-all-books")
    fun getAllBook(@RequestParam(name="pageNo",defaultValue = "0") pageNo:Int, @RequestParam(name="pageSize",defaultValue = "2") pageSize:Int):ResponseEntity<List<Book>>{
        return ResponseEntity<List<Book>>(bookService.getAllBook(pageNo,pageSize),HttpStatus.FOUND)
    }

    @GetMapping("/get-book/{id}")
    fun getBookById(@PathVariable("id") id: Long): ResponseEntity<Any>{
        return ResponseEntity<Any>(bookService.getBookById(id),HttpStatus.FOUND)
    }

    @GetMapping("/findByBookName/{Bname}")
    fun findByName(@PathVariable("Bname") Bname:String): ResponseEntity<List<Book>>{
        return ResponseEntity<List<Book>>(bookService.getDetails(Bname),HttpStatus.FOUND)
    }


    @GetMapping("/getByAuthorId/{id}")
    fun findByAuthorId(@PathVariable("id") id:Long): ResponseEntity<List<Book>>{
        return ResponseEntity<List<Book>>(bookService.getBookByAuthor(id),HttpStatus.FOUND)
    }


    @GetMapping("/getByAuthorName/{authName}")
    fun findByAuthorName(@PathVariable("authName") authName: String): ResponseEntity<List<Book>>{
        return ResponseEntity<List<Book>>(bookService.getBookByAuthorName(authName),HttpStatus.FOUND)
    }



    @GetMapping("/textualSearchBook/{str}")
    //@Cacheable(key="#str",value = ["Book"])
    fun textualSearchBook(@PathVariable("str") str: String,@RequestParam(name="pageNo",defaultValue = "0")pageNo:Int,@RequestParam(name="pageSize",defaultValue = "2")pageSize:Int):ResponseEntity<List<Book>>{
        return ResponseEntity<List<Book>>(bookService.getTextualSearch(str,pageNo,pageSize),HttpStatus.FOUND)
    }





/*

    @GetMapping("get-all-booksbyDB")
    @Query(value = "select * from book_table",nativeQuery = true)
    fun getAllBookByDB():ResponseEntity<List<Book>>{
        return ResponseEntity<List<Book>>(bookService.getAllBookByDB(),HttpStatus.FOUND)
    }

    @GetMapping("/get-book-by-id-DB/{id}")
    fun getBookByIdByDB(@PathVariable("id")id: Long): ResponseEntity<Book> {
        return ResponseEntity<Book>(bookService.getBookByIdByDB(id),HttpStatus.FOUND)
    }

    @GetMapping("/findByBookNameByDB/{Bname}")
    fun findByBookNameByDB(@PathVariable("Bname") Bname:String): ResponseEntity<List<Book>>{
        return ResponseEntity<List<Book>>(bookService.findByBookNameByDB(Bname),HttpStatus.FOUND)
    }


    @GetMapping("/getByAuthorIdByDB/{id}")
    fun findByAuthorIdByDB(@PathVariable("id") id:Long,@RequestParam(defaultValue = "0")pageNo:Int,@RequestParam(defaultValue = "2")pageSize:Int): ResponseEntity<List<Book>>{
        return ResponseEntity<List<Book>>(bookService.getBookByAuthorIdByDB(id,pageNo,pageSize),HttpStatus.FOUND)
    }


    @GetMapping("/getByAuthorNameByDB/{authName}")
    fun findByAuthorNameByDB(@PathVariable("authName") authName: String,@RequestParam(defaultValue = "0")pageNo:Int,@RequestParam(defaultValue = "2")pageSize:Int): ResponseEntity<List<Book>>{
        return ResponseEntity<List<Book>>(bookService.getBookByAuthorNameByDB(authName,pageNo,pageSize),HttpStatus.FOUND)
    }




    // http://localhost:8080/api/book
    @GetMapping("/book")
    fun method():Book{
        return Book(1,"mybook",5,101,"comedy")
    }

    // http://localhost:8080/api/books
    @GetMapping("/books")
    fun method2():ArrayList<Book>{
        val books=ArrayList<Book>()
        books.add(Book(1,"mybook",5,101,"comedy"))
        books.add(Book(2,"mybook2",6,102,"romance"))
        books.add(Book(3,"mybook3",10,103,"thriller"))
        return books
    }


    // http://localhost:8080/api/book/1/mybook/5/101/comedy
    @GetMapping("/book/{id}/{bookName}/{noPage}/{isbn}/{category}")
    fun method3(@PathVariable("id") id:Long, @PathVariable("bookName") bookName:String,
                @PathVariable("noPage") noPage:Long, @PathVariable("isbn") isbn:Long,
                @PathVariable("category") category:String):Book{

        return Book(id,bookName,noPage,isbn,category)
    }

    // http://localhost:8080/api/book/query?id=2&bookName=mybook2&noPage=6&isbn=102&category=romance
    @GetMapping("/book/query")
    fun querymethod(@RequestParam(name="id") id: Long,@RequestParam(name= "bookName") bookName:String,
                    @RequestParam(name="noPage") noPage:Long, @RequestParam(name="isbn") isbn:Long,
                    @RequestParam(name="category") category:String):Book{
        return Book(id,bookName,noPage,isbn,category)
    }


*/



}