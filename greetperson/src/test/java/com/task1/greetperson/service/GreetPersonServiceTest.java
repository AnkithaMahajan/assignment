package com.task1.greetperson.service;

import com.task1.greetperson.exception.EmptyRequestException;
import com.task1.greetperson.exception.GreetWordUnavailableException;
import com.task1.greetperson.model.PersonInfo;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GreetPersonServiceTest {

    private RestTemplate restTemplate;
    private Environment env;
    private GreetPersonService greetPersonService;

    @BeforeAll
    public void Setup() {
        this.restTemplate = Mockito.mock(RestTemplate.class);
        this.env = Mockito.mock(Environment.class);
        this.greetPersonService = new GreetPersonService(restTemplate,env);
    }

    @AfterEach
    private void resetMock(){
        Mockito.reset(restTemplate);
        Mockito.reset(env);
    }

    @Test
    void generateGreetPersonMessageGreetWordExceptionTest(){
        String url = "url";
        Mockito.when(env.getProperty(Mockito.anyString())).thenReturn(url);
        Mockito.when(restTemplate.getForObject(url, String.class)).thenThrow(new RuntimeException());

        Assertions.assertThrows(GreetWordUnavailableException.class, () -> greetPersonService.generateGreetPersonMessage(Mockito.any()));

    }

    @Test
    void generateGreetPersonMessageEmptyReqExceptionTest(){

        Assertions.assertThrows(EmptyRequestException.class, () ->greetPersonService.generateGreetPersonMessage(null));

    }

    @Test
    void generateGreetPersonMessageEmptyReqExceptionJsonTest(){

        PersonInfo personInfo = new PersonInfo();
        Assertions.assertThrows(EmptyRequestException.class, () ->greetPersonService.generateGreetPersonMessage(personInfo));

    }

    @Test
    void generateGreetPersonMessageTest(){
        PersonInfo personInfo = new PersonInfo();
        personInfo.setFirstName("Sidney");
        personInfo.setLastName("Sheldon");

        String url = "url";
        Mockito.when(env.getProperty(Mockito.anyString())).thenReturn(url);
        Mockito.when(restTemplate.getForObject(url, String.class)).thenReturn("Hello");
        Mockito.when(restTemplate.postForObject(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn("Sidney Sheldon");

        String greetMessage = greetPersonService.generateGreetPersonMessage(personInfo);

        Assertions.assertNotNull(greetMessage);
        Assertions.assertEquals("Hello Sidney Sheldon", greetMessage);

    }

}
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
//        Mockito.when(categoryDataRepository.getPersonInfoAlongWithChild(Mockito.anyLong())).thenReturn(dataList);
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
//        Mockito.when(categoryDataRepository.getPersonInfoAlongWithChild(Mockito.anyLong())).thenReturn(dataList);
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
//        Mockito.when(categoryDataRepository.getPersonInfoAlongWithChild(Mockito.anyLong())).thenReturn(dataList);
//        CategoryInfo categoryInfo = categoryInfoService.getInfoForPersonWithId((long)13);
//
//        Assertions.assertNotNull(categoryInfo);
//        Assertions.assertEquals("Thief", categoryInfo.getName());
//        Assertions.assertEquals("Blue", categoryInfo.getColour());
//        Assertions.assertNotNull(categoryInfo.getSubClasses());
//    }
//
//}
