package com.library.springlibrary.service;

import com.library.springlibrary.exceptions.BookNotFoundException;
import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.model.dto.PublicationCommentDto;
import com.library.springlibrary.model.dto.mapper.BookDtoMapper;
import com.library.springlibrary.repository.BookRepository;
import com.library.springlibrary.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Year;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yml")
public class BookServiceTest_RU {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookDtoMapper bookDtoMapper;

    @BeforeEach
    void setUp() {
        BookDto bookDto1 = getExampleBookDataOne();
        BookDto bookDto2 = getExampleBookDataTwo();
        BookDto bookDto3 = getExampleBookDataThree();
        //clearing db from testdata inserted by liquibase
        bookService.addBook(bookDto1);
        bookService.addBook(bookDto2);
        bookService.addBook(bookDto3);
    }
    @Test
    void should_add_new_comment_to_book_with_id_1() {
        //given - beforeEach
        String username = "aboshii";
        String comment = "testowy komentarz";
        //when
        bookService.addComment(1L, username, comment);
        //then
        assertEquals(bookService.getBookById(1L).getCommentaryList().get(0).getUsername(), username);
        assertEquals(bookService.getBookById(1L).getCommentaryList().get(0).getDescription(), comment);
    }
    @Test
    void should_set_book_publication_year_to_current_year_when_wrong_publication_date() {
        //given - beforeEach
        BookDto bookDto = getExampleBookWithWrongPublicationYearData();
        bookService.addBook(bookDto);
        //when
        printBooks();
        //then
        assertEquals(bookService.getBookById(4L).getPublicationYear(), Year.of(Year.now().getValue()));
    }
    @Test
    void should_add_new_comment_from_publicationCommentDto_object_to_book_with_id_1() {
        //given - beforeEach
        //when
        String username = "aboshii";
        String comment = "testowy komentarz";
        PublicationCommentDto publicationCommentDto =
                new PublicationCommentDto(username, comment);
        bookService.addComment(1L, publicationCommentDto);
        //then
        assertEquals(bookService.getBookById(1L).getCommentaryList().get(0).getUsername(), username);
        assertEquals(bookService.getBookById(1L).getCommentaryList().get(0).getDescription(), comment);
    }
    @Test
    void should_borrow_book_with_id_1_for_user_with_id_1() {
        //given - beforeEach
        //when
        bookService.borrowBook(bookRepository.findById(1L), userRepository.findById(1L));
        //then
        assertTrue(bookService.getBookById(1L).getBorrower().getNickname().equals("aboshii"));
    }
    @Test
    void should_get_any_book_by_title_and_compare_its_ISBN_with_example_book_ISBN() {
        //given - beforeEach
        //when
        //then
        assertEquals(bookService.getBookByTitle(getExampleBookDataOne().getTitle()).getISBN(), "917-3584-5564");
    }
    @Test
    void should_get_list_of_books_and_print_title(){
        //given
        //when
        bookService.addBook(getExampleBookDataTwo());
        bookService.addBook(getExampleBookDataThree());
        //then
        ArrayList<Book> books = bookService.getBooks();

        assertEquals(getExampleBookDataOne().getISBN(), books.get(0).getISBN());
        assertEquals(getExampleBookDataTwo().getISBN(), books.get(1).getISBN());
        assertEquals(getExampleBookDataThree().getISBN(), books.get(2).getISBN());
    }
    @Test
    void should_replace_book_with_id_1L_for_book_from_data_three(){
        //given

        //when
        bookService.replaceBook(1L, getExampleBookDataThree());
        //then
        assertEquals(getExampleBookDataThree().getISBN(), bookService.getBookById(1L).getISBN());
    }
    @Test
    void should_try_to_replace_book_with_id_1L_and_get_empty_Optional(){
        //given
        long param = 20L;
        //when
        Optional<BookDto> bookDto = bookService.replaceBook(20L, getExampleBookDataThree());
        //then
        assertTrue(bookDto.isEmpty());
    }
    @Test
    void should_update_book_data_with_id_1L_from_body(){
        //given
        long param = 1L;
        BookDto bookDto = getExampleBookDataThree();
        bookDto.setId(param);
        //when
        bookService.updateBook(bookDto);
        //then
        assertEquals(bookService.getBookById(param).getTitle(), bookDto.getTitle());
        assertEquals(bookService.getBookById(param).getISBN(), bookDto.getISBN());
    }
    @Test
    void should_try_to_update_book_and_throw_BookNotFoundException(){
        //given
        long param = 6L;
        BookDto bookDto = getExampleBookWithWrongPublicationYearData();
        bookDto.setId(param);
        //when
        BookNotFoundException thrown = assertThrows(
                BookNotFoundException.class,
                () -> bookService.updateBook(bookDto),
                "Expected \"Book not found\" but something went wrong"
        );
        //then
        assertTrue(thrown.getMessage().contains("Book not found"));
    }

    private static BookDto getExampleBookDataOne() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Moc 1974");
        bookDto.setId(1L);
        bookDto.setPublicationYear(Year.of(1974));
        bookDto.setPublisher("MAG, Jacek Rodek");
        bookDto.setAuthorFirstName("Tomas");
        bookDto.setAuthorLastName("Rożek");
        bookDto.setISBN("917-3584-5564");
        return bookDto;
    }

    private static BookDto getExampleBookDataTwo() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Droga Królów");
        bookDto.setId(2L);
        bookDto.setPublicationYear(Year.of(2013));
        bookDto.setPublisher("MAG, Jacek Rodek");
        bookDto.setAuthorFirstName("Brandon");
        bookDto.setAuthorLastName("Sanderson");
        bookDto.setISBN("978-83-66712-37-9");
        return bookDto;
    }
    private static BookDto getExampleBookDataThree() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Magical Kitchen: The Unofficial Harry Potter Cookbook");
        bookDto.setId(3L);
        bookDto.setPublicationYear(Year.of(2018));
        bookDto.setPublisher("Katarzyna Kronenberger");
        bookDto.setAuthorFirstName("K.T.");
        bookDto.setAuthorLastName("Crownhill");
        bookDto.setISBN("978-83-951679-0-4");
        return bookDto;
    }
    private static BookDto getExampleBookWithWrongPublicationYearData() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Moc 1974");
        bookDto.setPublicationYear(Year.of(2100));
        bookDto.setPublisher("Mag");
        bookDto.setAuthorFirstName("Tomas");
        bookDto.setAuthorLastName("Rożek");
        bookDto.setISBN("917-3584-5564");
        return bookDto;
    }
    private void printBooks() {
        for (Book book : bookService.getBooks()) {
            System.out.print(book.getId() + ", " + book.getTitle());
            System.out.println();
        }
    }
}
