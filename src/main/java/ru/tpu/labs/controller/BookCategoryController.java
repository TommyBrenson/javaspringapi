package ru.tpu.labs.controller;

import ru.tpu.labs.dto.BookCategoryReqDto;
import ru.tpu.labs.dto.BookCategoryResDto;
import ru.tpu.labs.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class BookCategoryController {
    @Autowired
    private BookCategoryService bookCategoryService;

    @GetMapping
    public List<BookCategoryResDto> findAllBookCategory(){

        return bookCategoryService.findAllBookCategory();
    }

    @GetMapping("/{id}")
    public BookCategoryResDto findBookById(@PathVariable("id")Long id){
        return bookCategoryService.findBookCategoryById(id);
    }

//    @PostMapping
//    public BookCategory saveOrUpdateBookCategory(@RequestBody BookCategory bookCategory){
//        return bookCategoryService.saveOrUpdateBookCategory(bookCategory);
//    }

    @PostMapping
    public BookCategoryResDto insert(@RequestBody BookCategoryReqDto bookCategoryReqDto){
        return bookCategoryService.insertBookCategory(bookCategoryReqDto);
    }

    @PutMapping("{id}")
    public BookCategoryResDto update(@PathVariable Long id,@RequestBody BookCategoryReqDto bookCategoryReqDto){
        return bookCategoryService.updateBookCategory(id,bookCategoryReqDto);
    }

    @DeleteMapping("/{id}")
    public String deleteBookCategory(@PathVariable("id")Long id){
        bookCategoryService.deleteBookCategory(id);
        return "Data id "+id.toString()+" deleted";
    }
}
