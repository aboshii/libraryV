package com.library.springlibrary;

import com.library.springlibrary.controller.BookController;
import com.library.springlibrary.repository.BookRepository;
import com.library.springlibrary.repository.UserRepository;
import com.library.springlibrary.service.BookService;
import com.library.springlibrary.service.UserService;
import com.library.springlibrary.service.VisitCounter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		BookService bookService = context.getBean(BookService.class);
		UserService userService = context.getBean(UserService.class);
		VisitCounter visitCounter = context.getBean(VisitCounter.class);
		BookController bookController = context.getBean(BookController.class);
		BookRepository bookRepository = context.getBean(BookRepository.class);
		UserRepository userRepository = context.getBean(UserRepository.class);
		bookService.borrowBook(bookRepository.findById(1L), userRepository.findById(1L));
		bookService.borrowBook(bookRepository.findById(2L), userRepository.findById(1L));
	}
}