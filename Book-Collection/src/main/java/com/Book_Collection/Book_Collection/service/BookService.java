package com.Book_Collection.Book_Collection.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Book_Collection.Book_Collection.exception.ResourceNotFoundException;
import com.Book_Collection.Book_Collection.model.Book;
import com.Book_Collection.Book_Collection.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}

	public Book validate(String isbn) throws ResourceNotFoundException {
		Optional<Book> optional = bookRepository.findById(isbn);
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Given isbn number is invalid try again...");
		}
		
		Book book = optional.get();
		
		return book;
		
	}

	public Book insert(Book book) {
		
		return bookRepository.save(book);
	}

	public void delete(String isbn) {
		
		bookRepository.deleteById(isbn);
		
	}

	public void validateAlreadyPresent(String isbn) throws ResourceNotFoundException {
		Optional<Book> optional = bookRepository.findById(isbn);
		if(!optional.isEmpty()) {
			throw new ResourceNotFoundException("Book already present with the entered isbn number...");
		}

		Book book = optional.get();
		
//		return book;
		
		
	}

}
