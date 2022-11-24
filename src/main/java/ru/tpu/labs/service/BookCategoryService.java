package ru.tpu.labs.service;

import ru.tpu.labs.dto.BookCategoryReqDto;
import ru.tpu.labs.dto.BookCategoryResDto;
import ru.tpu.labs.model.BookCategory;

import java.util.List;

public interface BookCategoryService {
    List<BookCategoryResDto> findAllBookCategory();
    BookCategoryResDto findBookCategoryById(Long id);
    BookCategory saveOrUpdateBookCategory(BookCategory bookCategory);
    BookCategoryResDto updateBookCategory(Long id, BookCategoryReqDto bookCategoryReqDto);
    BookCategoryResDto insertBookCategory(BookCategoryReqDto bookCategoryReqDto);
    void deleteBookCategory(Long id);
}
