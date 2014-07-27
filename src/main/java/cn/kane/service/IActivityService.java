package cn.kane.service;

import java.util.List;

import cn.kane.entity.Activity;
import cn.kane.entity.ActivityItem;


public interface IActivityService {

	void saveActivityItem(ActivityItem item);
	
	void updateActivityItem(ActivityItem item) ;
	
	List<ActivityItem> findItemList(long activityId);
	
	Activity findById(Long id);
	
	Activity findByIntroAndGame(String intro, Long gameId);
	
	Activity findLastByIntroAndGame(String intro, Long gameId);
	
	Activity getActivityByGameId(Long gameId);
	
}
