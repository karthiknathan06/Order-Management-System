package com.company.retailprocessor;

import com.company.retailprocessor.controller.OrderControllerTest;
import com.company.retailprocessor.controller.UserControllerTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class RetailprocessorApplicationTests {

	@Test
	public void contextLoads()
	{
		JUnitCore.runClasses(OrderControllerTest.class, UserControllerTest.class);
	}

}
