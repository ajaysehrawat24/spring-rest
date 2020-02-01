package com.pack.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.pack.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
	
	//session factory is injected from the AppConfig extraction from persistent-mysql.properties
	@Autowired
	private SessionFactory sessionFactory;


	/*SessionFactory sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Student.class)
			.buildSessionFactory();*/


	public List<Student> findAll() {


		Session session =sessionFactory.getCurrentSession();
		//session.beginTransaction();
		Query<Student> theQuery = session.createQuery("from Student",Student.class);
		List<Student> listOfStudents = theQuery.getResultList();
		//session.getTransaction().commit();
		return listOfStudents;
	}

	public Student saveStudent(Student theStudent) {
		Session session =sessionFactory.getCurrentSession();
		session.saveOrUpdate(theStudent);
		return theStudent;

	}

	public Student findAllById(int studentId) {
		Session session = sessionFactory.getCurrentSession();
		Student theStudent = session.get(Student.class,studentId);
		return theStudent;
	}

	public void deleteStudent(int studentId) {
		Session session =sessionFactory.getCurrentSession();
		Student student = session.get(Student.class,studentId);
		session.delete(student);
		/*Query<Student> theQuery = 
				session.createQuery("delete from Student stud where stud.id=:sid");
		theQuery.setParameter("sid", studentId);*/
	}

	@Override
	public List<Student> findAllByName(String name) {
		Session session =sessionFactory.getCurrentSession();
		Query<Student> theQuery = session.createQuery("from Student where name=:studentName",Student.class);
		theQuery.setParameter("studentName", name);
		List<Student> listOfStudents = theQuery.getResultList();
		return listOfStudents;
	}


}
