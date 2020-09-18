package com.allen.ResponsibilityChain;

/**
 * @author JUN @Description TODO
 * @createTime 22:58
 */
public class LoginHandler implements Handler {

    @Override
    public void doHandler(RequestSource requestSource, HandlerChain handlerChain) {
        System.out.println("登录认证");
        handlerChain.doHandler(requestSource);
    }
}
