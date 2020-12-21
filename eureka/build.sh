#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true

docker build -t registry.cn-beijing.aliyuncs.com/lcyun/eureka .

docker push registry.cn-beijing.aliyuncs.com/lcyun/eureka