# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/#build-image)

### Guides
The following guides illustrate how to use some features concretely:

* [Centralized Configuration](https://spring.io/guides/gs/centralized-configuration/)

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


