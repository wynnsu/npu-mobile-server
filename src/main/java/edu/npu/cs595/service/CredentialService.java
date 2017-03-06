package edu.npu.cs595.service;

import java.io.IOException;

import edu.npu.cs595.domain.Credential;

public interface CredentialService {
	public Credential getCredentialById(String id);

	public void addCredential(Credential credential) throws IOException;

	public void removeCredential(String id);

	public String validateCredentialById(String id) throws IOException;
}
