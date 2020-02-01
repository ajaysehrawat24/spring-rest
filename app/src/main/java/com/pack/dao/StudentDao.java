package com.pack.dao;
import java.util.List;

import com.pack.entity.*;
public interface StudentDao {
	public List<Student> findAll();
	public Student saveStudent(Student student);
	public Student findAllById(int studentId);
	public List<Student> findAllByName(String name);
	public void deleteStudent(int studentId);

}
