package edu.npu.cs595.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.npu.cs595.dao.BuildingDao;
import edu.npu.cs595.domain.Building;

public class BuildingDaoHibernateImpl implements BuildingDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Building storeBuilding(Building building) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(building);
		return building;
	}

	@Override
	public Building findBuilding(int buildingId) {
		Session session = sessionFactory.getCurrentSession();
		Building building = (Building) session.get(Building.class, buildingId);
		return building;
	}

	@Override
	public void removeBuilding(Building building) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(building);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Building> findAllBuildings() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Building");
		return query.list();
	}

}
