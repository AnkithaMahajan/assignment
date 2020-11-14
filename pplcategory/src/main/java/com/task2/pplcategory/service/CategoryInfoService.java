package com.task2.pplcategory.service;

import com.task2.pplcategory.aoplogger.LogMethodParam;
import com.task2.pplcategory.model.CategoryInfo;
import com.task2.pplcategory.model.DataMap;
import com.task2.pplcategory.model.DataPO;
import com.task2.pplcategory.repository.CategoryDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CategoryInfoService {

    private DataService dataService;

    @Autowired
    public CategoryInfoService(DataService dataService) {
        this.dataService = dataService;
    }

    @LogMethodParam
    public List<CategoryInfo> getInfoForAllCategories() {

        DataMap dataMap = dataService.getDataAndCategoryMap();

        Map<Long,CategoryInfo> categoryInfoMap = new TreeMap<>(dataMap.getCategoryInfoMap());

        dataMap.getDataPOList().forEach(dataPO -> {
            if(dataPO.getParentId() != 0){
                categoryInfoMap.remove(dataPO.getId());
            }
        });

        return new ArrayList<>(categoryInfoMap.values());

    }

    @LogMethodParam
    public CategoryInfo getInfoForPersonWithId(Long id) {

        DataMap dataMap = dataService.getDataAndCategoryMap();

        return dataMap.getCategoryInfoMap().get(id);

    }



}
