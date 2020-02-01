package com.pack.service;

import java.util.List;

import com.pack.entity.Student;

public interface StudentService {
	public List<Student> findAll();
	public Student findAllById(int studentId);
	public List<Student> findAllByName(String StudentName);
	public void saveStudent(Student student);
	public void deleteStudent(int studentId); 
}
