package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.model.StudentDTO;
import com.student.service.StudentService;
import com.student.util.Converter;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private Converter converter;

	@PostMapping("/createStudent")
	public String createStudent(@RequestBody StudentDTO studentDTO) {

		final Student student = converter.convertToEntity(studentDTO);
		return studentService.createStudent(student);
	}

	@PutMapping("/updateStudent/{id}")
	public StudentDTO updateStudent(@PathVariable("id") int id, @RequestBody StudentDTO studentDTO) {

		Student student = converter.convertToEntity(studentDTO);
		return studentService.updateStudent(id, student);

	}

	@GetMapping("/getStudentById/{id}")
	public StudentDTO getStudentById(@PathVariable("id") int id) {

		return studentService.getStudentById(id);
	}

	@GetMapping("/getAllStudent")
	public List<StudentDTO> getAllStudent() {
		return studentService.getAllStudent();
	}

	@DeleteMapping("/deleteStudentById/{id}")
	public String deleteStudentById(@PathVariable("id") int id) {
		return studentService.deleteStudentById(id);
	}

	@DeleteMapping("/deleteAllStudents")
	public ResponseEntity<String> deleteAllStudents() {

		studentService.deleteAllStudents();

		return new ResponseEntity<>("All Students have been deleted", HttpStatus.OK);
	}
}
