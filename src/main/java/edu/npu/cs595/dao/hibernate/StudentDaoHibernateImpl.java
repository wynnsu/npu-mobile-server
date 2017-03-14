package edu.npu.cs595.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		Session session = sessionFactory.openSession();
		Transaction tx = null;
//		Student result = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(student);
//			result = (Student) session.merge(student);
			// Student existStudent = (Student) session.get(Student.class,
			// student.getId());
			// if (existStudent != null) {
			// existStudent.setName(student.getName());
			// session.update(existStudent);
			// result = existStudent;
			// } else {
			// session.save(student);
			// result = student;
			// }
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return student;
	}

	@Override
	public Student findStudent(String studentId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Student result = null;
		try {
			tx = session.beginTransaction();
			result = (Student) session.get(Student.class, studentId);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public void removeStudent(Student student) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(student);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

}
