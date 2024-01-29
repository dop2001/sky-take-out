package com.sky.service;

import com.sky.dto.DishDTO;

public interface DisService {

    /**
     * 新增菜品和对应的口味数据
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    Integer count(long id);
}
