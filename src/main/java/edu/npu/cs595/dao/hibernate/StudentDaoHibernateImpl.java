package edu.npu.cs595.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.cs595.dao.CourseDao;
import edu.npu.cs595.dao.StudentDao;
import edu.npu.cs595.domain.Activity;
import edu.npu.cs595.domain.Student;
import edu.npu.cs595.domain.StudentCourse;

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
	public List<Activity> findActivity(String studentId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Activity> result = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from StudentCourse where student_id = :studentId");
			query.setParameter("studentId", studentId);
			List<StudentCourse> courseList = query.list();
			for (StudentCourse sc : courseList) {
				logger.info("Finding course of: " + sc.getStudentId());
				query = session.createQuery("from Activity where stucourse_id = :stucourseId");
				query.setParameter("stucourseId", sc.getId());
				result.addAll(query.list());
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

	@Override
	public List<String> findAttendance(String studentId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<String> result = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from StudentCourse where student_id = :studentId");
			query.setParameter("studentId", studentId);
			@SuppressWarnings("unchecked")
			List<StudentCourse> courseList = query.list();
			for (StudentCourse sc : courseList) {
				result.add(courseDao.findCourse(sc.getCourseId()).getCourseNumber() + ": " + sc.getAttendance());
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
	public String findGradeLatest(String studentId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Activity latest = null;
		String result = "";
		DateTime currentDate = new DateTime();
		Seconds diff = Seconds.ZERO;
		DateTimeFormatter formatter = DateTimeFormat.forPattern("M/dd/YYYY hh:mm:ss aa");
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from StudentCourse where student_id = :studentId");
			query.setParameter("studentId", studentId);
			List<StudentCourse> courseList = query.list();
			for (StudentCourse sc : courseList) {
				query = session.createQuery("from Activity where stucourse_id = :courseId");
				query.setParameter("courseId", sc.getId());
				List<Activity> acts = query.list();
				for (Activity act : acts) {
					String[] dtStrings = act.getDue().split(" ");
					String dtString = dtStrings[0] + " " + dtStrings[1] + " " + dtStrings[2];
					DateTime compareDate = formatter.parseDateTime(dtString);
					if (compareDate.isBefore(currentDate)) {
						if (latest == null) {
							latest = act;
							diff = Seconds.secondsBetween(compareDate, currentDate);
						} else {
							Seconds diffAct = Seconds.secondsBetween(compareDate, currentDate);
							if (diffAct.compareTo(diff) < 0) {
								diff = diffAct;
								latest = act;
							}
						}
					}
				}
			}
			if (latest != null) {
				result = latest.getPoints();
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
	public Activity findActivityComing(String studentId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Activity coming = null;
		DateTime currentDate = new DateTime();
		Seconds diff = Seconds.ZERO;
		DateTimeFormatter formatter = DateTimeFormat.forPattern("M/dd/YYYY hh:mm:ss aa");
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from StudentCourse where student_id = :studentId");
			query.setParameter("studentId", studentId);
			@SuppressWarnings("unchecked")
			List<StudentCourse> courseList = query.list();
			for (StudentCourse sc : courseList) {
				query = session.createQuery("from Activity where stucourse_id = :courseId");
				query.setParameter("courseId", sc.getId());
				List<Activity> acts = query.list();
				for (Activity act : acts) {
					String[] dtStrings = act.getDue().split(" ");
					String dtString = dtStrings[0] + " " + dtStrings[1] + " " + dtStrings[2];
					DateTime compareDate = formatter.parseDateTime(dtString);
					if (compareDate.isAfter(currentDate)) {
						if (coming == null) {
							coming = act;
							diff = Seconds.secondsBetween(compareDate, currentDate);
						} else {
							Seconds diffAct = Seconds.secondsBetween(compareDate, currentDate);
							if (diffAct.compareTo(diff) < 0) {
								diff = diffAct;
								coming = act;
							}
						}
					}
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
		return coming;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> findStudentCourseByStudentId(String studentId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<StudentCourse> result = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from StudentCourse where student_id = :studentId");
			query.setParameter("studentId", studentId);
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
	public StudentCourse storeStudentCourse(StudentCourse studentCourse) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(studentCourse);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return studentCourse;
	}

	@Override
	public Activity storeActivity(Activity activity) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(activity);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return activity;
	}

}
