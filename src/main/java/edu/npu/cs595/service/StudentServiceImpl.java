package edu.npu.cs595.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.npu.cs595.crawler.Crawler;
import edu.npu.cs595.dao.StudentDao;
import edu.npu.cs595.domain.Activity;
import edu.npu.cs595.domain.Student;
import edu.npu.cs595.domain.Enroll;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	@Qualifier("StudentDaoHibernate")
	private StudentDao studentDao;

	@Autowired
	@Qualifier("StudentCrawler")
	private Crawler<Student> studentCrawler;

	protected static Logger logger = Logger.getLogger(StudentService.class);

	@Override
	public Student getStudentById(String studentId) {
		return studentDao.findStudent(studentId);
	}

	@Override
	public Student registerStudent(String studentId, String base64Password) {
		Student student = null;
		try {
			List<Student> list = studentCrawler.crawl(studentId, base64Password);
			student = list.get(0);
		} catch (Exception e) {
			logger.info("Exception occured for crawler");
			e.printStackTrace();
		}
		if (student == null) {
			return null;
		} else {
			return studentDao.storeStudent(student);
		}
	}

	@Override
	public List<Activity> getActivityById(String studentId) {
		logger.info("Finding Activity");
		return studentDao.findActivity(studentId, 0);
	}

	@Override
	public List<Enroll> getAttendance(String studentId) {
		logger.info("Finding Attendance");
		return studentDao.findAttendance(studentId);
	}

	@Override
	public List<Activity> getActivityComing(String studentId) {
		logger.info("Finding coming activity");
		return studentDao.findActivity(studentId, 1);
	}

	@Override
	public void updateStudent(String studentId) throws Exception {
		logger.info("Retrieving data");
		Student student = studentDao.findStudent(studentId);
		if (student != null) {
			studentCrawler.crawl(student.getId(), student.getBase64Password());
		}
	}

	@Override
	public List<Activity> getActivityLatest(String studentId) {
		logger.info("Finding Latest");
		return studentDao.findActivity(studentId, -1);
	}

	@Override
	public void enrollCourse(Enroll enroll) {
		studentDao.enrollCourse(enroll);
	}
}
