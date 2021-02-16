package com.fwtai.api;

import com.fwtai.entities.Dept;

import java.util.List;

/**
 * 定义接口,get 或 list才符合restFul的风格,(本接口与FeignService没有任何关系,本接口是结合基于子模块provider800x服务提供者和子模块服务消费者web的controller层的RestTemplate.方法实现调用使用)
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-01 22:39
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
public interface DeptService{

    Integer add(Dept dept);

    //get 或 list才符合restFul的风格
    Dept get(Integer id);

    //get 或 list才符合restFul的风格
    List<Dept> list();
}