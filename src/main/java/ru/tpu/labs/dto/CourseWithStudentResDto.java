package ru.tpu.labs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseWithStudentResDto {
    public Long id;
    public String title;
    private int modules;
    private double fee;
    List<StudentResDto> studentResDtoList;
}
