package edu.npu.cs595.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "credential")
public class Credential {
	@Id
	private String id;
	@Column(name = "password")
	private String base64Password;

	public Credential() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBase64Password() {
		return base64Password;
	}

	public void setBase64Password(String base64Password) {
		this.base64Password = base64Password;
	}

}
