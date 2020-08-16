package com.allen.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Set;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author JUN
 * @Description TODO
 * @createTime 17:15
 */
public class NIO {
    
    /**
     * @description: 缓冲区写入/读取数据
     */
    @Test
    public void buffer() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);  // 默认写模式
        buffer.put("Java".getBytes());  // 写入缓冲区
        buffer.flip();  // 切换为读模式
        byte[] carrier = new byte[buffer.limit()];
        buffer.get(carrier);    // 读取缓冲区数据
        System.out.println(new String(carrier));
        buffer.clear(); // 切换为写模式
        System.out.println(buffer.limit());
    }
    
    /**
     * @description: 通过channel把文件数据读取到缓冲区并打印出来
     */
    @Test
    public void inChannel() {
        try {
            FileInputStream fileInputStream = new FileInputStream("D:/A_TEST.sql");
            FileChannel channel = fileInputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            byte[] bytes = new byte[1024];
            while (channel.read(buffer) != -1) {
                buffer.flip();
                int limit = buffer.limit();
                buffer.get(bytes, 0, limit);
                System.out.println(new String(bytes, 0, limit));
                buffer.clear();
            }
            channel.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @description: 通过channel把缓冲区的数据写到文件系统
     */
    @Test
    public void outChannel() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:/abc.txt");
            FileChannel channel = fileOutputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("Java".getBytes());
            buffer.flip();
            channel.write(buffer);
            channel.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @description: 直接缓冲区
     *                  直接缓冲区是在Java堆外开辟的一处空间，不收GC管理，创建/销毁的成本都比非直接缓冲区高
     *               为什么要使用直接缓冲区？
     *               1. 普通缓冲区是受GC管理的，但是由于GC机制，不是立刻得到回收的，有可能会经过移动，
     *               如果缓冲区有大量的字节，移动就会显得笨重
     *               2. 使用直接缓冲区不需要经过从内核空间到用户空间的拷贝
     */
    @Test
    public void directBuffer() {
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);
    }
    
    @Test
    public void tcpServer() {
        ServerSocketChannel serverSocketChannel = null;
        FileChannel fileChannel = null;
        try {
            fileChannel = FileChannel.open(Paths.get("D:/abc.txt"), StandardOpenOption.WRITE);
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8999));
            serverSocketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                if (selector.select() > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel socketChannel = channel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        }
                        if (selectionKey.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                            while (socketChannel.read(buffer) > 0) {
                                buffer.flip();
                                while (buffer.hasRemaining()) {
                                    fileChannel.write(buffer);
                                }
                                buffer.clear();
                            }
                            buffer.put("已读信息".getBytes());
                            buffer.flip();
                            socketChannel.write(buffer);
                            buffer.clear();
                        }
                        iterator.remove();
                    }
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocketChannel != null) {
                try {
                    serverSocketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Test
    public void tcpClient() {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8999));
            while (!socketChannel.finishConnect()) {

            }
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("X".getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            buffer.clear();
            int len;
            while ((len = socketChannel.read(buffer)) > 0) {
                buffer.flip();
                System.out.println(new String(buffer.array(), 0, len));
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Test
    public void tcpUDP() {
    }
    
    @Test
    public void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());
        System.out.println(buffer.mark());
        
        buffer.put("Java".getBytes());
        
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());
        System.out.println(buffer.mark());
        
        buffer.flip();
        
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());
        System.out.println(buffer.mark());
        
        byte[] bs = new byte[buffer.limit()];
        buffer.get(bs);
        
        System.out.println(new String(bs));
    }
    
    @Test
    @SneakyThrows
    public void test2() {
        
        @Cleanup FileChannel channel1 = FileChannel.open(Paths.get("D:/A_TEST.sql"), StandardOpenOption.READ);
        @Cleanup FileChannel channel2 = FileChannel
            .open(Paths.get("D:/D_TEST.sql"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        
        while (channel1.read(byteBuffer) > 0) {
            byteBuffer.flip();
            channel2.write(byteBuffer);
            byteBuffer.clear();
        }
        
    }
    
    @Test
    @SneakyThrows
    public void test3() {
        
        @Cleanup FileChannel channel1 = FileChannel.open(Paths.get("D:/A_TEST.sql"), StandardOpenOption.READ);
        @Cleanup FileChannel channel2 = FileChannel
            .open(Paths.get("D:/C_TEST.sql"), StandardOpenOption.WRITE, StandardOpenOption.CREATE,
                StandardOpenOption.READ);
        
        MappedByteBuffer map1 = channel1.map(MapMode.READ_ONLY, 0, channel1.size());
        MappedByteBuffer map2 = channel2.map(MapMode.READ_WRITE, 0, channel1.size());
        
        byte[] bytes = new byte[map1.limit()];
        
        map1.get(bytes);
        map2.put(bytes);
        
    }
    
    @Test
    @SneakyThrows
    public void test4() {
        Selector selector = Selector.open();
        
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        while (true) {
            int select = selector.select();
            if (select < 1) {
                continue;
            }
            System.out.println(select);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = ssc.accept();
                    if (socketChannel != null) {
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE,
                            ByteBuffer.allocateDirect(1024));
                    }
                } else if (selectionKey.isReadable()) {
                    SocketChannel sc = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    int read = sc.read(byteBuffer);
                    while (read > 0) {
                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()) {
                            System.out.print((char) byteBuffer.get());
                        }
                        byteBuffer.clear();
                        read = sc.read(byteBuffer);
                    }
                    System.out.println();
                    sc.close();
                } else if (selectionKey.isWritable()) {

                }
                
                keyIterator.remove();
            }
        }
        
    }
    
    @Test
    @SneakyThrows
    public void test5() {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(9999));
        
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("Java".getBytes());
        buffer.flip();
        
        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
        
        socketChannel.close();
    }
    
}
