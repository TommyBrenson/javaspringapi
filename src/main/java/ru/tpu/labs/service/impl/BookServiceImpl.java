package ru.tpu.labs.service.impl;

import ru.tpu.labs.dto.BookReqDto;
import ru.tpu.labs.dto.BookResDto;
import ru.tpu.labs.dto.BookWithCategoryResDto;
import ru.tpu.labs.model.Book;
import ru.tpu.labs.model.BookCategory;
import ru.tpu.labs.repository.BookRepository;
import ru.tpu.labs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookResDto> findAllBook() {
        List<BookResDto> bookDto = new ArrayList<>();
        List<Book> books = bookRepository.findAll();
        for(Book book : books){
            bookDto.add(new BookResDto(book.getId(),book.getTitle(),book.getWriter(),book.getIsbn()));
        }
        return bookDto;
    }

    @Override
    public List<BookWithCategoryResDto> findAllWithCategory() {
        List<Book> books = bookRepository.findAll();
        List<BookWithCategoryResDto> bookDto = new ArrayList<>();
        for(Book book : books){
            bookDto.add(BookWithCategoryResDto.builder().id(book.getId())
                    .title(book.getTitle())
                    .writter(book.getWriter())
                    .isbn(book.getIsbn())
                    .categoryId(book.getBookCategory().getId())
                    .categoryName(book.getBookCategory().getName())
                    .build());
        }
        return bookDto;
    }

    @Override
    public BookResDto findById(Long id) {
        BookResDto bookResDto = new BookResDto();
        Book book = bookRepository.findById(id).orElse(new Book());
        bookResDto.setId(book.getId());
        bookResDto.setTitle(book.getTitle());
        bookResDto.setWritter(book.getWriter());
        bookResDto.setIsbn(book.getIsbn());

        return bookResDto;
    }

    @Override
    public BookResDto updateBook(Long id, BookReqDto bookReqDto) {
        Book editBook = Book.builder().id(id).title(bookReqDto.getTitle())
                .writer(bookReqDto.getWritter()).isbn(bookReqDto.getIsbn())
                .bookCategory(BookCategory.builder().id(bookReqDto.getCategoryId()).build()).build();
        Book result = bookRepository.save(editBook);

        return BookResDto.builder().id(result.getId()).title(result.getTitle())
                .writter(result.getWriter()).isbn(result.getIsbn()).build();
    }

    @Override
    public BookResDto insertBook(BookReqDto bookReqDto) {
        Book book = new Book();
        book.setTitle(bookReqDto.getTitle());
        book.setWriter(bookReqDto.getWritter());
        book.setIsbn(bookReqDto.getIsbn());
        book.setBookCategory( BookCategory.builder().id(bookReqDto.getCategoryId()).build());
        Book result = bookRepository.save(book);

        return BookResDto.builder().id(result.getId()).title(result.getTitle())
                .writter(result.getWriter()).isbn(result.getIsbn()).build();
    }

    @Override
    public void deleteBook(Long id) {
//        Book book = findById(id);
//        bookRepository.delete(book);
        bookRepository.deleteById(id);
    }
}
