package com.rofine.gp.application;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rofine.gp.platform.exception.GpException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class AppTest {

	@Autowired
	private AppTestService appTestService;

	@BeforeClass
	public static void testInit() {
		AppTestService.testInit();
	}

	@Test
	public void testDomain() throws GpException {
		appTestService.testDomain();
	}

}
