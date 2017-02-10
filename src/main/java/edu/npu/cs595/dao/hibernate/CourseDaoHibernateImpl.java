package edu.npu.cs595.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.cs595.dao.CourseDao;
import edu.npu.cs595.domain.Course;

@Repository("CourseDaoHibernate")
@Transactional
public class CourseDaoHibernateImpl implements CourseDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Course storeCourse(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(course);
		return course;
	}

	@Override
	public Course findCourse(int courseId) {
		Session session = sessionFactory.getCurrentSession();
		Course course = (Course) session.get(Course.class, courseId);
		return course;
	}

	@Override
	public void removeCourse(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(course);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findAllCourses() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Course");
		return query.list();
	}

}
