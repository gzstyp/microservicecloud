package com.fwtai.dao;

import com.fwtai.entities.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-01 19:13
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@Mapper
public interface DeptDao{

    Integer addDept(final Dept dept);

    Dept findById(final Integer id);

    List<Dept> findAll();

}