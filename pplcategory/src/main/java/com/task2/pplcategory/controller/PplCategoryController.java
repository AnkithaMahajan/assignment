package com.task2.pplcategory.controller;

import com.task2.pplcategory.model.CategoryInfo;
import com.task2.pplcategory.service.CategoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PplCategoryController {

    private CategoryInfoService categoryInfoService;

    @Autowired
    public PplCategoryController(CategoryInfoService categoryInfoService) {
        this.categoryInfoService = categoryInfoService;
    }

    @GetMapping(value = "/people-categories")
    public List<CategoryInfo> getInfoForAllPplCategories(){
        return categoryInfoService.getInfoForAllCategories();
    }

    @GetMapping(value = "/person-info")
    public CategoryInfo getInfoForId(@RequestParam Long id){
        return categoryInfoService.getInfoForPersonWithId(id);
    }

}
