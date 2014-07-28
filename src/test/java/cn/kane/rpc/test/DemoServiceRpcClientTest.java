package cn.kane.rpc.test ;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import cn.kane.entity.Activity;
import cn.kane.entity.ActivityItem;
import cn.kane.entity.MultiTypeEntity;
import cn.kane.service.IActivityService;
import cn.kane.service.IDemoService;
import cn.kane.service.IReturnMultiService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo-client_test.xml")
public class DemoServiceRpcClientTest extends AbstractJUnit4SpringContextTests{
	@Autowired
	private IDemoService demoService ;
	@Autowired
	private IReturnMultiService returnMiltyService ;
	@Autowired
	private IActivityService activityService ;
	
	@Before
	public void testSetup() {
	}

	
	@Test
	public void testQueryById(){
		demoService.sayHi();
	}
	
	@Test
	public void testMultiResp(){
		MultiTypeEntity entity = returnMiltyService.getMultiTypeEntity() ;
		Assert.notNull(entity);
	}
	
	@Test
	public void testGetList(){
		long activityId = 1L;
		List<ActivityItem> items = activityService.findItemList(activityId ) ;
		Assert.notEmpty(items);
	}
	
	@Test
	public void testO2MDao(){
		Long gameId = 456L ;
		Activity activity = activityService.getActivityByGameId(gameId ) ;
		Assert.notNull(activity);
	}
}
