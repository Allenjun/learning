本项目主要是学习使用Consul作为服务注册、发现中心，并利用Fegin + Hystrix + Ribbon实现接口形式的服务调用+熔断降级+负载均衡
### 主要目的
1. 使用Consul客户端完成服务注册、发现
2. Fegin实现接口形式的远程服务调用
3. Hystrix实现服务的熔断降级
4. Ribbon实现负载均衡
5. Spring-Cloud-Config客户端连接远程配置中心

#### Consul常用命令
```bash
# 调试模式 + 开启UI面板 + 指定节点名 + 指定服务器ip
./consul agent -dev -ui -node=consul-dev -client=xxx.xxx.xx.xxx > nohup.out &
# 关闭服务端
./consul leave
```
> Feign读作 /feɪn/

#### 连接远程配置中心
> 配置中心的配置要在bootstrap.yml中配置
1. 远程配置中心未注册为服务
```yaml
spring:
  application:
    name: learning-cloud-service
  cloud:
    config:
      uri: http://127.0.0.1:6997/
      profile: default
      label: master
```
2. 远程配置中心已注册为服务
```yaml
spring:
  application:
    name: learning-cloud-service
  cloud:
    config:
      uri: http://127.0.0.1:6997/
      profile: default
      label: master
```

