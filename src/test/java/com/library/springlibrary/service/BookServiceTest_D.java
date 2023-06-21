package com.library.springlibrary.service;

import com.library.springlibrary.exceptions.BookNotFoundException;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.model.dto.mapper.BookDtoMapper;
import com.library.springlibrary.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yml")
public class BookServiceTest_D {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookDtoMapper bookDtoMapper;

    @Test
    void should_delete_book_with_id_1L_and_throw_BookNotFoundException() {
        //given
        long param = 1L;
        //when
        bookService.deleteBookById(param);
        //then
        BookNotFoundException thrown = assertThrows(
                BookNotFoundException.class,
                () -> bookService.getBookById(param),
                "Expected \"Book not found\" but something went wrong"
        );
        assertTrue(thrown.getMessage().contains("Book not found"));
    }

    private static BookDto getExampleBookData() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Moc 1974");
        bookDto.setPublicationYear(Year.of(1974));
        bookDto.setPublisher("Mag");
        bookDto.setAuthorFirstName("Tomas");
        bookDto.setAuthorLastName("Rożek");
        bookDto.setISBN("917-3584-5564");
        return bookDto;
    }
}
