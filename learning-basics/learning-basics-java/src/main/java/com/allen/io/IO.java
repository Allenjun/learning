package com.allen.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PipedInputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

/**
 * @author JUN
 * @Description TODO
 * @createTime 21:14
 */
public class IO {
    
    @Test
    public void test1() {
        try {
            FileReader fileReader = new FileReader("D:/A_TEST.sql");
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("D:/A_TEST.sql"),
                StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            RandomAccessFile randomAccessFile = new RandomAccessFile("D:/A_TEST.sql", "rw");
            PipedInputStream pipedInputStream = new PipedInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("D:/A_TEST.sql"));
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:/A_TEST.sql"));
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream("D:/A_TEST.sql"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
