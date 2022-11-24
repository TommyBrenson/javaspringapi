package ru.tpu.labs.service.impl;

import ru.tpu.labs.dto.StudentReqDto;
import ru.tpu.labs.dto.StudentResDto;
import ru.tpu.labs.model.Student;
import ru.tpu.labs.repository.StudentRepository;
import ru.tpu.labs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<StudentResDto> findAllStudent() {
        List<Student> students = studentRepository.findAll();
        List<StudentResDto> studentResDtoList = new ArrayList<>();
        for(Student student : students){
            studentResDtoList.add(StudentResDto.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .age(student.getAge()).build());
        }
        return studentResDtoList;
    }

    @Override
    public StudentResDto findById(Long id) {
        Student student = studentRepository.findById(id).get();
        StudentResDto result = StudentResDto.builder().id(id).name(student.getName()).age(student.getAge()).build();
        return result;
    }

    @Override
    public StudentResDto insertStudent(StudentReqDto studentReqDto) {
        Student newStudent = Student.builder().name(studentReqDto.getName()).age(studentReqDto.getAge()).build();
        Student result = studentRepository.save(newStudent);
        return StudentResDto.builder().id(result.getId()).name(result.getName()).age(result.getAge()).build();
    }

    @Override
    public StudentResDto updateStudent(Long id, StudentReqDto studentReqDto) {
        Optional<Student> findStudent = studentRepository.findById(id);
        Student updateStudent = new Student();
        if(findStudent.isPresent()){
            Student student = findStudent.get();
            student.setId(id);
            student.setName(studentReqDto.getName());
            student.setAge(studentReqDto.getAge());
            updateStudent = studentRepository.save(student);
        }
        return StudentResDto.builder().id(updateStudent.getId()).name(updateStudent.getName())
                .age(updateStudent.getAge()).build();
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
