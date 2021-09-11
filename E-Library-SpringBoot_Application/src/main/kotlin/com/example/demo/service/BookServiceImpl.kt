package com.example.demo.service.impl

import com.example.demo.model.Book
import com.example.demo.repository.*
import com.example.demo.service.BookService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class BookServiceImpl(val bookRepository: BookRepository, val authorRepository: AuthorRepository, val itemRepository:ItemDao): BookService {

    @Override
    override fun saveBook(book: Book): Book {
        val bookSaved= bookRepository.save(book)
        val savingToRedis = Item(bookSaved.id,bookSaved.bookName)
        itemRepository.save(savingToRedis)
        return bookSaved
    }

    @Override
    override fun deleteBook(id: Long): Book? {
        val delBook= bookRepository.findByIdOrNull(id)
        if(delBook!=null){
            val bookDeleted=bookRepository.delete(delBook)
            itemRepository.deleteItem(id.toString())
        }
        return delBook
    }


    @Override
    override fun getAllBook(pageNo:Int,pageSize:Int): List<Book> {
        val paging:Pageable = PageRequest.of(pageNo,pageSize)
        return bookRepository.findAll(paging).content
        /*if(pageResult.hasContent()){
            return pageResult.getContent()
        }
        else{
            return ArrayList<Book>()
        }*/
    }



    @Override
    override fun getBookById(id: Long): Any? {
        println("B")
        val radisData=itemRepository.findItemById(id.toString())
        return radisData
        /*if(radisData!=null){
            return radisData
        }
        println("D")
        val retrievedBook=bookRepository.getById(id)
        val savingToRedis = Item(retrievedBook.id,retrievedBook.bookName)
        itemRepository.save(savingToRedis)
        return retrievedBook
        */


    }


    @Override
    override fun getDetails(Bname: String): List<Book> {
        return bookRepository.findByBookName(Bname)
    }



    @Override
    override fun getBookByAuthor(id: Long): List<Book> {
        return bookRepository.findByAuthorId(id)
    }


    @Override
    override fun getBookByAuthorName(authName: String): List<Book> {
        val authDetails=authorRepository.findByAuthName(authName)
        return bookRepository.findByAuthor(authDetails[0])
    }

    @Override
    override fun getTextualSearch(str: String,pageNo: Int,pageSize: Int):List<Book> {
        println("called getTextualSearch Method")
        val pageable:Pageable=PageRequest.of(pageNo,pageSize)
        return bookRepository.applyTextualSearchBook(str,pageable).content

    }

    /*
    @Override
    override fun getBookByAuthorIdByDB(id: Long,pageNo: Int,pageSize: Int): List<Book> {
        val pageable:Pageable=PageRequest.of(pageNo,pageSize)
        val pageResult:Page<Book> = bookRepository.getBookByAuthorIdByDB(id,pageable)
        return if(pageResult.hasContent()){
            pageResult.getContent()
        } else{
            ArrayList<Book>()
        }
    }
    @Override
    override fun getAllBookByDB(): List<Book> {
        return bookRepository.getAllBookByDB()
    }
    @Override
    override fun getBookByIdByDB(id: Long): Book {
        return bookRepository.getBookByIdByDB(id)
    }
    @Override
    override fun findByBookNameByDB(Bname: String): List<Book> {
        return bookRepository.findByBookNameByDB(Bname)
    }


    @Override
    override fun getBookByAuthorNameByDB(authName: String,pageNo: Int,pageSize: Int): List<Book> {
        val pageable:Pageable=PageRequest.of(pageNo,pageSize)
        val pageResult:Page<Book> = bookRepository.getBookByAuthorNameByDB(authName,pageable)
        return if(pageResult.hasContent()){
            pageResult.getContent()
        } else{
            ArrayList<Book>()
        }
    }

     */

}