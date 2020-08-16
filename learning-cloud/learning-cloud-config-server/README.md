spring-cloud-config-server是分布式配置中心，它是一个独立的微服务应用，用来连接配置仓库并为客户端提供获取配置信息、加密/解密信息等访问接口
### 使用步骤
1. 引入Maven依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```
2. 开启服务注解
```java
@EnableConfigServer
```
3. 配置application.yml
```yaml
spring:
  application:
    name: learning-cloud-config-server
  cloud:
    config:
      server:
        git:
          uri: https://github.com/Allenjun/learning-cloud-config-server-repo.git
          password: d9863711h
          username: allenjun@gmail.com
```
4. 访问url获取配置信息
```http request
# 查看指定配置文件信息
http://127.0.0.1:8080/{application-name}/{profile}/{label}
# 查看全部配置文件信息
http://127.0.0.1:8080/{application-name}.yml
```
> {application-name}即应用名称  
> {profile}即运行环境  
> {label}即远程仓库分支名  

### 注册为服务
要注册微服务只需要配置application.yml即可，真正有变化的是客户端的配置方式