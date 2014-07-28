package cn.kane.rpc.test;

import java.net.MalformedURLException;

import junit.framework.TestCase;

import org.junit.Test;

import cn.kane.service.IDemoService;

import com.caucho.hessian.client.HessianProxyFactory;

public class HessionClientTest extends TestCase{
	
	private IDemoService demoService ;
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
}
