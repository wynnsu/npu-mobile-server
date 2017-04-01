package edu.npu.cs595.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activity")
public class Activity {
	@Id
	@GeneratedValue
	private int id;
	private int week;
	private Date due;
	private int pastdue;
	@Column(name="allowlate")
	private int allowLateSubmission;
	private String title;
	private double points;
	private double total;
	private int submitted;
	@Column(name="submit_time")
	private Date submitTime;
	@Column(name="stucourse_id")
	private int studentCourseId;

	/**
	 * @return the due
	 */
	public Date getDue() {
		return due;
	}

	/**
	 * @param due the due to set
	 */
	public void setDue(Date due) {
		this.due = due;
	}

	/**
	 * @return the pastdue
	 */
	public int getPastdue() {
		return pastdue;
	}

	/**
	 * @param pastdue the pastdue to set
	 */
	public void setPastdue(int pastdue) {
		this.pastdue = pastdue;
	}

	/**
	 * @return the allowLateSubmission
	 */
	public int getAllowLateSubmission() {
		return allowLateSubmission;
	}

	/**
	 * @param allowLateSubmission the allowLateSubmission to set
	 */
	public void setAllowLateSubmission(int allowLateSubmission) {
		this.allowLateSubmission = allowLateSubmission;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the submitted
	 */
	public int getSubmitted() {
		return submitted;
	}

	/**
	 * @param submitted the submitted to set
	 */
	public void setSubmitted(int submitted) {
		this.submitted = submitted;
	}

	/**
	 * @return the submitTime
	 */
	public Date getSubmitTime() {
		return submitTime;
	}

	/**
	 * @param submitTime the submitTime to set
	 */
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * @return the studentCourseId
	 */
	public int getStudentCourseId() {
		return studentCourseId;
	}

	/**
	 * @param studentCourseId the studentCourseId to set
	 */
	public void setStudentCourseId(int studentCourseId) {
		this.studentCourseId = studentCourseId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the week
	 */
	public int getWeek() {
		return week;
	}

	/**
	 * @param week the week to set
	 */
	public void setWeek(int week) {
		this.week = week;
	}

	/**
	 * @return the points
	 */
	public double getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(double points) {
		this.points = points;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	
}
