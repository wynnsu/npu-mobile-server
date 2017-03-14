package edu.npu.cs595.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "studentcourse")
public class StudentCourse {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(optional = false)
	@JoinColumn(name = "student_id", nullable = false, updatable = false)
	private Student student;
	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", nullable = false, updatable = false)
	private Course course;
}
