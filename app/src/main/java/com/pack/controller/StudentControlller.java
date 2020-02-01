package com.pack.controller;import com.pack.entity.*;
import com.pack.restError.StudentErrorResponse;
import com.pack.restError.StudentNotFoundException;
import com.pack.service.StudentService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class StudentControlller {
	@Autowired
	private StudentService studentService;

	@GetMapping("students")
	public List<Student> showStudents() {
		return studentService.findAll();

	}

	@GetMapping("students/byName/{studentName}")
	public List<Student> showStudentsByName(@PathVariable("studentName") String name) {
		return studentService.findAllByName(name);

	}

	@PostMapping("students")
	public Student saveStudents(@RequestBody Student theStudent) {
		theStudent.setId(0);
		studentService.saveStudent(theStudent);
		return theStudent;

	}

	@GetMapping("students/{studentId}")
	public Student findbyId(@PathVariable("studentId") int studentId) {
		Student theStudent = studentService.findAllById(studentId);
		if ( studentId < 0 ) {			
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
		return theStudent;

	}


	@DeleteMapping("students/{studentId}")
	public String deleteStudent(@PathVariable("studentId") int studentId) {
		studentService.deleteStudent(studentId);
		return "Successfully deleted ID : "+studentId;
	}
	
	//custom exception handler for particular exception
	/*@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

		StudentErrorResponse error = new StudentErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}*/

	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {

		StudentErrorResponse error = new StudentErrorResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
