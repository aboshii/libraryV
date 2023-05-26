package com.library.springlibrary.service;

import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.model.dto.mapper.BookDtoMapper;
import com.library.springlibrary.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yml")
public class AddBookTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookDtoMapper bookDtoMapper;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository, bookDtoMapper);
    }
    @Test
    void shouldAddNewBookToRepo() {
        //given
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Moc 1974");
        bookDto.setPublicationYear(Year.of(1974));
        bookDto.setPublisher("Mag");
        bookDto.setAuthorFirstName("Tomas");
        bookDto.setAuthorLastName("Rożek");
        bookDto.setISBN("917-3584-5564");
        //when
        bookService.addBook(bookDto);
        //then
        assertTrue(bookDto.getTitle().equals(bookService.getBookById(1L).getTitle()));
    }

}
