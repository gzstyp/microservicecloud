
示例仅供feign负载均衡的算法调用

feign组件是接口 + 注解 [本示例仅供feign负载均衡的算法调用]需要添加依赖 spring-cloud-starter-feign 版本号 1.4.6.RELEASE,feign默认的负载均衡是轮询的方式,feign包含Ribbon???,本接口是给子模块 webfeign 的controller层调用的!!!
未提供Hystrix组件时的注解@FeignClient(value = "deptProvider") //deptProvider是针对调用哪一个微服务名!!!,即服务提供方[接口实现方]的 spring.application.name=deptProvider，[deptProvider不区分大小写],在该接口的功能在子模块euraka700x下已实现
本注解启用了降级功能,其意义是给子模块 webfeign 的controller层提供了一锅端处理,deptProvider是针对调用哪一个微服务名!!!,即服务提供方[接口实现方]的 spring.application.name=deptProvider，[deptProvider不区分大小写],在该接口的功能在子模块euraka700x下已实现

含本模块的及子模块的webfeign内容：
<dependencies>
    <!--专为feign添加的依赖,是想通过 接口+注解来调用服务 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-feign</artifactId>
        <version>1.4.5.RELEASE</version>
    </dependency>
</dependencies>