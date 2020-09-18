package com.allen.ResponsibilityChain;

/**
 * @author JUN @Description TODO
 * @createTime 22:45
 */
public interface HandlerChain {

    void addHandler(Handler handler);

    void doHandler(RequestSource requestSource);
}
