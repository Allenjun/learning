package com.allen.learningbootcache;

import com.allen.learningbootcache.service.LogServiceWithCaffeineCache;
import com.allen.learningbootcache.service.LogServiceWithRedisCache;
import com.baidu.aip.nlp.AipNlp;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author chan
 * @date 2020/10/23
 * description: TODO
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class LearningBootCacheApplicationTests {

    @Autowired
    LogServiceWithRedisCache logServiceWithRedisCache;

    @Autowired
    LogServiceWithCaffeineCache logServiceWithCaffeineCache;

    @Test
    public void test1() {
        System.out.println(logServiceWithRedisCache.get("aa"));
        System.out.println(logServiceWithRedisCache.get("aa"));
    }

    @Test
    public void test2() {
        System.out.println(logServiceWithCaffeineCache.get("aa"));
        System.out.println(logServiceWithCaffeineCache.get("aa"));
    }

    @SneakyThrows
    @Test
    public void test3() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/333.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/444.txt")));
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            try {
                ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://api.map.baidu.com/place/v2/suggestion?query=" + line + "&region=全国&output=json&ak=BOSgGwdAq8z3totFl5aqTQqNRzmBTo4g", String.class);
                JsonNode jsonNode = new ObjectMapper().readTree(forEntity.getBody());
                JsonNode result = jsonNode.get("result");
                Iterator<JsonNode> iterator = result.iterator();
                while (iterator.hasNext()) {
                    JsonNode next = iterator.next();
                    System.out.println(next.get("city"));
                    bufferedWriter.write(next.get("city").toString().replaceAll("\"", ""));
                    break;
                }
                Thread.sleep(2500);
            } catch (Exception ex) {

            } finally {
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.flush();
    }

    @SneakyThrows
    @Test
    public void test4() {
        String API_KEY = "tVvTrsQ0GuHNZ5uURSZXtYtI";
        String APP_ID = "23106868";
        String SECRET_KEY = "71Ff6f5BozVZfUt5GEHX1q1AmyNWKXjm";
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
        JSONObject jsonObject = client.address("崇仁2019年度长江经济带（元家水流域）农业面源污染综合治理项目畜禽养殖粪便污染治理工程搪瓷罐体采购项目", new HashMap<>());
        System.out.println(jsonObject);
    }

}



