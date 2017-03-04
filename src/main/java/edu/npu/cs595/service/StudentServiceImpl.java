package edu.npu.cs595.service;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.npu.cs595.crawler.Crawler;
import edu.npu.cs595.dao.StudentDao;
import edu.npu.cs595.domain.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	@Qualifier("StudentDaoHibernate")
	private StudentDao studentDao;

	@Autowired
	@Qualifier("StudentCrawler")
	private Crawler<Student> studentCrawler;

	@Override
	public Student getStudentById(int studentId) {
		return studentDao.findStudent(studentId);
	}

	@Override
	public int addNewStudent(Student student) throws IOException {
		System.out.println(student.getBase64Credential());
		int status_code = Crawler.validate(student.getBase64Credential());
		System.out.println(status_code);
		int res = -1;
		if (status_code == HttpStatus.SC_MOVED_TEMPORARILY) {
			// redirected, means found
			Student result = studentDao.storeStudent(student);
			res = result.getId();
		}
		return res;
	}

	@Override
	public void removeStudent(Student student) {
		studentDao.removeStudent(student);
	}

}
