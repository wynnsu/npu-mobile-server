package edu.npu.cs595.service;

import java.util.List;

import edu.npu.cs595.domain.Building;
public interface BuildingService {
	public List<Building> getBuildings();
	public Building getBuildingById(int buildingId);
}
