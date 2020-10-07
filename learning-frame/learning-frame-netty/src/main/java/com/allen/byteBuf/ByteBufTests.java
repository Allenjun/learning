package com.allen.byteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author chan
 * @date 2020/9/25
 * description: ByteBuf深入解析
 */
@Slf4j
public class ByteBufTests {

    @Test
    public void test1() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("abc".getBytes(CharsetUtil.UTF_8));
        if (byteBuf.hasArray()) {
            byte[] bytes = byteBuf.array();
            int offset = byteBuf.readerIndex() + byteBuf.arrayOffset();
            int length = byteBuf.readableBytes();
            log.info(new String(bytes, offset, length, CharsetUtil.UTF_8));
        }
    }

    @Test
    public void test2() {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        compositeByteBuf.addComponent(Unpooled.copiedBuffer("abc".getBytes(CharsetUtil.UTF_8)));
        compositeByteBuf.addComponent(Unpooled.copiedBuffer("bcd".getBytes(CharsetUtil.UTF_8)));
        for (ByteBuf byteBuf : compositeByteBuf) {
            System.out.println(byteBuf.toString());
        }
    }

    @Test
    public void test3() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("abc".getBytes(CharsetUtil.UTF_8));
        for (int i = 0; i < byteBuf.readableBytes(); i++) {
            byte aByte = byteBuf.getByte(i);
            System.out.println((char) aByte);
        }
    }

    @Test
    public void test4() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("abc".getBytes(CharsetUtil.UTF_8));
        log.info("{}", byteBuf.refCnt());
    }

}
