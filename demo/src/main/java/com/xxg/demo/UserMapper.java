package com.xxg.demo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * User映射类 Created by Administrator on 2017/11/24.
 */
@Mapper
public interface UserMapper {

	@Select("SELECT * FROM T_USER WHERE PHONE = #{phone}")
	User findUserByPhone(@Param("phone") String phone);

	@Insert("INSERT INTO T_USER(NAME, PASSWORD, PHONE) VALUES(#{name}, #{password}, #{phone})")
	int insert(@Param("name") String name, @Param("password") String password, @Param("phone") String phone);

}
