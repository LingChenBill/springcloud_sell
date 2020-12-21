# 拉取jdk镜像.
FROM openjdk:8-alpine

# 添加微服务jar,并命名为app.jar.
ADD target/*.jar app.jar

# 暴露端口号.
EXPOSE 8761

# 运行jar.
ENTRYPOINT ["java", "-jar", "/app.jar"]