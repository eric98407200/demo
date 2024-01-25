package com.example.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api/v1")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public List<Book> getAll(){
		return bookService.findAll();
	}
	
	@GetMapping("/books/{id}")
	public Optional<Book> getALl(@PathVariable Long id){
		System.out.println(bookService.findOne(id));
		return bookService.findOne(id);
	}
	
	@PostMapping("/books")
	public Book add(@RequestParam String name,
					@RequestParam String author,
					@RequestParam String descript,
					@RequestParam String status) {
		Book book = new Book();
		book.setId(0);
		book.setName(name);
		book.setAuthor(author);
		book.setDescript(descript);
		book.setStatus(status);
		return bookService.add(book);
	}
	
	
	@PostMapping("/books/by")
	public List<Book> findByAuthor(@RequestParam String author){
		return bookService.findBookByAuthor(author);
	}
	
	@PostMapping("/books/AS")
	public List<Book> findBy(@RequestParam String author, @RequestParam String status){
		return bookService.findBookByAuthorAndStatus(author, status);
	}
	
}
