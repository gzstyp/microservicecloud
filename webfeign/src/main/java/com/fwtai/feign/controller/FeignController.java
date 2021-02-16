package com.fwtai.feign.controller;

import com.fwtai.entities.Dept;
import com.fwtai.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * feign组件面向接口编程,即接口+注解来调用服务,在服务消费方中的controller层和service层没有什么关系了,controller只管调用啦!!!
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-02 0:18
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@RestController
@RequestMapping("/feign/dept")
public class FeignController{

    //feign 组件是接口 + 注解 [本示例仅供feign负载均衡的算法调用],针对调用哪一个微服务!!!,即服务提供方[接口实现方]的 spring.application.name=deptProvider，[deptProvider不区分大小写],在该接口的功能在子模块euraka700x下已实现
    @Autowired
    private FeignService service; // 本接口已实现服务降级的功能,详情在 com.fwtai.hystrix.DeptHystrixFallbackFactory

    // http://127.0.0.1:81/feign/dept/get/1
    @RequestMapping(value = "/get/{id}")
    public Dept get(@PathVariable("id") final Integer id){
        return this.service.get(id);
    }

    // http://127.0.0.1:81/feign/dept/list
    @RequestMapping(value = "/list")
    public List<Dept> list(){
        return this.service.list();
    }

    @RequestMapping(value = "/add")
    public Object add(Dept dept){
        return this.service.add(dept);
    }
}