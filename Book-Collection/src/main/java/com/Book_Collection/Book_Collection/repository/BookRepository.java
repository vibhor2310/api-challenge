package com.Book_Collection.Book_Collection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Book_Collection.Book_Collection.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,String>{

}
