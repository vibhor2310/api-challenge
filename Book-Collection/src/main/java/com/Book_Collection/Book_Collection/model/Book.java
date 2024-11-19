package com.Book_Collection.Book_Collection.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String author;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(unique = true)
	private String isbn;
	
	@Column(nullable = false)
	private int year;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Book(String title, String author, String isbn, int year) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.year = year;
	}
	
	
	

	

}
