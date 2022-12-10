package com.student.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Student;
import com.student.model.StudentDTO;
import com.student.repository.StudentRepository;
import com.student.service.StudentService;
import com.student.util.Converter;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private Converter converter;

	@Override
	public String createStudent(Student student) {

		String message = null;
		studentRepository.save(student);
		if (student != null) {
			message = "Student Saved Successfully";
		}
		return message;
	}

	@Override
	public StudentDTO updateStudent(int id, Student student) {

		Student foundStudent = studentRepository.findById(id).get();

		foundStudent.setStudentName(student.getStudentName());
		foundStudent.setPhone(student.getPhone());
		foundStudent.setEmail(student.getEmail());

		studentRepository.save(foundStudent);
		return converter.convertToStudentDTO(foundStudent);

	}

	@Override
	public StudentDTO getStudentById(int id) {

		Student foundStudent = studentRepository.findById(id).get();
		return converter.convertToStudentDTO(foundStudent);
	}

	@Override
	public List<StudentDTO> getAllStudent() {

		List<Student> allStudentList = studentRepository.findAll();

		List<StudentDTO> allStudentDTOs = new ArrayList<>();

		for (Student student : allStudentList) {
			allStudentDTOs.add(converter.convertToStudentDTO(student));
		}

		return allStudentDTOs;
	}

	@Override
	public String deleteStudentById(int id) {

		Optional<Student> student = studentRepository.findById(id);

		if (student.isPresent()) {
			studentRepository.deleteById(id);
			return "Student Deleted Successfully :)";
		}

		return "Student Not Found :(";
	}

	@Override
	public void deleteAllStudents() {
		studentRepository.deleteAll();
	}

}
