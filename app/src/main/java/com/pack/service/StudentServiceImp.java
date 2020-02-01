package com.pack.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pack.dao.StudentDao;
import com.pack.entity.Student;

@Service
public class StudentServiceImp implements StudentService {
	@Autowired
	private StudentDao studentDao;

	@Override
	@Transactional
	public List<Student> findAll() {
		List<Student> listOfStudents = studentDao.findAll();
		return listOfStudents;
	}

	@Override
	@Transactional
	public void saveStudent(Student student) {
		studentDao.saveStudent(student);
	}

	@Override
	@Transactional
	public Student findAllById(int studentId) {
		Student theStudent = studentDao.findAllById(studentId);
		return theStudent;
	}
	@Override
	@Transactional
	public void deleteStudent(int studentId) {
		studentDao.deleteStudent(studentId);
	}
	@Transactional
	@Override
	public List<Student> findAllByName(String studentName) {
		List<Student> studenList = studentDao.findAllByName(studentName);
		return studenList;
	}

}
