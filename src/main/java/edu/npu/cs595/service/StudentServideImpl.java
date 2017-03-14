package edu.npu.cs595.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.npu.cs595.crawler.Crawler;
import edu.npu.cs595.dao.StudentDao;
import edu.npu.cs595.domain.Student;

@Service
public class StudentServideImpl implements StudentService {

	@Autowired
	@Qualifier("StudentDaoHibernate")
	private StudentDao studentDao;

	@Autowired
	@Qualifier("StudentCrawler")
	private Crawler<Student> studentCrawler;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (student == null) {
			return null;
		} else {
			return studentDao.storeStudent(student);
		}
	}
}
