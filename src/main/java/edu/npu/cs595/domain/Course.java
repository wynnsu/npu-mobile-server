package edu.npu.cs595.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;
	@Column(name = "course_number")
	private String courseNumber;
	@Column(name = "is_online")
	private String isOnline;
	private String title;
	private double credits;
	@ManyToMany
	@JoinTable(name = "prerequisite", joinColumns = @JoinColumn(name = "prerequisite_course_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "advanced_course_id", referencedColumnName = "id"))
	private List<Course> prerequisite = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "instructor_id", referencedColumnName = "id")
	private Faculty instructor;
	private String time;
	@ManyToOne
	@JoinColumn(name = "classroom_id", referencedColumnName = "id")
	private Classroom classroom;
	private int week = 0;
	@ManyToMany(mappedBy = "courses")
	@JsonBackReference
	private List<Student> students = new ArrayList<>();

	public Department getDepartment() {
		return department;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	@JsonIgnore
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

	@JsonIgnore
	public List<Course> getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(List<Course> prerequisite) {
		this.prerequisite = prerequisite;
	}

	public Faculty getInstructor() {
		return instructor;
	}

	public void setInstructor(Faculty instructor) {
		this.instructor = instructor;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
}
