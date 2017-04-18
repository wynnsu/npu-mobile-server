package edu.npu.cs595.dao.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.cs595.dao.CourseDao;
import edu.npu.cs595.domain.Course;
import edu.npu.cs595.domain.CourseId;
import edu.npu.cs595.domain.Semester;

@Repository("CourseDaoHibernate")
@Transactional
public class CourseDaoHibernateImpl implements CourseDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger logger = Logger.getLogger(CourseDaoHibernateImpl.class);

	@Override
	public Course storeCourse(Course course) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(course);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return course;
	}

	@Override
	public Course findCourse(CourseId courseId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Course course = null;
		try {
			tx = session.beginTransaction();
			course = (Course) session.get(Course.class, courseId);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return course;
	}

	@Override
	public void removeCourse(Course course) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(course);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findAllCourses() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Course> result = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Course");
			result = query.list();
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
	public void removeAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("delete from Course");
			query.executeUpdate();
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

	@Override
	public void storeCourseList(List<Course> list) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Course c : list) {
				session.saveOrUpdate(c);
			}
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

	@Override
	public Course findSuggested(String studentId) {
		return null;
	}

	@Override
	public CourseId findCourseIdByName(String courseName, String semester) {
		logger.info("Finding course: " + courseName + ", sem: " + semester);
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		CourseId result = null;
		try {
			tx = session.beginTransaction();
			String sql = "select id from Course where course_number = (:name) and semester = (:semester)";
			Query query = session.createQuery(sql);
			query.setParameter("name", courseName);
			query.setParameter("semester", semester);
			result = (CourseId) query.list().get(0);
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

	@SuppressWarnings("unchecked")
	@Override
	public String getCurrentSemester() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		String result = "";
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Semester");
			List<Semester> semesters = query.list();
			for (Semester s : semesters) {
				Date today = Calendar.getInstance().getTime();
				if (s.getStartDate().before(today) && s.getEndDate().after(today)) {
					result = s.getName();
					break;
				}
			}
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

	@SuppressWarnings("unchecked")
	@Override
	public int getCurrentWeek() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int result = -1;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Semester");
			List<Semester> semesters = query.list();
			Semester semester = null;
			for (Semester s : semesters) {
				Date today = Calendar.getInstance().getTime();
				if (s.getStartDate().before(today) && s.getEndDate().after(today)) {
					semester = s;
					break;
				}
			}
			if (semester != null) {
				DateTime start = new DateTime(semester.getStartDate().getTime());
				DateTime current = new DateTime();
				result = current.getWeekOfWeekyear() - start.getWeekOfWeekyear();
				logger.info(result);
			}
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

}
