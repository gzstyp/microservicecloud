package com.fwtai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-01 23:04
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
public class LaunchProvider8003{

    public static void main(String[] args){
        SpringApplication.run(LaunchProvider8003.class,args);
        System.out.println("--应用启动成功--");
    }
}