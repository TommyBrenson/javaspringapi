package ru.tpu.labs.controller;

import ru.tpu.labs.dto.StudentReqDto;
import ru.tpu.labs.dto.StudentResDto;
import ru.tpu.labs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/student")
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentResDto> findAllStudents(){

        return studentService.findAllStudent();
    }

    @GetMapping("/{id}")
    public StudentResDto findStudentById(@PathVariable("id") Long id){
        return studentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResDto insert(@RequestBody StudentReqDto studentReqDto){
        return studentService.insertStudent(studentReqDto);
    }

    @PutMapping("/{id}")
    public StudentResDto update(@PathVariable("id") Long id ,@RequestBody StudentReqDto studentReqDto){
        return studentService.updateStudent(id, studentReqDto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return "Student id " + id.toString() + "deleted";
    }

}
