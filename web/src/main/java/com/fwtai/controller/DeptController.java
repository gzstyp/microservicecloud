package com.fwtai.controller;

import com.fwtai.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

/**
 * [Controller + RestTemplate实现] 在服务消费方中的controller层和service层没有什么关系了,controller只管调用啦!!!
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-02 0:18
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@RestController
@RequestMapping("/consumer/dept")
public class DeptController{

    private final static String URL_PREFIX = "http://deptProvider";// deptProvider 是服务提供方的实例名,无端口号是因为添加了 Ribbon 这个值是在哪里找到的呢???是在服务提供方的 spring.application.name处找到,但是需要给 RestTemplate 添加注释 @Bean和@LoadBalanced

    //在服务消费方中,我只管吃蛋糕,我更不用管关心做蛋糕,所以在消费方没有service层,仅仅调用:
    @Autowired
    private RestTemplate restTemplate;

    // http://127.0.0.1/consumer/dept/insert/BigData
    @RequestMapping(value = "/insert/{dname}")
    public Integer insert(@PathVariable("dname") final String dname){
        final HashMap<String,String> map = new HashMap<String,String>();
        map.put("dname",dname);
        map.put("db_source","microcloud01");
        return restTemplate.postForObject(URL_PREFIX + "/dept/add",map,Integer.class);
    }

    @RequestMapping(value = "/add")
    public Integer add(final Dept dept){
        return restTemplate.postForObject(URL_PREFIX + "/dept/add",dept,Integer.class);
    }

    @RequestMapping(value = "/get/{id}")
    public Dept get(@PathVariable("id") final Integer id){
        return restTemplate.getForObject(URL_PREFIX + "/dept/get/"+id,Dept.class);
    }

    @RequestMapping(value = "/list")
    public List list(){
        return restTemplate.getForObject(URL_PREFIX + "/dept/list",List.class);
    }

    // 对外提供服务发现的接口 http://127.0.0.1/consumer/dept/discovery 刷新可出现不同的端口号
    @RequestMapping(value = "/discovery")
    public Object discovery(){
        return restTemplate.getForObject(URL_PREFIX + "/dept/discovery",Object.class);
    }
}