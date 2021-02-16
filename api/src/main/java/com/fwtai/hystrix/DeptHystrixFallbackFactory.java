package com.fwtai.hystrix;

import com.fwtai.entities.Dept;
import com.fwtai.feign.FeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 给接口 FeignApi 提供降级服务,组件hystrix的降级服务的功能演示,解决了每个方法都添加的注解 @HystrixCommand,相当于给服务提供方的实现方法的controller层的每个方法都添加@HystrixCommand做统一处理,达到子模块webfeign给的controller层达解耦合!本接口是给子模块 webfeign 调用的!!!
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-03 20:07
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@Component // 在实现接口 FallbackFactory 的服务降级类添加注解 @Component,在配置文件application.yml开启 feign.hystrix.enabled=true开启hystrix组件的服务降级服务,还需在启动类添加注解 @EnableFeignClients(basePackages = {"com.fwtai"}) 和  @ComponentScan("com.fwtai")
public class DeptHystrixFallbackFactory implements FallbackFactory<FeignService>{

    // 本类DeptHystrixFallbackFactory针对接口 com.fwtai.feign.FeignService 里的各个方法进行服务降级,也就是对FeignService的方法每一个熔断机制统一在本类统一处理解决,
    // 以达到在服务提供方的子模块 provider_hystrix 里的controller层的每个方法的注解@HystrixCommand进行解耦了.还需在com.fwtai.feign.FeignService的注解@FeignClient添加属性fallbackFactory=本类.class
    // 最终是在服务消费方的子模块 webfeign 里的controller层的实例.方法来调用

    //备选响应,针对接口 FeignApi 实现备选响应,服务降级能实现解耦合分离
    @Override
    public FeignService create(final Throwable cause){
        return new FeignService(){

            @Override
            public Dept get(Integer id){
                final Dept dept = new Dept();
                dept.setDeptno(id);
                dept.setDname("该id:"+id+"没有找到对应的数据,服务提供方使用了Hystrix组件服务降级功能,服务降级成功,此刻已经关闭!");
                dept.setDb_source("no this database in MySQL");
                return dept;
            }

            @Override
            public List<Dept> list(){
                return null;
            }

            @Override
            public boolean add(final Dept dept){
                return false;
            }
        };
    }
}