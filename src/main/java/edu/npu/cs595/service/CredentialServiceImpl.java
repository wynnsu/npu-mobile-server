package edu.npu.cs595.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.npu.cs595.crawler.Crawler;
import edu.npu.cs595.dao.CredentialDao;
import edu.npu.cs595.domain.Credential;

@Service
public class CredentialServiceImpl implements CredentialService {

	@Autowired
	@Qualifier("CredentialDaoHibernate")
	private CredentialDao credentialDao;

	@Override
	public Credential getCredentialById(String id) {
		return credentialDao.findCredential(id);
	}

	@Override
	public void addCredential(Credential credential) throws IOException {
		int result = Crawler.validate(credential.getId(), credential.getBase64Password());
		if (result == Crawler.SUCCESS) {
			credentialDao.storeCredential(credential);
		}
	}

	@Override
	public void removeCredential(String id) {
		credentialDao.removeCredential(credentialDao.findCredential(id));
	}

	@Override
	public String validateCredentialById(String id) throws IOException {
		Credential credential = credentialDao.findCredential(id);
		if (credential == null) {
			return "NOT FOUND";
		}
		int result = Crawler.validate(id, credential.getBase64Password());

		if (result == Crawler.SUCCESS) {
			return "Success";
		} else if (result == Crawler.FAILED) {
			return "Failed";
		} else {
			return "Error";
		}
	}

}
