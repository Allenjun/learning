package com.allen.ResponsibilityChain;

/**
 * @author JUN @Description TODO
 * @createTime 22:47
 */
public class App {

    public static void main(String[] args) {
        HandlerChain handlerChain = new DefaultHandlerChain();
        handlerChain.addHandler(new LogHandler());
        handlerChain.addHandler(new LoginHandler());
        handlerChain.doHandler(new RequestSource());
    }
}
