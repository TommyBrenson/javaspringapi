package ru.tpu.labs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResDto {
    private Long id;
    private String title;
    private String writter;
    private String isbn;
//    private Long categoryId;
//    private String categoryName;
}
