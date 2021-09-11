package com.example.demo.model

import lombok.Data
import org.hibernate.annotations.Check
import java.time.LocalDateTime
import javax.persistence.*
import java.io.Serializable

@Data
@Entity
@Table(name = "book_table")
@Check(constraints = "category IN ('Crime', 'Horror', 'Thriller', 'Romance', 'Comedy', 'Drama')")
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long,

    @Column(name = "BookName")
    val bookName: String,

    @Column(name = "no_pages")
    val noPage: Int,

    @Column(name = "isbn_no")
    val isbn: Int,

    @Column(name="category")
    val category:String,

    @Column(name="added_on")
    val added_on: LocalDateTime?=LocalDateTime.now(),

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name="auth_id",nullable = true)
    val author:Author,
):Serializable
