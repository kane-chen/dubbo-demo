package cn.kane.rpc.test ;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.kane.service.IDemoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo-client_test.xml")
public class DemoServiceRpcClientTest extends AbstractJUnit4SpringContextTests{
	@Autowired
	private IDemoService demoService ;

	@Before
	public void testSetup() {
	}

	
	@Test
	public void testQueryById(){
		demoService.sayHi();
	}
	
}
