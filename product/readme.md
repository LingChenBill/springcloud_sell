# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/#build-image)

1.maven打包:  
  解压好maven.zip包到目录,配置好~/.bash_profile  
  source ~/.bash_profile  
  在idea的Terminal命令窗口中,若mvn -v不起作用.重新执行:  
  source ~/.bash_profile  
  打包命令:  
  mvn -Dserver.test.skip=true -U clean install  


