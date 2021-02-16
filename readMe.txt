
在微服务架构中,分3个角色: 服务注册中心 | 服务端[服务提供方] | 客户端[服务消费方]

feign组件 负载均衡算法,是通过接口 + 注释来实现的服务的调用
feign组件和Ribbon组件都是服务消费方的功能
Hystrix组件是在服务提供方的组件,类似于异常通知,原理:一旦调用服务出错出异常后并抛出错误信息,会自动调用注解@HystrixCommand标注好的fallbackMethod调用类中的指定的方法
Hystrix的降级服务可以解决资源抢占和资源分配,Hystrix组件可以对故障和延时进行隔离和管理
在Ribbon集群模式之后,所有节点的spring.application.name的实例名必须是一致!!!所有客户端在调用时不需要加端口号,仅需http://实例名
spring cloud微服务远程调用方法:
1)、 微服务名获得调用地址,如 http://deptProvider
2)、 通过接口 + 注解 获得服务调用
服务熔断处理是在客户端实现完成的,与服务端没有关系
服务降级能实现解耦合分离

开启hystrix组件的服务降级服务,还需在启动类添加注解 @EnableFeignClients(basePackages = {"com.fwtai"}) 和  @ComponentScan("com.fwtai"),另外在实现接口 FallbackFactory 的服务降级类添加注解 @Component
在实现接口 FallbackFactory 的服务降级类添加注解 @Component,在配置文件application.yml开启 feign.hystrix.enabled=true开启hystrix组件的服务降级服务,还需在启动类添加注解 @EnableFeignClients(basePackages = {"com.fwtai"}) 和  @ComponentScan("com.fwtai")
在启动类添加注解 @EnableFeignClients(basePackages = {"com.fwtai"}) 和  @ComponentScan("com.fwtai"),在配置文件application.yml开启 feign.hystrix.enabled=true hystrix组件的服务降级服务,另外在实现接口 FallbackFactory 的服务降级类添加注解 @Component
zuul提供了代理 + 路由 + 过滤功能
zuul需要和eureka进行整合,zuul自身注册到eureka的服务治理下的应用,同时从Eureka中获得其他微服务的消息,随即以后访问微服务都是通过Zuul跳转得到,
原地址 http://127.0.0.1:8001/dept/get/1
加入zuul地址 http://127.0.0.1:9527/deptprovider/dept/get/1
区别是 deptprovider 是http://127.0.0.1:8001的服务提供者spring.application.name的实例名,注意区分大小写哦,一般是小写能正常访问
zuul路由访问映射规则

1、新建maven父工程
2、创建子模块
3、创建配置文件 application.yml 文件
4、创建实体类 ORM 一一对一的,dept orm mysql->dept(table) 类和表的关系映射,微服务必须序列化
5、编写xml文件
6、创建与xml对应的接口文件
7、提供公共模块,把GAV依赖注入进去即可
------------------------------------------
GAV:
<groupId>com.fwtai</groupId>
<artifactId>microservicecloud</artifactId>
<version>1.0.1</version>
------------------------------------------
8、接口的实现模块和接口的调用只需添加接口的依赖即可
9、@Configuration配置   ConfigBean = applicationContext.xml
10、bean的注解的作用是 <bean id="userServcie" class="com.fwtai.UserServiceImpl">
10、在服务消费方中的controller层和service层没有什么关系了,controller只管调用啦!!!即 在服务消费方中,我只管吃蛋糕,我更不用管关心做蛋糕,所以在消费方没有service层
11、eureka的集群,需要修改etc [C:\Windows\System32\drivers\etc\hosts] 文件添加如下内容
# 用一个名字,映射不同的别名
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
127.0.0.1 eureka7003.com
各个eureka的集群配置文件application.yml配置如下
7001机器
server:
  port: 7001
eureka:
  instance:
    hostname: eureka7001.com #域名的区分及映射,需要修改文件 C:\Windows\System32\drivers\etc\hosts
    appname: appEureka7001
  client:
    register-with-eureka: false # false 表示不向注册中心注册自己
    fetch-registry: false # false 表示自己端就是注册中心,我的职责就是维护服务实例,并不需要去检索服务!!!
    service-url:
      defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/ # 设置与eureka Service 交互的地址查询服务和注册服务都需要这个url

7002机器
server:
  port: 7002
eureka:
  instance:
    hostname: eureka7002.com #域名的区分及映射,需要修改文件 C:\Windows\System32\drivers\etc\hosts
    appname: appEureka7002
  client:
    register-with-eureka: false # false 表示不向注册中心注册自己
    fetch-registry: false # false 表示自己端就是注册中心,我的职责就是维护服务实例,并不需要去检索服务!!!
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7003.com:7003/eureka/ # 设置与eureka Service 交互的地址查询服务和注册服务都需要这个url
7003机器
server:
  port: 7003
eureka:
  instance:
    hostname: eureka7003.com #域名的区分及映射,需要修改文件 C:\Windows\System32\drivers\etc\hosts
    appname: appEureka7003
  client:
    register-with-eureka: false # false 表示不向注册中心注册自己
    fetch-registry: false # false 表示自己端就是注册中心,我的职责就是维护服务实例,并不需要去检索服务!!!
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/ # 设置与eureka Service 交互的地址查询服务和注册服务都需要这个url

