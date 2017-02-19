package edu.npu.cs595.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;

@XmlRootElement
@Entity
@Table(name = "academic_event")
public class AcademicEvent {
	@Id
	@GeneratedValue
	private int id;
	@Column(name="event_date")
	@Type(type="date")
	private Date date;
	private String content;

	public int getId() {
		return id;
	}

	public AcademicEvent(Date date, String content) {
		super();
		this.date = date;
		this.content = content;
	}

	public AcademicEvent() {
		// TODO Auto-generated constructor stub
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
