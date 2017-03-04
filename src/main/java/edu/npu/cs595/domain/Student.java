package edu.npu.cs595.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "credential")
	private String base64Credential;
	

	public Student() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBase64Credential() {
		return base64Credential;
	}

	public void setBase64Credential(String base64Credential) {
		this.base64Credential = base64Credential;
	}

}