同时,服务提供者或服务消费者[@EnableEurekaClient]也要注册到多个eureka集群中!!!,其配置如下:
---------------------------------------------------------------------------------------------------------------------------------------------------------
eureka:
  client:
    register-with-eureka: true # 提供者需要注册到注册服务中心去!!!交互的地址查询服务和注册服务都需要这个url
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
服务消费者
eureka:
  client:
    register-with-eureka: false # 服务消费者也需要注册到注册服务中心去???不需要吧!因为只管调用即可
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
---------------------------------------------------------------------------------------------------------------------------------------------------------
12、Ribbon 具有客户端及负载均衡的工具,在服务消费方@EnableEurekaClient添加注解 @LoadBalanced 即可实现 Ribbon 负载均衡的功能,需要添加两个依赖
<!--Ribbon相关的依赖开始,Ribbon 拥有客户端操作功能及附带负载均衡的组件,仅用在服务消费端使用??即在服务消费者使用-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
    <version>1.4.6.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.5.8</version>
</dependency>
<!--Ribbon相关的依赖开始-->
13、添加了Ribbon后在服务消费方在调用服务提供方时无需添加端口的,方可实现集群访问,即可 private final static String URL_PREFIX = "http://deptProvider";// deptProvider 是服务提供方的实例名,这个值是在哪里找到的呢???是在服务提供方的 spring.application.name处找到
@Bean// bean的注解的作用是 <bean id="userServcie" class="com.fwtai.UserServiceImpl">
@LoadBalanced // Ribbon 拥有客户端操作功能及附带负载均衡的功能,且在调用服务提供方时无需添加端口号,即 "http://deptProvider";
public RestTemplate getRestTemplate(){
    return new RestTemplate();
}
14、服务提供者在Ribbon集群之后,调用时不需要加端口号,且集群的所有节点的name的值必须是一致!!!
15、接口 + 注解 [本示例仅供feign负载均衡的算法调用]需要添加依赖 spring-cloud-starter-feign 版本号 1.4.6.RELEASE
16、如果报错 nested exception is java.lang.NoClassDefFoundError: org/apache/http/conn/HttpClientConnectionManager 可能是spring-cloud-starter-feign版本号的问题或添加依赖 httpclient 版本号 4.5.8
总结

使用 使用restTemplate访问restful接口非常的简单粗暴无脑。
(url, requestMap,ResponseBean.class)
这三个参数分别代表 REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。

约定 > 配置 > 编码

controller 或 service层都采用 get 或 list才符合 restFul 的风格

*********************************************************************************
controller层
@RestController //资源 + rest的操作符
@RequestMapping("/dept")
public class DeptController{

    @Autowired
    private DeptService deptService;


    @PostMapping("/add")
    public Integer add(@RequestBody Dept dept){
        return deptService.add(dept);
    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Dept get(@PathVariable("id") final Integer id){
        return deptService.get(id);
    }

    @RequestMapping("/list")
    public List<Dept> list(){
        return deptService.list();
    }
}
*********************************************************************************

---------------------------------------------------------------------------------
service层
@Service
public class DeptServiceImpl implements DeptService{

}
---------------------------------------------------------------------------------

=================================================================================
dao层
@Mapper //import org.apache.ibatis.annotations.Mapper;
public interface DeptDao{
}
=================================================================================

#################################################################################
xml层
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--命名空间是 包名 + 接口-->
<mapper namespace="com.fwtai.dao.DeptDao">

    <update id="addDept" parameterType="Dept">
        INSERT INTO dept(dname,db_source) VALUES (#{dname},#{db_source})
    </update>

    <select id="findById" parameterType="Integer" resultType="Dept">
        SELECT deptno,dname,db_source FROM dept WHERE deptno = #{deptno}
    </select>

    <select id="findAll" resultType="Dept">
        SELECT deptno,dname,db_source FROM dept
    </select>

</mapper>
#################################################################################
服务消费方的controller层
@RestController
public class DeptController{

    //在服务消费方中,我只管吃蛋糕,我更不用管关心做蛋糕,所以在消费方明天service层,仅仅调用:
    @Autowired
    private RestTemplate restTemplate;

}
<!--jetty容器服务器-->
<!--<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-loader-tools</artifactId>
    <version>1.5.9.RELEASE</version>
</dependency>-->

解决实例的info页面报404错误的信息
1、在子模块里的pom.xml添加
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
    <version>1.5.9.RELEASE</version>
</dependency>
2、在父工程的pom.xml添加
<build>
    <!--解决info页面报404错误,即完善info页面的-步骤2,在父工程弄,意思是在目录下 src/main/resources 以 $开头和以$结尾的信息可以显示,一般在子模块添加-->
    <finalName>microservicecloud</finalName>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>true</filtering>
        </resource>
    </resources>
    <plugins>
        <!--解决info页面报404错误,即完善info页面的-步骤3,在父工程弄,意思是在目录下 src/main/resources 以 $开头和以$结尾的信息可以显示,一般在子模块添加-->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-resources-plugin</artifactId>
            <version>3.0.2</version>
            <configuration>
                <delimiters>
                    <delimit>$</delimit>
                </delimiters>
            </configuration>
        </plugin>
    </plugins>
</build>
3、在各个子模块的application.yml文件添加

Hystrix 服务降级处理是在客户端实现完成的,与服务端没有关系

类似于银行的办理窗口的 暂停服务

服务降级将要替换服务熔断的

可以使用 Hystrix 组件的服务降级功能来减少@HystrixCommand的代码量,服务降级详情 com.fwtai.hystrix.DeptHystrixFallbackFactory

本类DeptHystrixFallbackFactory针对接口 com.fwtai.feign.FeignService 里的各个方法进行服务降级,也就是对FeignService的方法每一个熔断机制统一在本类统一处理解决,
以达到在子模块 provider_hystrix 里的controller层的每个方法的注解@HystrixCommand进行解耦了.还需在com.fwtai.feign.FeignService的注解@FeignClient添加属性fallbackFactory=本类.class
