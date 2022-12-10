package com.student.service;

import java.util.List;

import com.student.entity.Student;
import com.student.model.StudentDTO;

public interface StudentService {

	String createStudent(Student student);

	StudentDTO updateStudent(int id, Student student);

	StudentDTO getStudentById(int id);

	List<StudentDTO> getAllStudent();

	String deleteStudentById(int id);

	void deleteAllStudents();
}
