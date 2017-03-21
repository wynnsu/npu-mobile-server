package edu.npu.cs595.dao;

import java.util.List;

import edu.npu.cs595.domain.Activity;

public interface ActivityDao {
	public Activity storeActivity(Activity activity);

	public Activity findActivity(int activityId);

	public void removeActivity(Activity activity);

	public List<Activity> findAllActivities();

	public void removeAll();

	public void storeActivityList(List<Activity> list);
}
