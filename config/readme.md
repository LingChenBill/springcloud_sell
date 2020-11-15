# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/#build-image)

### Guides
The following guides illustrate how to use some features concretely:

* [Centralized Configuration](https://spring.io/guides/gs/centralized-configuration/)

### App端口:  
-Dserver.port=80  

1. 配置application.yml中的git配置时,出错.  
c.s.e.MultipleJGitEnvironmentRepository : Stacktrace for: Could not fetch remote for main remote: https://github.com/  

打开日志打印信息  
logging:  
  level:  
    com.netflix.discovery: 'OFF'  
    org.springframework.cloud: 'DEBUG'  

解决策:  
打开force-pull: true  
basedir: /Users/zhuyangze/Documents/fork/springcloud/SpringCloud_Sell/config/basedir  
default-label: main  

2. 在地址栏中访问:  
http://localhost:8080/order-dev.yml  
看是否出现git中的配置文件信息.  

3. git中的config配置文件的加载顺序:  
order-test.yml  
order.yml  
加载后的结果是会把两者中的内容合并.  
order.yml中可以配置两个文件都相同的内容.  

4. git中文件修改后,要实现自动刷新.   
先在pom文件中加入依赖:spring-cloud-starter-bus-amqp(利用rabbitMQ)  
配置文件中暴露端口:actuator/bus-refresh  
在调用方微服务中加入@RefreshScope注解.  
curl -v -X POST "http://localhost:8080/actuator/bus-refresh"  


