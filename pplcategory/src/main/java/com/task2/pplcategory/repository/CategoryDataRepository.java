package com.task2.pplcategory.repository;

import com.task2.pplcategory.model.DataPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDataRepository extends JpaRepository<DataPO, Long> {

}
