package com.task2.pplcategory.service;

import com.task2.pplcategory.exception.PplCategoryNotFoundException;
import com.task2.pplcategory.model.DataMap;
import com.task2.pplcategory.model.DataPO;
import com.task2.pplcategory.repository.CategoryDataRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataServiceTest {

    private CategoryDataRepository categoryDataRepository;
    private DataService dataService;

    @BeforeAll
    public void setup() {
        this.categoryDataRepository = Mockito.mock(CategoryDataRepository.class);
        this.dataService = new DataService(categoryDataRepository);
    }

    @AfterEach
    private void resetMock(){
        Mockito.reset(categoryDataRepository);
    }

    @Test
    void getDataAndCategoryMapTest(){

        List<DataPO> dataList = new ArrayList<>();

        DataPO dataPO = new DataPO();
        dataPO.setId((long)13);
        dataPO.setParentId(4);
        dataPO.setName("Thief");
        dataPO.setColour("Blue");

        DataPO dataPO2 = new DataPO();
        dataPO2.setId((long)15);
        dataPO2.setParentId(13);
        dataPO2.setName("Assassin");
        dataPO2.setColour("Red");

        dataList.add(dataPO);
        dataList.add(dataPO2);



        Mockito.when(categoryDataRepository.findAll()).thenReturn(dataList);

        DataMap dataMap = dataService.getDataAndCategoryMap();

        Assertions.assertNotNull(dataMap);
        Assertions.assertEquals(2, dataMap.getDataPOList().size());
        Assertions.assertEquals("Thief", dataMap.getCategoryInfoMap().get((long)13).getName());
        Assertions.assertEquals("Assassin", dataMap.getCategoryInfoMap().get((long)13).getSubClasses().get(0).getName());

    }

    @Test
    void getDataAndCategoryMapExceptionTest(){

        Mockito.when(categoryDataRepository.findAll()).thenReturn(new ArrayList<>());

        Assertions.assertThrows(PplCategoryNotFoundException.class, dataService::getDataAndCategoryMap);

    }

}
