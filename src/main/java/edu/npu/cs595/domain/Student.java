package edu.npu.cs595.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@XmlRootElement
@Entity
@Table(name = "student")
public class Student {
	@Id
	private String id;
	@Column(name = "password")
	private String base64Password;
	public String getBase64Password() {
		return base64Password;
	}

	public void setBase64Password(String base64Password) {
		this.base64Password = base64Password;
	}

	private String name;
	private String email;
	private String address;
	private String program;
	private double cgpa;
	@Column(name="unit_progress")
	private double unitProgress;
	@Column(name="unit_total")
	private double unitTotal;

	@ManyToMany
	@JoinTable(name = "enroll", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
	@JsonManagedReference
	private List<Course> courses = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public double getUnitProgress() {
		return unitProgress;
	}

	public void setUnitProgress(double unitProgress) {
		this.unitProgress = unitProgress;
	}

	public double getUnitTotal() {
		return unitTotal;
	}

	public void setUnitTotal(double unitTotal) {
		this.unitTotal = unitTotal;
	}

}
