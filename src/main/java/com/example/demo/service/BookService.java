package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Book;
import com.example.demo.domain.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	//獲取全部Book
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
	
	//透過ID獲取單筆Book
	public Optional<Book> findOne(Long id){
		return bookRepository.findById(id);
	}
	
	//新增單筆Book
	public Book add(Book book) {
		return bookRepository.save(book);
	}
	
	//依照條件獲取單/多筆Book
	public List<Book> findBookByAuthor(String author){
		return bookRepository.findByAuthor(author);
	}
	
	//依照條件獲取單/多筆Book
	public List<Book> findBookByAuthorAndStatus(String author, String status){
		return bookRepository.findByAuthorAndStatus(author, status);
	}
	
}
