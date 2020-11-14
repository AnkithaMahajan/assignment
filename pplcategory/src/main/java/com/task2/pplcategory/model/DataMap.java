package com.task2.pplcategory.model;

import java.util.List;
import java.util.Map;

public class DataMap {

    private List<DataPO> dataPOList;
    private Map<Long, CategoryInfo> categoryInfoMap;

    public DataMap(List<DataPO> dataPOList, Map<Long, CategoryInfo> categoryInfoMap) {
        this.dataPOList = dataPOList;
        this.categoryInfoMap = categoryInfoMap;
    }

    public List<DataPO> getDataPOList() {
        return dataPOList;
    }

    public void setDataPOList(List<DataPO> dataPOList) {
        this.dataPOList = dataPOList;
    }

    public Map<Long, CategoryInfo> getCategoryInfoMap() {
        return categoryInfoMap;
    }

    public void setCategoryInfoMap(Map<Long, CategoryInfo> categoryInfoMap) {
        this.categoryInfoMap = categoryInfoMap;
    }
}
