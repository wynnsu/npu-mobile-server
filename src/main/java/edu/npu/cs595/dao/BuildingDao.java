package edu.npu.cs595.dao;

import java.util.List;

import edu.npu.cs595.domain.Building;

public interface BuildingDao {
	public Building storeBuilding(Building building);

	public Building findBuilding(int buildingId);

	public void removeBuilding(Building building);

	public List<Building> findAllBuildings();

}
