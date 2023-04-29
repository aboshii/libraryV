package com.library.springlibrary;

import com.library.springlibrary.repository.BookRepository;
import com.library.springlibrary.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		BookService bookService = context.getBean(BookService.class);
/*		bookService.addBook(new BookDto("W pustyni i w puszczy",
				Year.of(2014), "Znak Emotikon", "Henryk", "Sienkiewicz", "978-83-240-2959-4"));
		bookService.addBook(new BookDto("Przygody Jasia Fasoli",
				Year.of(1986), "Jaś Wedrowniś", "Jaś", "Fasola", "978-83-61387"));*/
		//Book bookById = bookService.getBookById(1L);
		//System.out.println(bookById.toString());
		BookRepository bookRepository = context.getBean(BookRepository.class);
		bookRepository.findAll().forEach(System.out::println);
		new Scanner(System.in).nextLine();
	}
}
