package com.task2.pplcategory;

import com.task2.pplcategory.controller.PplCategoryController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PplcategoryApplicationTests {

	@Autowired
	private PplCategoryController pplCategoryController;

	@Test
	public void contextLoads() throws Exception {
		Assertions.assertNotNull(pplCategoryController);
	}

}
