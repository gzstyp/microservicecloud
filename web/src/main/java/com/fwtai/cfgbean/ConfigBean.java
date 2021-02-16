package com.fwtai.cfgbean;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 采用注解!!! 注释 Configuration 的意思是告诉 spring boot @Configuration配置   ConfigBean = applicationContext.xml
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-02 0:06
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@Configuration
public class ConfigBean{

    //如果不指定,那默认使用的是轮询的负载均衡方式,即 new RoundRobinRule();
    @Bean// bean的注解的作用是 <bean id="userServcie" class="com.fwtai.UserServiceImpl">
    @LoadBalanced // Ribbon 拥有客户端操作功能及附带负载均衡的功能,且在Ribbon之后调用服务提供方时无需添加端口号,即 "http://deptProvider";
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    //显式指定使用 '随机'负载均衡方式,一般用于抢红包,换个负载均衡的方式需要重新启动 eureka 服务,RetryRule 如果某次失败后再重新换个服务调用
    @Bean
    public IRule myIRule(){
        return new RandomRule();
    }
}