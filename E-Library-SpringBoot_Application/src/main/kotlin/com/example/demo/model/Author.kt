package com.example.demo.model

import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "author_table")
class Author(
    @Id
    @GeneratedValue
    val id:Long,

    @Column(name= "author_name")
    val authName:String,

    ):Serializable