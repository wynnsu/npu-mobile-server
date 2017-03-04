package edu.npu.cs595.dao.hibernate;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.npu.cs595.dao.StudentDao;
import edu.npu.cs595.domain.Student;

@Repository("StudentDaoHibernate")
@Transactional
public class StudentDaoHibernateImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Student storeStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.save(student);
		return student;
	}

	@Override
	public Student findStudent(int studentID) {
		Session session = sessionFactory.getCurrentSession();
		Student student = (Student) session.get(Student.class, studentID);
		return student;
	}

	@Override
	public void removeStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(student);
	}

}
