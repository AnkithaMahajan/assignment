package com.task2.pplcategory.service;

import com.task2.pplcategory.exception.PplCategoryNotFoundException;
import com.task2.pplcategory.model.CategoryInfo;
import com.task2.pplcategory.model.DataMap;
import com.task2.pplcategory.model.DataPO;
import com.task2.pplcategory.repository.CategoryDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class DataService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryInfoService.class);

    private CategoryDataRepository categoryDataRepository;

    @Autowired
    public DataService(CategoryDataRepository categoryDataRepository) {
        this.categoryDataRepository = categoryDataRepository;
    }

    @Cacheable("dataMap")
    public DataMap getDataAndCategoryMap(){
        List<DataPO> dataPOList= getAllFromDb();
        Map<Long, CategoryInfo> categoryInfoMap = mapDataToCategoryInfoWithRel(dataPOList);

        return new DataMap(dataPOList, categoryInfoMap);

    }

    private Map<Long, CategoryInfo> mapDataToCategoryInfoWithRel(List<DataPO> data) {

        Map<Long, CategoryInfo> categoryInfoMap= new TreeMap<>();

        data.forEach(dataPO -> {
            CategoryInfo categoryInfo = new CategoryInfo();
            categoryInfo.setName(dataPO.getName());
            categoryInfo.setColour(dataPO.getColour());
            categoryInfoMap.put(dataPO.getId(),categoryInfo);
        });

        data.forEach(dataPO -> {
            if(dataPO.getParentId()!=0){
                CategoryInfo parent = categoryInfoMap.get(dataPO.getParentId());
                if(parent!=null) {
                    if (parent.getSubClasses() == null) {
                        parent.setSubClasses(new ArrayList<>());
                    }
                    parent.getSubClasses().add(categoryInfoMap.get(dataPO.getId()));
                }
            }
        });

        return categoryInfoMap;
    }

    private List<DataPO> getAllFromDb() {
        List<DataPO> data = categoryDataRepository.findAll();

        if(data == null || data.isEmpty())  throw new PplCategoryNotFoundException();

        return data;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @CacheEvict(value = "dataMap",allEntries=true)
    public String flushCache(){
        LOG.info("Cache Cleared");
        return "Cache Cleared";
    }

}
