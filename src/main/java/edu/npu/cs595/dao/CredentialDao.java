package edu.npu.cs595.dao;

import edu.npu.cs595.domain.Credential;

public interface CredentialDao {

	public Credential storeCredential(Credential credential);

	public Credential findCredential(String id);

	public void removeCredential(Credential credential);
}
