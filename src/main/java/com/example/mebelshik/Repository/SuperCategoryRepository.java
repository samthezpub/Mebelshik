package com.example.mebelshik.Repository;

import com.example.mebelshik.Entitiy.Category;
import com.example.mebelshik.Entitiy.SuperCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperCategoryRepository extends JpaRepository<SuperCategory, Long> {
    @Query("SELECT s.categories FROM SuperCategory s WHERE s.id =:supercategoryId")
    List<Category> findAllCategoriesBySuperCategoryId(@Param("supercategoryId") Long supercategoryId);
}
