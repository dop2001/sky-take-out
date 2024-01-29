package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import com.sky.service.DisService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api("分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DisService disService;

    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping
    public Result add(@RequestBody CategoryDTO categoryDTO){
        categoryService.add(categoryDTO);

        return Result.success();
    }

    /**
     * 页面查询
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);

    }

    /**
     * 更新菜品信息
     * @param categoryDTO
     */
    @PutMapping
    public Result update(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 启停分类
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status, Long id){
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public Result delete(long id){
        Integer count = disService.count(id);
        if (count != 0){
            return Result.error(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        } else{
            categoryService.delete(id);
            return Result.success();
        }
    }

    /**
     * 根据类型查询分类
     * @return
     */
    @GetMapping("/list")
    public Result list(Integer type){
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }

}
