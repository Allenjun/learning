Oauth2是一种授权框架，该框架的核心是**向第三方应用发送令牌**，使第三方应用可以在不知道用户名和密码的前提下获得用户资源的授权。

有四种授权模式
- 简易模式(implicit)  /ɪmˈplɪsɪt/
- 密码模式(password)
- 授权码模式(authorization_code) /ˌɔːθərəˈzeɪʃn/
- 凭证模式(client_credentials) /krəˈdenʃl/

### 名词解释
- 第三方应用程序（Third-party application）： 又称之为客户端（client）。
- 资源所有者（Resource Owner）： 又称之为用户（user）。
- 认证服务器（Authorization server）： 即服务提供商专门用来处理认证的服务器，简单点说就是登录功能（验证用户的账号密码是否正确以及分配相应的权限）
- 资源服务器（Resource server）： 即服务提供商存放用户生成的资源的服务器。它与认证服务器，可以是同一台服务器，也可以是不同的服务器。简单点说就是资源的访问入口，比如上节中提到的“云笔记服务”和“云相册服务”都可以称之为资源服务器。

### Springboot Security Oauth2
#### 访问接口
```http request
# 密码模式
http://127.0.0.1:5999/oauth/token?username=user_1&password=123456&grant_type=password&scope=select&client_id=client_1&client_secret=test

# 凭证模式(适合无页面的后台应用)[POST]
http://127.0.0.1:5999/oauth/token?client_id=client_1&client_secret=test&grant_type=client_credentials

# 简易模式(适合只有前端的应用)[GET]
http://127.0.0.1:5999/oauth/authorize?client_id=wechat&response_type=token&scope=all&redirect_uri=http://127.0.0.1:5998/pay

# 授权码模式
# 获取code
http://127.0.0.1:5999/oauth/authorize?client_id=wechat&scope=all&response_type=code&redirect_uri=http://127.0.0.1/ac
# 获取令牌
http://127.0.0.1:5999/oauth/token?client_id=wechat&client_secret=mysecret&code=cBuQr8&grant_type=authorization_code
```

### 名词读音
grant /ɡrænter/