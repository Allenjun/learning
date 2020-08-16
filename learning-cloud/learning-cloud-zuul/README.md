Zuul服务网关使用步骤
1. 配置MAVEN
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
```

2. 开启注解
```java
@EnableZuulProxy
```

3. 配置application.yml
```yaml
zuul:
  routes:
    # 服务id
    learning-cloud-hystrix-dashboard: /learning-cloud-hystrix-dashboard/**
```
当没有注册为服务时,采用以下方式配置
```yaml
zuul:
  routes:
    learning-cloud-hystrix-dashboard: 
      path: /learning-cloud-hystrix-dashboard/**
      url: http://127.0.0.1:6998/
```

### ZuulFilter
相当于过滤器，可以实现诸如权限控制、日志记录等逻辑
```java

```