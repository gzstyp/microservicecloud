package com.fwtai.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * feign组件面向接口编程,即接口+注解来调用服务
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-02 22:29
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@SpringBootApplication
@EnableEurekaClient
// 在启动类添加注解 @EnableFeignClients(basePackages = {"com.fwtai"}) 和  @ComponentScan("com.fwtai"),
// 在配置文件application.yml开启 feign.hystrix.enabled=true hystrix组件的服务降级服务,
// 另外在实现接口 FallbackFactory 的服务降级类添加注解 @Component
@EnableFeignClients(basePackages = {"com.fwtai"})
@ComponentScan("com.fwtai")
public class LaunchFeign{

    public static void main(final String[] args){
        SpringApplication.run(LaunchFeign.class,args);
        System.out.println("--应用启动成功--");
    }
}