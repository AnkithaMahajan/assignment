//package com.task2.pplcategory.service;
//
//import com.task2.pplcategory.exception.PplCategoryNotFoundException;
//import com.task2.pplcategory.model.CategoryInfo;
//import com.task2.pplcategory.model.DataPO;
//import com.task2.pplcategory.repository.CategoryDataRepository;
//import org.junit.jupiter.api.*;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class CategoryInfoServiceTest {
//
//    private CategoryInfoService categoryInfoService;
//    private CategoryDataRepository categoryDataRepository;
//
//    @BeforeAll
//    public void setup(){
//        this.categoryDataRepository = Mockito.mock(CategoryDataRepository.class);
//        this.categoryInfoService = new CategoryInfoService(categoryDataRepository);
//    }
//
//    @AfterEach
//    private void resetMock(){
//        Mockito.reset(categoryDataRepository);
//    }
//
//    @Test
//    void getInfoForAllCategoriesExceptionTest(){
//        List<DataPO> dataList = null;
//        Mockito.when(categoryDataRepository.findAll()).thenReturn(dataList);
//        Assertions.assertThrows(PplCategoryNotFoundException.class ,categoryInfoService::getInfoForAllCategories);
//
//    }
//
//    @Test
//    void getInfoForAllCategoriesTest(){
//        List<DataPO> dataList = new ArrayList<>();
//
//        DataPO dataPO1 = new DataPO();
//        dataPO1.setId((long)1);
//        dataPO1.setParentId(0);
//        dataPO1.setName("Warrior");
//        dataPO1.setColour("Red");
//
//        DataPO dataPO2 = new DataPO();
//        dataPO2.setId((long)5);
//        dataPO2.setParentId(1);
//        dataPO2.setName("Fighter");
//        dataPO2.setColour("Blue");
//
//        dataList.add(dataPO1);
//        dataList.add(dataPO2);
//
//        Mockito.when(categoryDataRepository.findAll()).thenReturn(dataList);
//        List<CategoryInfo> categoryInfos = categoryInfoService.getInfoForAllCategories();
//
//        Assertions.assertNotNull(categoryInfos);
//        Assertions.assertEquals(1, categoryInfos.size());
//        Assertions.assertEquals("Warrior", categoryInfos.get(0).getName());
//        Assertions.assertNotNull(categoryInfos.get(0).getSubClasses());
//        Assertions.assertEquals("Fighter", categoryInfos.get(0).getSubClasses().get(0).getName());
//
//    }
//
//    @Test
//    void getInfoForPersonWithIdExceptionTest(){
//        List<DataPO> dataList = null;
//        Mockito.when(categoryDataRepository.findAll()).thenReturn(dataList);
//        Assertions.assertThrows(PplCategoryNotFoundException.class ,categoryInfoService::getInfoForAllCategories);
//
//    }
//
//    @Test
//    void getInfoForPersonWithIdNoChildTest(){
//        List<DataPO> dataList = new ArrayList<>();
//
//        DataPO dataPO = new DataPO();
//        dataPO.setId((long)5);
//        dataPO.setParentId(1);
//        dataPO.setName("Fighter");
//        dataPO.setColour("Blue");
//
//        dataList.add(dataPO);
//
//        Mockito.when(categoryDataRepository.findAll()).thenReturn(dataList);
//        CategoryInfo categoryInfo = categoryInfoService.getInfoForPersonWithId((long)5);
//
//        Assertions.assertNotNull(categoryInfo);
//        Assertions.assertEquals("Fighter", categoryInfo.getName());
//        Assertions.assertEquals("Blue", categoryInfo.getColour());
//        Assertions.assertNull(categoryInfo.getSubClasses());
//
//    }
//
//    @Test
//    void getInfoForPersonWithIdTest(){
//        List<DataPO> dataList = new ArrayList<>();
//
//        DataPO dataPO = new DataPO();
//        dataPO.setId((long)13);
//        dataPO.setParentId(4);
//        dataPO.setName("Thief");
//        dataPO.setColour("Blue");
//
//        DataPO dataPO2 = new DataPO();
//        dataPO2.setId((long)15);
//        dataPO2.setParentId(13);
//        dataPO2.setName("Assassin");
//        dataPO2.setColour("Red");
//
//        dataList.add(dataPO);
//        dataList.add(dataPO2);
//
//        Mockito.when(categoryDataRepository.findAll()).thenReturn(dataList);
//        CategoryInfo categoryInfo = categoryInfoService.getInfoForPersonWithId((long)13);
//
//        Assertions.assertNotNull(categoryInfo);
//        Assertions.assertEquals("Thief", categoryInfo.getName());
//        Assertions.assertEquals("Blue", categoryInfo.getColour());
//        Assertions.assertNotNull(categoryInfo.getSubClasses());
//    }
//
//}
