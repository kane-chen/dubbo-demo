package cn.kane.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.kane.dao.ActivityDAO;
import cn.kane.dao.ActivityItemDAO;
import cn.kane.entity.Activity;
import cn.kane.entity.ActivityItem;
import cn.kane.service.IActivityService;

public class ActivityServiceImpl implements IActivityService {

	@Autowired
	private ActivityDAO activityDAO;
	@Autowired
	private ActivityItemDAO activityItemDAO;

	@Override
	public void saveActivityItem(ActivityItem item) {
		this.activityItemDAO.save(item);
	}
	
	@Override
	public void updateActivityItem(ActivityItem item) {
		this.activityItemDAO.update(item);
	}
	
	@Override
	public List<ActivityItem> findItemList(long activityId){
		String hql = "from ActivityItem where activityId = ? ";
		List<ActivityItem>  list = activityItemDAO.doQueryList(hql, activityId);

		return list;
	}
	
	@Override
	public Activity findById(Long id) {
		String hql = "from Activity where id=?";
		return this.activityDAO.doQueryFirst(hql, id);
	}

	@Override
	public Activity findByIntroAndGame(String intro, Long gameId){
		String hql="from Activity where gameId=? and intro like ? order by startTime desc";
		return this.activityDAO.doQueryFirst(hql, gameId, intro);
	}
	
	@Override
	public Activity findLastByIntroAndGame(String intro, Long gameId){
		String hql="from Activity where gameId=? and intro like ? order by id desc";
		return this.activityDAO.doQueryFirst(hql, gameId, intro);
	}
	
	@Override
	public Activity getActivityByGameId(Long gameId) {
			String hql = "from Activity where gameId=?";
			return this.activityDAO.doQueryFirst(hql, gameId);
	}

}
