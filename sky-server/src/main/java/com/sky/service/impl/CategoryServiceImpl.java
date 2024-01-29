package com.sky.service.impl;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.Utilities;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;


    /**
     * 添加菜品
     * @param categoryDTO
     */
    public void add(CategoryDTO categoryDTO) {
       Category category = new Category();
       BeanUtils.copyProperties(categoryDTO, category);

       category.setStatus(StatusConstant.ENABLE);
       //category.setCreateTime(LocalDateTime.now());
       //category.setUpdateTime(LocalDateTime.now());
       //category.setCreateUser(BaseContext.getCurrentId());
       //category.setUpdateUser(BaseContext.getCurrentId());

       categoryMapper.insert(category);
    }

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);

        long total = page.getTotal();
        List<Category> records = page.getResult();

        return new PageResult(total, records);
    }

    public void update(CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        //category.setUpdateTime(LocalDateTime.now());
        //category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.updateByID(category);
    }


    public void startOrStop(@PathVariable Integer status, Long id){
        Category category = Category.builder()
                .status(status)
                .id(id)
                .build();
        categoryMapper.updateByID(category);
    }

    public void delete(long id){
        categoryMapper.deleteById(id);
    }

    public Category select(long id){
        Category category = categoryMapper.selectById(id);
        return category;
    }
}
