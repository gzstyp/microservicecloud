package com.fwtai.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-02 3:17
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@SpringBootApplication
@EnableEurekaServer // EurekaServer服务器端启动类,接受其它微服务注册进来
public class LaunchEureka7001{

    public static void main(final String[] args){
        SpringApplication.run(LaunchEureka7001.class,args);
        System.out.println("--应用启动成功--");
    }
}