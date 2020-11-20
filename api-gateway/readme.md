# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.0/maven-plugin/reference/html/#build-image)

### 1.port  
-Dserver.port=9000  

### 2.配置网关
在启动类上加上注解:  
@EnableZuulProxy  
原product微服务地址:  
http://localhost:8090/product/list  
通过网关访问(application_name/path):  
http://localhost:9000/product/product/list  

### 3.自定义路由
在bootstrap.yml中配置路由规则  
```
zuul:
  routes:
    myProduct:
      path: /myProduct/**
      serviceId: product
```
http://localhost:9000/myProduct/product/list  

### 4.排除路由
```
zuul:
  routes:
    # 配置路由规则
#    myProduct:
#      path: /myProduct/**
#      serviceId: product
    # 简洁写法
    product: /myProduct/**
  # 排除访问地址
  ignored-patterns:
    - /myProduct/product/listForOrder
    - /product/product/listForOrder
```
访问不到:  
localhost:9000/product/product/listForOrder  
使用通配符:  
```
zuul:
  routes:
    # 配置路由规则
#    myProduct:
#      path: /myProduct/**
#      serviceId: product
    # 简洁写法
    product: /myProduct/**
  # 排除访问地址
  ignored-patterns:
#    - /myProduct/product/listForOrder
#    - /product/product/listForOrder
    - /**/product/listForOrder
```
访问不到:  
localhost:9000/myProduct/product/listForOrder  
