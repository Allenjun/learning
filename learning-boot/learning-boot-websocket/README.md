## WebSocket浅析
传统的HTTP 协议有一个缺陷：通信只能由客户端发起, 客户端要获知实时动态信息。我们只能使用"轮询"。这种方式效率低，浪费资源。

WebSocket是一种网络通信协议，它的最大特点就是，服务器可以主动向客户端推送信息，客户端也可以主动向服务器发送信息，是真正的全双工通信。

一般的，网页脚本例子如下：
```javascript
let ws = new WebSocket("ws://127.0.0.1:5997/mypath");
ws.onopen = function(event) {
    console.info("建立连接")
}
ws.onclose = function(event) {
  console.info("关闭连接")
}
ws.onerror = function(event) {
  console.error("发生异常", event);
}
ws.onmessage = function(event) {
  console.info("接受服务端信息：" + event.data);
}
// 发送信息
ws.send("Hi, 我是Chan");
// 关闭连接
ws.close();
```

### SockJS
SockJ是一个JavaScript库，提供了一个类似websosocket的对象。当浏览器支持webSocket时，优先使用原生的WebSocket；
当浏览器不支持WebSocket时，使用其他传输协议/轮询或者其他方法去实现WebSocket
使用方法如下：
```html
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script>
let sock = new SockJS('http://127.0.0.1:5997/mypath');
sock.onopen = function() {
    console.log('open');
    sock.send('test');
};

sock.onmessage = function(e) {
    console.log('message', e.data);
    sock.close();
};

sock.onclose = function() {
    console.log('close');
};
</script>
```

### STOMP
STOMP即Simple (or Streaming) Text Orientated Messaging Protocol，简单(流)文本定向消息协议。
它提供了一个可互操作的连接格式，允许STOMP客户端与任意STOMP消息代理（Broker）进行交互。
STOMP协议由于设计简单，易于开发客户端，因此在多种语言和多种平台上得到广泛地应用。

简单来讲，就是在我们的websocket（TCP）上面加了一层协议，使双方遵循这种协议来发送消息
