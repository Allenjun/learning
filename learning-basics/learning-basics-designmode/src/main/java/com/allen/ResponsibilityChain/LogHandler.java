package com.allen.ResponsibilityChain;

/**
 * @author JUN @Description TODO
 * @createTime 22:57
 */
public class LogHandler implements Handler {

    @Override
    public void doHandler(RequestSource requestSource, HandlerChain handlerChain) {
        System.out.println("日志记录");
        handlerChain.doHandler(requestSource);
    }
}
