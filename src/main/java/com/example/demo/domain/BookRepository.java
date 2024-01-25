package com.example.demo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	List<Book> findByAuthor(String author);
	
	List<Book> findByAuthorAndStatus(String author, String status);
}
