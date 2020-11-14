package com.task2.pplcategory.service;

import com.task2.pplcategory.model.CategoryInfo;
import com.task2.pplcategory.model.DataMap;
import com.task2.pplcategory.model.DataPO;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryInfoServiceTest {

    private CategoryInfoService categoryInfoService;
    private DataService dataService;

    @BeforeAll
    public void setup(){
        this.dataService = Mockito.mock(DataService.class);
        this.categoryInfoService = new CategoryInfoService(dataService);
    }

    @AfterEach
    private void resetMock(){
        Mockito.reset(dataService);
    }

    @Test
    void getInfoForAllCategoriesTest(){

        Map<Long, CategoryInfo> categoryInfoMap = new TreeMap<>();
        List<DataPO> dataPOList = new ArrayList<>();

        DataPO dataPO = new DataPO();
        dataPO.setId((long)13);
        dataPO.setParentId(4);
        dataPO.setName("Thief");
        dataPO.setColour("Blue");

        DataPO dataPO3 = new DataPO();
        dataPO3.setId((long)4);
        dataPO3.setParentId(0);
        dataPO3.setName("Rogue");
        dataPO3.setColour("Yellow");

        DataPO dataPO2 = new DataPO();
        dataPO2.setId((long)15);
        dataPO2.setParentId(13);
        dataPO2.setName("Assassin");
        dataPO2.setColour("Red");

        dataPOList.add(dataPO);
        dataPOList.add(dataPO2);
        dataPOList.add(dataPO3);

        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setName(dataPO3.getName());
        categoryInfo.setColour(dataPO3.getColour());

        List<CategoryInfo> subClasses = new ArrayList<>();
        CategoryInfo child = new CategoryInfo();
        child.setName(dataPO.getName());
        child.setColour(dataPO.getColour());

        List<CategoryInfo> subClasses1 = new ArrayList<>();
        CategoryInfo grandChild = new CategoryInfo();
        grandChild.setName(dataPO2.getName());
        grandChild.setColour(dataPO2.getColour());
        subClasses1.add(grandChild);
        child.setSubClasses(subClasses1);

        subClasses.add(child);
        categoryInfo.setSubClasses(subClasses);

        categoryInfoMap.put(dataPO3.getId(), categoryInfo);

        DataMap dataMap = new DataMap(dataPOList, categoryInfoMap);

        Mockito.when(dataService.getDataAndCategoryMap()).thenReturn(dataMap);

        List<CategoryInfo> categoryInfoList = categoryInfoService.getInfoForAllCategories();

        Assertions.assertNotNull(categoryInfoList);
        Assertions.assertEquals(1, categoryInfoList.size());
        Assertions.assertEquals("Rogue", categoryInfoList.get(0).getName());
        Assertions.assertEquals("Thief", categoryInfoList.get(0).getSubClasses().get(0).getName());
        Assertions.assertEquals("Assassin", categoryInfoList.get(0).getSubClasses().get(0).getSubClasses().get(0).getName());

    }

    @Test
    void getInfoForPersonWithIdTest(){

        Map<Long, CategoryInfo> categoryInfoMap = new TreeMap<>();
        List<DataPO> dataPOList = new ArrayList<>();

        DataPO dataPO = new DataPO();
        dataPO.setId((long)13);
        dataPO.setParentId(4);
        dataPO.setName("Thief");
        dataPO.setColour("Blue");

        DataPO dataPO3 = new DataPO();
        dataPO3.setId((long)4);
        dataPO3.setParentId(0);
        dataPO3.setName("Rogue");
        dataPO3.setColour("Yellow");

        DataPO dataPO2 = new DataPO();
        dataPO2.setId((long)15);
        dataPO2.setParentId(13);
        dataPO2.setName("Assassin");
        dataPO2.setColour("Red");

        dataPOList.add(dataPO);
        dataPOList.add(dataPO2);
        dataPOList.add(dataPO3);

        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setName(dataPO3.getName());
        categoryInfo.setColour(dataPO3.getColour());

        List<CategoryInfo> subClasses = new ArrayList<>();
        CategoryInfo child = new CategoryInfo();
        child.setName(dataPO.getName());
        child.setColour(dataPO.getColour());

        List<CategoryInfo> subClasses1 = new ArrayList<>();
        CategoryInfo grandChild = new CategoryInfo();
        grandChild.setName(dataPO2.getName());
        grandChild.setColour(dataPO2.getColour());
        subClasses1.add(grandChild);
        child.setSubClasses(subClasses1);

        subClasses.add(child);
        categoryInfo.setSubClasses(subClasses);

        categoryInfoMap.put(dataPO3.getId(), categoryInfo);
        categoryInfoMap.put(dataPO.getId(), child);
        categoryInfoMap.put(dataPO2.getId(), grandChild);

        DataMap dataMap = new DataMap(dataPOList, categoryInfoMap);

        Mockito.when(dataService.getDataAndCategoryMap()).thenReturn(dataMap);

        CategoryInfo categoryInfoResp = categoryInfoService.getInfoForPersonWithId((long) 13);

        Assertions.assertNotNull(categoryInfoResp);
        Assertions.assertEquals(3, dataMap.getCategoryInfoMap().size());
        Assertions.assertEquals("Thief", categoryInfoResp.getName());
        Assertions.assertEquals("Assassin", categoryInfoResp.getSubClasses().get(0).getName());

    }

}
