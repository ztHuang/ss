package com.huang.web.dao;

import com.huang.web.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.28
 * @Version 1.0
 */

@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User getUserById(@Param("id") int id);

    @Insert("insert into user(id,name)values (#{id},#{name})")
    public int insert(User user);
}
