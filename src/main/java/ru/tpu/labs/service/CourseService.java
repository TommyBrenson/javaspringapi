package ru.tpu.labs.service;

import ru.tpu.labs.dto.CourseReqDto;
import ru.tpu.labs.dto.CourseResDto;
import ru.tpu.labs.dto.CourseWithStudentDto;
import ru.tpu.labs.dto.CourseWithStudentResDto;

import java.util.List;

public interface CourseService {
    List<CourseResDto> findAllCourse();
    CourseResDto insertCourse(CourseReqDto courseReqDto);
    void registerStudentToCourse(CourseWithStudentDto courseWithStudentDto);
    CourseWithStudentResDto getCourseWithStudentById(Long courseId);
    List<CourseWithStudentResDto> getAllCourseWithStudent();
}
