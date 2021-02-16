package com.fwtai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 熔断机制的提供者,仿照子模块 provider8001 的功能
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-01 23:04
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@SpringBootApplication
@EnableEurekaClient //程序正常启动后会自动注册eureka服务中
@EnableDiscoveryClient //服务发现,可有可无???
@EnableCircuitBreaker // 熔断器,开启对 hystrix 组件的熔断机制的支持
public class LaunchProviderHystrix{

    public static void main(final String[] args){
        SpringApplication.run(LaunchProviderHystrix.class,args);
        System.out.println("--应用启动成功--");
    }
}