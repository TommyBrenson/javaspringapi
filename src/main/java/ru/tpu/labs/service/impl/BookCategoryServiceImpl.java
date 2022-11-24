package ru.tpu.labs.service.impl;

import ru.tpu.labs.dto.BookCategoryReqDto;
import ru.tpu.labs.dto.BookCategoryResDto;
import ru.tpu.labs.model.BookCategory;
import ru.tpu.labs.repository.BookCategoryRepository;
import ru.tpu.labs.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {
    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Override
    public List<BookCategoryResDto> findAllBookCategory() {
//        List<BookCategoryResDto> bookCategoryResDto = new ArrayList<>();
//        List<BookCategory> bookCategories = bookCategoryRepository.findAll();
//        for(BookCategory bookCategory : bookCategories){
//            bookCategoryResDto.add(new BookCategoryResDto(bookCategory.getId(),bookCategory.getName()));
//        }
//        return bookCategoryResDto;
        List<BookCategory> bookCategories = bookCategoryRepository.findAll();
        List<BookCategoryResDto> bookCategoryResDto = bookCategories.stream().map(this::mapToProductResponse).collect(Collectors.toList());
        return bookCategoryResDto;
    }

    private BookCategoryResDto mapToProductResponse(BookCategory bookCategory) {
        return BookCategoryResDto.builder()
                .id(bookCategory.getId())
                .name(bookCategory.getName())
                .build();
    }

    @Override
    public BookCategoryResDto findBookCategoryById(Long id) {
//        BookCategory bookCategory = bookCategoryRepository.findById(id).orElse(new BookCategory());
//        return bookCategory;
//          return bookCategoryRepository.findById(id).get();
        /*
            BookCategoryResDto bookCategoryResDto = new BookCategoryResDto();
            BookCategory bookCategory = bookCategoryRepository.findById(id).orElse(new BookCategory());
            bookCategoryResDto.setId(bookCategory.getId());
            bookCategoryResDto.setName(bookCategory.getName());
            return bookCategoryResDto; */
        BookCategory bookCategory = bookCategoryRepository.findById(id).get();
        BookCategoryResDto bookCategoryResDto = mapToProductResponse(bookCategory);
        return bookCategoryResDto;
    }

    @Override
    public BookCategory saveOrUpdateBookCategory(BookCategory bookCategory) {
        return bookCategoryRepository.save(bookCategory);
    }

    @Override
    public BookCategoryResDto updateBookCategory(Long id, BookCategoryReqDto bookCategoryReqDto) {
        BookCategory bookCategory = bookCategoryRepository.findById(id).get();
        BookCategory bookEdit = new BookCategory();
        bookEdit.setId(bookCategory.getId());
        bookEdit.setName(bookCategoryReqDto.getName());

        BookCategory result = bookCategoryRepository.save(bookEdit);

        BookCategoryResDto bookCategoryResDto = new BookCategoryResDto();
        bookCategoryResDto.setId(result.getId());
        bookCategoryResDto.setName(result.getName());
        return bookCategoryResDto;
    }

    @Override
    public BookCategoryResDto insertBookCategory(BookCategoryReqDto bookCategoryReqDto) {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setName(bookCategoryReqDto.getName());
        BookCategory result = bookCategoryRepository.save(bookCategory);

        BookCategoryResDto bookCategoryResDto = new BookCategoryResDto();
        bookCategoryResDto.setId(result.getId());
        bookCategoryResDto.setName(result.getName());
        return bookCategoryResDto;
    }

    @Override
    public void deleteBookCategory(Long id) {
//        BookCategory bookCategory = findBookCategoryById(id);
        BookCategory bookCategory = bookCategoryRepository.findById(id).orElse(new BookCategory());
        bookCategoryRepository.delete(bookCategory);
    }
}
