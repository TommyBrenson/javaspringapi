package ru.tpu.labs.service;

import ru.tpu.labs.dto.BookReqDto;
import ru.tpu.labs.dto.BookResDto;
import ru.tpu.labs.dto.BookWithCategoryResDto;

import java.util.List;

public interface BookService {
    List<BookResDto> findAllBook();
    List<BookWithCategoryResDto> findAllWithCategory();
    BookResDto findById(Long id);
    BookResDto updateBook(Long id,BookReqDto book);
    BookResDto insertBook(BookReqDto book);
    void deleteBook(Long id);
}
