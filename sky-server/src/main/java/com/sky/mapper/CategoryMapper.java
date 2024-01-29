package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category (id, type, name, sort, status, create_time, update_time, create_user, update_user)" +
            "values (#{id}, #{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Category category);

    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    @AutoFill(value = OperationType.UPDATE)
    void updateByID(Category category);

    @Delete("delete from category where id = #{id}")
    void deleteById(long id);

    @Select("select * from category where id = #{id}")
    Category selectById(long id);
}
