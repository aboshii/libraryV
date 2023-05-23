package com.library.springlibrary.controller.Endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.library.springlibrary.exceptions.BookNotFoundException;
import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.model.dto.mapper.BookDtoMapper;
import com.library.springlibrary.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/books",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
class BookEndpoint {
    private BookService bookService;
    private BookDtoMapper bookDtoMapper;
    private ObjectMapper objectMapper;

    @GetMapping()
    String emptyMapping() {
        return "Empty!";
    }

    @GetMapping("/")
    List<Book> getBookList() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDto> getBook(@PathVariable(required = false, name = "id") Long id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(bookDtoMapper.map(book));
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto) {
        BookDto savedBook = bookService.addBook(bookDto);
        URI savedBookUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBook.getId())
                .toUri();
        return ResponseEntity.created(savedBookUri).body(savedBook);
    }
    @PutMapping("/{id}")
    ResponseEntity<?> replaceBook(@PathVariable(required = true, name = "id") Long id,
                                  @RequestBody BookDto bookDto){
        return bookService.replaceBook(id, bookDto)
                .map(book -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> updateBook(@PathVariable(required = true, name = "id") Long id,
                                 @RequestBody JsonMergePatch patch){
        try {
            BookDto bookDto = bookService.getBookDtoById(id);
            BookDto bookDtoPatched = applyPatch(bookDto, patch);
            bookService.updateBook(bookDtoPatched);
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (JsonProcessingException | JsonPatchException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBook(@PathVariable(required = true, name = "id") Long id){
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
    private BookDto applyPatch(BookDto bookDto, JsonMergePatch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode jsonNode = objectMapper.valueToTree(bookDto);
        JsonNode jsonNodePatched = patch.apply(jsonNode);
        return objectMapper.treeToValue(jsonNodePatched, BookDto.class);
    }

}
