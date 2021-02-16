package com.fwtai.controller;

import com.fwtai.api.DeptService;
import com.fwtai.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * get 或 list才符合 restFul 的风格
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-01 22:51
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@RestController //资源 + rest的操作符
@RequestMapping("/dept")
public class DeptController{

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient discoveryClient;

    // http://127.0.0.1:8001/dept/add
    @PostMapping("/add")
    public Integer add(@RequestBody Dept dept){
        return deptService.add(dept);
    }

    // http://127.0.0.1:8001/dept/get/1
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Dept get(@PathVariable("id") final Integer id){
        return deptService.get(id);
    }

    // http://127.0.0.1:8001/dept/list
    @RequestMapping("/list")
    public List<Dept> list(){
        return deptService.list();
    }

    // 对外提供服务发现的url http://127.0.0.1:8001/dept/discovery
    @RequestMapping(value = "/discovery",method = RequestMethod.GET)
    public Object discovery(){
        final List<String> list = discoveryClient.getServices();
        System.out.println("******************"+list);
        final List<ServiceInstance> instances = discoveryClient.getInstances("deptprovider");
        for(final ServiceInstance instance : instances){
            System.out.println(instance.getServiceId()+","+instance.getHost()+","+instance.getPort()+","+instance.getUri());
        }
        return this.discoveryClient;
    }
}