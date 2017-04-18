package edu.npu.cs595.dao.hibernate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.cs595.dao.CourseDao;
import edu.npu.cs595.dao.StudentDao;
import edu.npu.cs595.domain.Activity;
import edu.npu.cs595.domain.Student;
import edu.npu.cs595.domain.Enroll;

@Repository("StudentDaoHibernate")
@Transactional
public class StudentDaoHibernateImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger logger = Logger.getLogger(StudentDaoHibernateImpl.class);

	@Autowired
	CourseDao courseDao;

	@Override
	public Student storeStudent(Student student) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(student);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Enroll> findAttendance(String studentId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		logger.info(studentId);
		List<Enroll> result = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Enroll where student_id = :studentId");
			query.setParameter("studentId", studentId);
			logger.info(query.getQueryString());
			result.addAll(query.list());
			logger.info(query.list().toString());
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
	public List<Activity> findActivity(String studentId, int code) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Activity> result = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Enroll where student_id = :studentId");
			query.setParameter("studentId", studentId);
			List<Enroll> courseList = query.list();

			for (Enroll sc : courseList) {
				switch (code) {
				case 0:
					query = session.createQuery(
							"from Activity where student_id = :studentId and course_number = :courseNumber and semester = :semester");
					query.setParameter("studentId", sc.getId().getStudentId());
					query.setParameter("courseNumber", sc.getId().getCourseId().getCourseNumber());
					query.setParameter("semester", sc.getId().getCourseId().getSemester());
					result.addAll(query.list());
					break;
				case 1:
					query = session.createQuery(
							"from Activity where student_id = :studentId and course_number = :courseNumber and semester = :semester and due > :today");
					query.setParameter("studentId", sc.getId().getStudentId());
					query.setParameter("courseNumber", sc.getId().getCourseId().getCourseNumber());
					query.setParameter("semester", sc.getId().getCourseId().getSemester());
					query.setParameter("today", new Date(Calendar.getInstance().getTimeInMillis()));
					result.addAll(query.list());
					break;
				case -1:
					query = session.createQuery(
							"from Activity where student_id = :studentId and course_number = :courseNumber and semester = :semester and submit_time < :today");
					query.setParameter("studentId", sc.getId().getStudentId());
					query.setParameter("courseNumber", sc.getId().getCourseId().getCourseNumber());
					query.setParameter("semester", sc.getId().getCourseId().getSemester());
					query.setParameter("today", new Date(Calendar.getInstance().getTimeInMillis()));
					result.addAll(query.list());
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
		switch (code) {
		case 1:
			Collections.sort(result, new Comparator<Activity>() {

				@Override
				public int compare(Activity arg0, Activity arg1) {
					return arg0.getDue().compareTo(arg1.getDue());
				}

			});
			break;
		case -1:
			Collections.sort(result, new Comparator<Activity>() {
				@Override
				public int compare(Activity arg0, Activity arg1) {
					return arg0.getSubmitTime().compareTo(arg1.getSubmitTime());
				}
			});
			Collections.reverse(result);
			break;
		}
		logger.info("Date format: " + result.get(0).getDue());
		org.json.JSONObject json = new JSONObject();
		json.put("date", result.get(0).getDue());
		logger.info("Date json: " + json.get("date"));
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(Long.parseLong("1491805800000"));
		cal.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = cal.getTime();
		logger.info(date);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enroll> findStudentCourseByStudentId(String studentId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Enroll> result = null;
		try {
			tx = session.beginTransaction();
			logger.info(studentId);
			Query query = session.createQuery("from Enroll where student_id = :studentId");
			query.setParameter("studentId", studentId);
			result = query.list();
			if (result == null)
				logger.info("null result");
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
	public void enrollCourse(Enroll enroll) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(enroll);
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
