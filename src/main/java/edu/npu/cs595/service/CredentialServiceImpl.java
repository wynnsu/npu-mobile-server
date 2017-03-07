package edu.npu.cs595.service;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
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
	private Logger logger = Logger.getLogger(CredentialServiceImpl.class);

	@Override
	public Credential getCredentialById(String id) {
		return credentialDao.findCredential(id);
	}

	@Override
	public Response addCredential(Credential credential) throws IOException {
////		int result = Crawler.validate(credential.getId(), credential.getBase64Password());
//		switch (result) {
//		case Crawler.SUCCESS:
//			logger.info("success");
//			credentialDao.storeCredential(credential);
//			return Response.status(Status.OK).build();
//		case Crawler.FAILED:
//			logger.info("failed");
//			return Response.status(Status.NOT_FOUND).build();
//		case Crawler.DUPLICATE:
//			logger.info("duplicate");
//			return Response.status(Status.CONFLICT).build();
//		case Crawler.ERROR:
//			logger.info("error");
//			return Response.status(Status.BAD_REQUEST).build();
//		default:
//			logger.info("null");
//			return null;
//		}
//		if (result == Crawler.SUCCESS) {
//			credentialDao.storeCredential(credential);
//			logger.info("success");
//			return Response.status(Status.OK).build();
//		} else{
//			logger.info("not found");
//			return Response.status(Status.NOT_FOUND).build();
//		}
		return null;
	}

	@Override
	public void removeCredential(String id) {
		credentialDao.removeCredential(credentialDao.findCredential(id));
	}

	@Override
	public Response validateCredentialById(String id) throws IOException {
//		Credential credential = credentialDao.findCredential(id);
//		if (credential == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		int result = Crawler.validate(id, credential.getBase64Password());
//		logger.info(result);
//		switch (result) {
//		case Crawler.SUCCESS:
//			logger.info("success");
//			return Response.status(Status.OK).build();
//		case Crawler.FAILED:
//			logger.info("failed");
//			return Response.status(Status.NOT_FOUND).build();
//		case Crawler.DUPLICATE:
//			logger.info("duplicate");
//			return Response.status(Status.CONFLICT).build();
//		case Crawler.ERROR:
//			logger.info("error");
//			return Response.status(Status.BAD_REQUEST).build();
//		default:
//			logger.info("null");
//			return null;
//		}
		return null;
	}
}
