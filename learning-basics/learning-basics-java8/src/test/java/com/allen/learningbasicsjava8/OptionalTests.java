package com.allen.learningbasicsjava8;

import java.util.Optional;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

/**
 * @author admin
 * @version 1.0.0
 * @Description Optional用法介绍
 * @createTime 2019/07/19 12:01:00
 */
public class OptionalTests {
    
    private Boy boy;
    
    @Before
    public void init() {
        boy = new Boy();
        boy.setUsername("allen");
    }
    
    @Test
    public void test1() {
        Optional<Boy> op1 = Optional.of(this.boy);  // 创建Optional，如果是NULL，则抛异常
        
        Optional<Boy> op2 = Optional.ofNullable(this.boy);  // 创建Optional，如果是NULL，则返回value是NULL的创建Optional
        
        Optional<Boy> op3 = Optional.empty();   /// 创建value是NULL的Optional
        
        op1.get();  // 获取value,如果为NULL，则抛异常
        
        op1.orElseThrow(IllegalArgumentException::new);     // 获取value,如果为NULL，则抛指定异常
        
        op1.orElse(new Boy());  // 如果是NULL，则返指定对象
        
        op1.orElseGet(Boy::new);  // 如果是NULL，则返回表达式的对象（与orElse的区别是：不是NULL则不执行，而orElse则无论如何都会创建新对象）
        
        op1.ifPresent(System.out::println); // 如果不是NULL，则执行指定表达式
        
        Optional<String> op4 = op1.map(Boy::getUsername);   // 获取value中的属性值并返回一个新的Optional(自动创建)
        
        op1.flatMap(boy -> Optional.of(boy.getUsername())); // 获取value中的属性值并返回一个新的Optional(必须自己创建)
        
        Optional<Boy> op5 = op1.filter(boy -> "allen".equals(boy.getUsername()));   // 过滤后再返回Optional
    }
    
    @Data
    class Boy {
        
        private String username;
    }
    
}
