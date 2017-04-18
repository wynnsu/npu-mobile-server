package edu.npu.cs595.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "enroll")
public class Enroll {
	@EmbeddedId
	private EnrollId id;
	private String attendance;

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	/**
	 * @return the id
	 */
	public EnrollId getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(EnrollId id) {
		this.id = id;
	}

}
