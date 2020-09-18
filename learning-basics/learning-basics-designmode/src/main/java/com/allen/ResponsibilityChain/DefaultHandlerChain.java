package com.allen.ResponsibilityChain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JUN @Description TODO
 * @createTime 22:48
 */
public class DefaultHandlerChain implements HandlerChain {

    private int pos = 0;
    private List<Handler> handlers = new ArrayList<>();

    @Override
    public void addHandler(Handler handler) {
        handlers.add(handler);
    }

    @Override
    public void doHandler(RequestSource requestSource) {
        if (pos < handlers.size()) {
            handlers.get(this.pos++).doHandler(requestSource, this);
        }
    }
}
