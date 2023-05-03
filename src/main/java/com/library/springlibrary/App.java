package com.library.springlibrary;

import com.library.springlibrary.model.Publication;
import com.library.springlibrary.repository.BookRepository;
import com.library.springlibrary.repository.UserRepository;
import com.library.springlibrary.service.BookService;
import com.library.springlibrary.service.UserService;
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
/*		bookService.addBook(new BookDto("W pustyni i w puszczy",
				Year.of(2014), "Znak Emotikon", "Henryk", "Sienkiewicz", "978-83-240-2959-4"));
		bookService.addBook(new BookDto("Przygody Jasia Fasoli",
				Year.of(1986), "Jaś Wedrowniś", "Jaś", "Fasola", "978-83-61387"));*/
		//Book bookById = bookService.getBookById(1L);
		//System.out.println(bookById.toString());
		BookRepository bookRepository = context.getBean(BookRepository.class);
		UserRepository userRepository = context.getBean(UserRepository.class);
		bookService.borrowBook(bookRepository.findById(1L), userRepository.findById(1L));
		System.out.println(userService.getUserBooksByUserId(2L));
		new Scanner(System.in).nextLine();
	}
}