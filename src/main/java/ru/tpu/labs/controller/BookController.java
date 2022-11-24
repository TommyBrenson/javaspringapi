package ru.tpu.labs.controller;

import ru.tpu.labs.dto.BookReqDto;
import ru.tpu.labs.dto.BookResDto;
import ru.tpu.labs.dto.BookWithCategoryResDto;
import ru.tpu.labs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/book")
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookResDto> findAllBook(){
        return bookService.findAllBook();
    }

    @GetMapping("/withcategory")
    public List<BookWithCategoryResDto> findAllBookWithCategory(){
        return bookService.findAllWithCategory();
    }

    @GetMapping("/{id}")
    public BookResDto findBookById(@PathVariable("id") Long id){
        return bookService.findById(id);
    }

    @PostMapping
    public BookResDto insertBook(@RequestBody BookReqDto book){
        return bookService.insertBook(book);
    }

    @PutMapping("/{id}")
    public BookResDto updateBook(@PathVariable("id") Long id, @RequestBody BookReqDto book){
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return "Book id " + id.toString() + "deleted";
    }


//    @GetMapping("/{id}")
//    public Book getBook(@PathVariable("id") Long id){
//        Book book1 = new Book();
//        book1.setId(id);
//        book1.setTitle("Spring Boot in Action");
//        book1.setWritter("Ridwanda");
//        book1.setIsbn("ISBN-99883344");
//        return book1;
////        return new Book("Spring in Action","Ridwanda Safatain","IS-99887766");
//    }
//
//    @GetMapping
//    public List<Book> getBooks(){
//        List<Book> books = new ArrayList<>();
//        books.add(new Book("Spring in Action", "Ridwanda Safatain","ISBN-99887766"));
//        books.add(new Book("Java Fundamentals", "Budi Sutejo", "ISBN-998877665"));
//        books.add(new Book("JPA in Action", "Mickhael Mors", "ISBN-99887755"));
//        return books;
//    }
}
