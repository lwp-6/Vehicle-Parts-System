package com.example.springboottest1.mapper;

import com.example.springboottest1.entity.Parts;
import com.example.springboottest1.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface partsMapper {
    //用户登录
    List<Parts> partsSearchbyname(@Param("partsname") String partsname);
    List<Parts> partsSearchbyusername(@Param("username") String username);
    List<Parts> partsSearchall();
    Parts partsSearchbyid(@Param("id")int id);
    int addparts(@Param("partsname") String partsname, @Param("quantity") int quantity, @Param("price") double price, @Param("username")String username, @Param("information")String information);
    int deleteparts(@Param("id")int id);
    int updateparts(@Param("id")int id, @Param("partsname")String partsname, @Param("quantity") int quantity, @Param("price") double price, @Param("information")String information);
}
