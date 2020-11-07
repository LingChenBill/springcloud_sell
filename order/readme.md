# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/#build-image)

1. 引导配置  
bootstrap.yml  

2. 配置文件的加载顺序:  
先找eureka, 再找bootstrap.yml中的配置的config微服务,拉取配置信息文件.  

3. 测试拉取微服务config中的配置文件  
http://localhost:8081/env/print  
