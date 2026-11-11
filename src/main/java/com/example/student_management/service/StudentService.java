
package com.example.student_management.service;

import com.example.student_management.Student;
import com.example.student_management.exception.StudentNotFoundException;
import com.example.student_management.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Create Student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get All Students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get Student By ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));
    }

    // Update Student
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = getStudentById(id);

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAge(student.getAge());

        return studentRepository.save(existingStudent);
    }

    // Delete Student
    public void deleteStudent(Long id) {
        Student existingStudent = getStudentById(id);
        studentRepository.delete(existingStudent);
    }
}
