package cn.kane.rpc.test;

import java.net.MalformedURLException;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.util.Assert;

import cn.kane.entity.Activity;
import cn.kane.service.IActivityService;
import cn.kane.service.IDemoService;
import cn.kane.utils.serialize.hessian.HibernateSerializerFactory;

import com.caucho.hessian.client.HessianProxyFactory;

public class HessionClientTest extends TestCase{
	
	private IDemoService demoService ;
	private IActivityService activityService ;
	private HessianProxyFactory factory = new HessianProxyFactory();
	
	@Test
	public void singHessionTest() throws MalformedURLException, ClassNotFoundException{
		String url = "http://localhost:8080/dubbo-demo/singhession";
		demoService = (IDemoService) this.getService(url,null);
		demoService.sayHi();
	}
	
	@Test
	public void testHessionWithSpring() throws MalformedURLException, ClassNotFoundException{
		String url = "http://localhost:8080/dubbo-demo/springhession/demoService";
		demoService = (IDemoService) this.getService(url,IDemoService.class);
		demoService.sayHi();
	}
	
	private Object getService(String serviceProviderUrl,Class<?> clazz) throws MalformedURLException, ClassNotFoundException{
		if(null == clazz){
			return factory.create(serviceProviderUrl) ;
		}else{
			return factory.create(clazz,serviceProviderUrl) ;
		}
	}
	
	@Test
	public void testClientDeserialize() throws MalformedURLException{
		String url = "http://127.0.0.1:8085/cn.kane.service.IActivityService" ;
		factory.setSerializerFactory(new HibernateSerializerFactory());
		activityService = (IActivityService)factory.create(IActivityService.class,url);
		Long gameId = 456L ;
		Activity activity = activityService.getActivityByGameId(gameId ) ;
		Assert.notNull(activity);
	}
}
