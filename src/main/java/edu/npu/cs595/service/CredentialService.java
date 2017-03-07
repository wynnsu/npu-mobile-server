package edu.npu.cs595.service;

import java.io.IOException;

import javax.ws.rs.core.Response;

import edu.npu.cs595.domain.Credential;

public interface CredentialService {
	public Credential getCredentialById(String id);

	public Response addCredential(Credential credential) throws IOException;

	public void removeCredential(String id);

	public Response validateCredentialById(String id) throws IOException;
}
