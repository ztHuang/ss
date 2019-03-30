package com.huang.web.dao;

import com.huang.web.domain.SSUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */

@Mapper
public interface SSUserDao {

    @Select("select * from ss_user where id = #{id}")
    public SSUser getById(@Param("id") long id);
}
