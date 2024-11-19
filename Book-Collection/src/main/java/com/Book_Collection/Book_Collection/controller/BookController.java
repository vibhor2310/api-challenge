package com.Book_Collection.Book_Collection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Book_Collection.Book_Collection.dto.ResponseMessageDto;
import com.Book_Collection.Book_Collection.exception.ResourceNotFoundException;
import com.Book_Collection.Book_Collection.model.Book;
import com.Book_Collection.Book_Collection.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	// to retrieve all books
	
	@GetMapping("/book/all")
	public ResponseEntity<?> getAllBook() {
		List<Book> list = bookService.getAllBook();
		return ResponseEntity.ok(list);
	}
	
	
	
	// to retrieve book by its ISBN number
	
	@GetMapping("/book/get/{isbn}")
	public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn,ResponseMessageDto dto){
		
		Book book = null;
		try {
			book = bookService.validate(isbn); // validates the ISBN
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		return ResponseEntity.ok(book);
		
	}
	
	
	
	// to add a book
	
	@PostMapping("/book/add")
	public ResponseEntity<?> addBook(@RequestBody Book book,ResponseMessageDto dto) {
		
		String isbn = book.getIsbn();
		
		try {
		  bookService.validateAlreadyPresent(isbn);
			
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		book = bookService.insert(book);
		return ResponseEntity.ok(book);
	}
	
	
	
	// to update an existing book;
	
	@PutMapping("/book/update/{isbn}")
	public ResponseEntity<?> updateBook(@PathVariable String isbn,@RequestBody Book newBook,ResponseMessageDto dto) {
		
		// validate isbn;
		
		try {
			Book existingBookDb = bookService.validate(isbn);
			
			if(newBook.getAuthor()!=null)
				existingBookDb.setAuthor(newBook.getAuthor());
			if(newBook.getTitle()!=null)
				existingBookDb.setTitle(newBook.getTitle());
			if(newBook.getYear()!=0)
				existingBookDb.setYear(newBook.getYear());
			
			//re save the existing book having the new updated value 
			existingBookDb = bookService.insert(existingBookDb);
			return ResponseEntity.ok(existingBookDb);
			
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
	}
	
	
	
	// to delete the book by its ISBN
	
	@DeleteMapping("/book/delete/{isbn}")
	public ResponseEntity<?> deleteByISBN(@PathVariable String isbn,ResponseMessageDto dto) {
		
		try {
			bookService.validate(isbn); // validates the ISBN
			bookService.delete(isbn);   // to delete the book by ISBN
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		dto.setMsg("Book deleted ...");
		return ResponseEntity.ok(dto);
		
	}
	
	
	
	
	
	
	

}
