package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/dish")
@Slf4j
@Api("菜品相关接口")
public class DishController {

    @Autowired
    private DisService disService;

    @PostMapping
    @ApiOperation("新增菜品")
    @ResponseBody
    public Result save(@RequestBody DishDTO dishDTO){
        disService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 菜品分页
     * @return
     */
    @GetMapping("/page")
    @ResponseBody
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult = disService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 菜品批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("菜品批量删除")
    @ResponseBody
    public Result delete(@RequestParam List<Long> ids){
        disService.deleteBatch(ids);
        return Result.success();
    }
}
