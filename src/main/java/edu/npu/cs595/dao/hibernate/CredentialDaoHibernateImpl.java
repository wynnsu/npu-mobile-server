package edu.npu.cs595.dao.hibernate;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.npu.cs595.dao.CredentialDao;
import edu.npu.cs595.domain.Credential;

@Repository("CredentialDaoHibernate")
@Transactional
public class CredentialDaoHibernateImpl implements CredentialDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Credential storeCredential(Credential credential) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(credential);
		return credential;
	}

	@Override
	public Credential findCredential(String id) {
		Session session = sessionFactory.getCurrentSession();
		Credential student = (Credential) session.get(Credential.class, id);
		return student;
	}

	@Override
	public void removeCredential(Credential credential) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(credential);
	}

}
