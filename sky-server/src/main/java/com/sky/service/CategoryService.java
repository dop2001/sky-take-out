package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryService {

    /**
     * 新增分类
     */
    void add(CategoryDTO categoryDTO);

    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void update(CategoryDTO categoryDTO);

    void startOrStop(Integer status, Long id);

    void delete(long id);

    List<Category> list(Integer type);
}
