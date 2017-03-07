package edu.npu.cs595.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		Student existStudent = (Student) session.get(Student.class, student.getId());
		if (existStudent != null) {
			existStudent.setName(student.getName());
			session.update(existStudent);
			return existStudent;
		} else {
			session.save(student);
			return student;
		}
	}

	@Override
	public Student findStudent(String studentId) {
		Session session = sessionFactory.getCurrentSession();
		Student student = (Student) session.get(Student.class, studentId);
		return student;
	}

	@Override
	public void removeStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(student);
	}

}
