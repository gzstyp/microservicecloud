package com.fwtai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-01 23:04
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@SpringBootApplication
@EnableZuulProxy //代理,代理人,中介
public class LaunchZuul{

    public static void main(String[] args){
        SpringApplication.run(LaunchZuul.class,args);
        System.out.println("--应用启动成功--");
    }
}