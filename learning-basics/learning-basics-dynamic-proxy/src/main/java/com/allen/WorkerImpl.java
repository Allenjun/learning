package com.allen;

import java.text.MessageFormat;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/16 18:29:00
 */
public class WorkerImpl implements Worker {

    @Override
    public boolean work(String name) {
        System.out.println(MessageFormat.format("{0}在工作", name));
        return true;
    }
    
    public boolean overwork(String name) {
        System.out.println(MessageFormat.format("{0}在超负荷工作", name));
        return true;
    }
    
}
