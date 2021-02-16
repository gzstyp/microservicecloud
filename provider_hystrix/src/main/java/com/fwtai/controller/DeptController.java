package com.fwtai.controller;

import com.fwtai.api.DeptService;
import com.fwtai.entities.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本子模块是Hystrix服务熔断示例,即报异常后如何处理,类似于异常通知
 * get 或 list才符合 restFul 的风格
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-01 22:51
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@RestController //rest风格,资源 + rest的操作符
@RequestMapping("/dept")
public class DeptController{

    //一旦调用服务出错出异常后并抛出错误信息,会自动调用注解@HystrixCommand标注好的failbackMethon调用类中的指定的方法

    @Autowired
    private DeptService deptService;// 可以使用 Hystrix 组件的服务降级功能来减少@HystrixCommand的代码量,服务降级详情 com.fwtai.hystrix.DeptHystrixFallbackFactory

    /**
     * 服务熔断功能{缺点方法膨胀,高耦合!!!也就是每个方法都要配置@HystrixCommand,可以使用 ‘服务降级’ 代替,服务降级能实现解耦合分离} http://127.0.0.1:9001/dept/get/2 [服务降级将要替换服务熔断的]
    */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrixGet")// 一旦调用服务出错出异常后并抛出错误信息,会自动调用注解@HystrixCommand标注好的failbackMethon调用类中的指定的方法返回给调用方
    public Dept get(@PathVariable("id") Integer id){
        final Dept dept = this.deptService.get(id);
        if(dept == null){
            throw new RuntimeException("该id:"+id+"没有找到对应的数据");
        }
        return dept;
    }

    // 出错后被调用的方法,返回备选、可处理的预留信息
    protected Dept processHystrixGet(@PathVariable("id") Integer id){
        final Dept dept = new Dept();
        dept.setDeptno(id);
        dept.setDname("该id:"+id+"没有找到对应的数据,null--@HystrixCommand,熔断调用成功!");
        dept.setDb_source("no this database in MySQL");
        return dept;
    }
}