package com.Book_Collection.Book_Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.Book_Collection.Book_Collection.exception.ResourceNotFoundException;
import com.Book_Collection.Book_Collection.model.Book;
import com.Book_Collection.Book_Collection.repository.BookRepository;
import com.Book_Collection.Book_Collection.service.BookService;


@SpringBootTest
public class BookServiceTest {
	
	@InjectMocks
	private BookService bookService;
	
	@Mock
	private BookRepository bookRepository;
	
	private Book book;
	
	private List<Book> list;
	
	@BeforeEach
	public void initSetup() {
		book = new Book("The book","Vibhor","12",2024);
		list = Arrays.asList(book, new Book("book-2","Vibhor-2","13",2021));
		
	}
	

	@Test
	public void insertTest() {
		//arrangement using mocking 
		when(bookRepository.save(book)).thenReturn(book);
		
		//Act : Calling the actual method 
		Book newBook =   bookService.insert(book); 
		
		//test and compare 
		assertNotNull(newBook);
		//assertEquals(customer.getName(), newCustomer.getName());
		verify(bookRepository, times(1)).save(book);
	}
	
	@Test
	public void validateTest() {
		//arrange
		when(bookRepository.findById("15")).thenReturn(Optional.of(book));
				
		//act
		Book newBook = null;
		try {
			newBook=  bookService.validate("15");
		} catch (ResourceNotFoundException e) { }
				
		//test 
		assertNotNull(newBook);
		assertEquals(book.getAuthor(), newBook.getAuthor());
				
		//verify that repository method is getting called only once
		verify(bookRepository, times(1)).findById("15");
	}
	
	@Test
	public void validateTestNotExist(){
		//arrange
		when(bookRepository.findById("0")).thenReturn(Optional.empty());
		
		//act
				Book newBook = null;
				try {
					newBook =  bookService.validate("0");
				} catch (ResourceNotFoundException e) { 
					assertEquals("Given isbn number is invalid try again...".toLowerCase(),
							e.getMessage().toLowerCase());
				}
			assertNull(newBook);
			
		//verify that repository method is getting called only once
		verify(bookRepository, times(1)).findById("0");
	}
	
	@Test
	public void getAllBookTest() {
		// Arrangement using mocking 
		when(bookRepository.findAll()).thenReturn(list); 
		
		// Act: Calling the actual method
		List<Book> result = bookService.getAllBook(); 
		
		// Test and compare 
		assertEquals(list, result); 
		
		// Verify that findAll was called
		verify(bookRepository).findAll(); // Verify that findAll was called
	}
	
	
	
	
	
	
	
	

}
