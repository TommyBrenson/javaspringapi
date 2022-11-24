package ru.tpu.labs.service;

import ru.tpu.labs.dto.StudentReqDto;
import ru.tpu.labs.dto.StudentResDto;

import java.util.List;


public interface StudentService {
    List<StudentResDto> findAllStudent();
    StudentResDto findById(Long id);
    StudentResDto insertStudent(StudentReqDto studentReqDto);
    StudentResDto updateStudent(Long id, StudentReqDto studentReqDto);

    void deleteStudent(Long id);
}
