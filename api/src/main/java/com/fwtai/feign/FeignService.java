package com.fwtai.feign;

import com.fwtai.entities.Dept;
import com.fwtai.hystrix.DeptHystrixFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

/**
 * 它给子模块webfeign服务消费方的controller层来调用
 * (本接口与DeptService没有任何关系,本接口是结合基于子模块provider800x服务提供者和子模块服务消费者 webfeign的controller层的对象.方法实现调用使用[针对注解@FeignClient(value = "deptProvider")不含属性fallbackFactory])
 * feign 面向接口编辑方式调用,feign组件是接口 + 注解 [本示例仅供feign负载均衡的算法调用]需要添加依赖 spring-cloud-starter-feign 版本号 1.4.6.RELEASE,feign默认的负载均衡是轮询的方式,feign包含Ribbon???,本接口是结合子模块 webfeign 直接在服务的客户端的controller层通过实例.方法调用的!!!
*/
//未提供Hystrix组件的服务降级时,也就是没有属性fallbackFactory则是给服务提供者的子模块 provider_hystrix 的controller层调用 @FeignClient(value = "deptProvider") //deptProvider 是针对调用哪一个微服务名!!!,即服务提供方[接口实现方]的 spring.application.name=deptProvider，[deptProvider不区分大小写],在该接口的实现功能在子模块euraka700x下均已实现
@FeignClient(value = "deptProvider",fallbackFactory = DeptHystrixFallbackFactory.class) //在添加了属性 fallbackFactory 后已是提供了服务降级的功能,且是给服务消费方的子模块 webfeign 调用!!!
public interface FeignService extends Serializable{

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    Dept get(@PathVariable("id") Integer id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    List<Dept> list();

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    boolean add(final Dept dept);
}
// 本注解启用了降级功能,其意义是给子模块 webfeign 的controller层提供了一锅端处理,deptProvider是针对调用哪一个微服务名!!!,
// 即服务提供方[接口实现方]的 spring.application.name=deptProvider，[deptProvider不区分大小写],在该接口的功能在子模块euraka700x下已实现