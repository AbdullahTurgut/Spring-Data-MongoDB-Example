package com.alcorcode.demo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // bu class'ın client tarafından http request yapılmasını sağlar
@RequestMapping("api/v1/students")
@AllArgsConstructor // Lombok sayesinde constructor oluşturur
public class StudentController {

    private final StudentService studentService;
// AllArgsConstructor constructor oluşturmamıza gerek bırakmıyor
    /*
    *  public StudentController(StudentService studentService){
    *       this.studentService = studentService
    * }
    * */

    // Method for return all the students
    @GetMapping
    public List<Student> fetchAllStudents(){
        return studentService.getAllStudents();
    }

}
