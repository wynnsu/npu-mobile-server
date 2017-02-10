package edu.npu.cs595.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.npu.cs595.dao.BuildingDao;
import edu.npu.cs595.domain.Building;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	@Qualifier("BuildingDaoHibernate")
	private BuildingDao buildingDao;

	@Override
	public List<Building> getBuildings() {
		return buildingDao.findAllBuildings();
	}

	@Override
	public Building getBuildingById(int buildingId) {
		return buildingDao.findBuilding(buildingId);
	}

}
