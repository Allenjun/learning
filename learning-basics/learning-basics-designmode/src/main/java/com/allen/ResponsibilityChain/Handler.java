package com.allen.ResponsibilityChain;

/**
 * @author JUN @Description TODO
 * @createTime 22:42
 */
public interface Handler {

    void doHandler(RequestSource requestSource, HandlerChain handlerChain);
}
