### Feign + Ribbon + Hystrix
- Feign 远程过程调用
- Ribbon 基于轮询的负载均衡
- Hystrix 服务容错框架（降级、断路、依赖隔离）
> 注意：openFeign已经集成了三个框架
### 配置
1. 通过配置文件
```yaml
feign:
  hystrix:
    enabled: true # 开启hystrix
  client:
    config:
      default:  # 全局设置
        connectTimeout: 5000
        readTimeout: 5000
        loggerLevel: basic
      learning-cloud-service: # 针对指定服务
        connectTimeout: 6000
```
2. JavaConfig
```java
@Configuration
public class FeignConfiguration {
    
    @Bean
    public SpringFormEncoder springFormEncoder() {
        return new SpringFormEncoder();     // 使用Feign客户端上传文件需要表单提交编码器
    }
    
}
```
> 可以参考默认的配置类FeignClientsConfiguration

### 超时问题、重试策略

### 连接到Hystrix Dashboard

### sleuth(分布式服务跟踪)
>  读作：/sluːθ/
