package ru.tpu.labs.service.impl;

import ru.tpu.labs.dto.*;
import ru.tpu.labs.model.Course;
import ru.tpu.labs.model.Student;
import ru.tpu.labs.repository.CourseRepository;
import ru.tpu.labs.repository.StudentRepository;
import ru.tpu.labs.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<CourseResDto> findAllCourse() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResDto> courseResDtoList = new ArrayList<>();
        for(Course course : courses){
            courseResDtoList.add(CourseResDto.builder().id(course.getId())
                    .title(course.getTitle())
                    .modules(course.getModules())
                    .fee(course.getFee())
                    .build());
        }
        return courseResDtoList;
    }

    @Override
    public CourseResDto insertCourse(CourseReqDto courseReqDto) {
        Course course = Course.builder()
                .title(courseReqDto.getTitle())
                .modules(courseReqDto.getModules())
                .fee(courseReqDto.getFee())
                .build();
        Course result = courseRepository.save(course);
        return CourseResDto.builder().id(result.getId()).title(result.getTitle())
                .modules(result.getModules()).fee(result.getFee()).build();
    }

    @Override
    public void registerStudentToCourse(CourseWithStudentDto courseWithStudentDto) {
        Course course = courseRepository.findById(courseWithStudentDto.getCourseId()).get();
        Student student = studentRepository.findById(courseWithStudentDto.getStudentId()).get();

//        course.getStudents().add(student);
        student.getCourses().add(course);
        studentRepository.save(student);
//        courseRepository.save(course);
    }

    @Override
    public CourseWithStudentResDto getCourseWithStudentById(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        List<Student> studentList = course.getStudents();
        List<StudentResDto> studentResDtoList = new ArrayList<>();
        for (Student student : studentList){
            studentResDtoList.add(StudentResDto.builder()
                    .age(student.getAge())
                    .name(student.getName())
                    .age(student.getAge())
                    .build());
        }
        return CourseWithStudentResDto.builder().id(courseId).title(course.getTitle())
                .modules(course.getModules()).fee(course.getFee()).studentResDtoList(studentResDtoList).build();
    }

    @Override
    public List<CourseWithStudentResDto> getAllCourseWithStudent() {
        List<Course> courses = courseRepository.findAll();
        List<CourseWithStudentResDto> courseWithStudentResDtoList = new ArrayList<>();
        for(Course course : courses){
            List<StudentResDto> studentResDtoList = new ArrayList<>();
            for(Student student : course.getStudents()){
                studentResDtoList.add(StudentResDto.builder().id(student.getId())
                        .name(student.getName()).age(student.getAge()).build());
            }
            courseWithStudentResDtoList.add(CourseWithStudentResDto.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .modules(course.getModules())
                    .fee(course.getFee())
                    .studentResDtoList(studentResDtoList)
                    .build());
        }
        return courseWithStudentResDtoList;
    }
}
