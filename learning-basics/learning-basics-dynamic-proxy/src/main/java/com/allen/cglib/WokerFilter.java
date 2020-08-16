package com.allen.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.CallbackFilter;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/16 20:57:00
 */
public class WokerFilter implements CallbackFilter {
    
    @Override
    public int accept(Method method) {
        return 0;
    }
}
