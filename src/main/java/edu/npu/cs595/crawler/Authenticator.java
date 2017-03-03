package edu.npu.cs595.crawler;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class Authenticator implements Crawler{

	public boolean isReady = false;

	private String base64Credential = null;

	public String getCredential() {
		return base64Credential;
	}

	public void setCredential(String credential) {
		this.base64Credential = credential;
		
		isReady=true;
	}

	protected Authenticator() {
	}

	public String getPlainUsername() {
		String credential = new String(Base64.decodeBase64(base64Credential));
		return credential.split(":")[0];

	}

	public String getPlainPassword() {
		String credential = new String(Base64.decodeBase64(base64Credential));
		return credential.split(":")[1];
	}

	@Override
	public List crawl() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
