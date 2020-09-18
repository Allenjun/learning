package com.allen.learningcloudopenfeign;

import com.allen.learningcloudopenfeign.service.openfeign.RpcService;
import com.allen.web.vo.ArticleDetailVO;
import com.allen.web.vo.Resp;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningCloudRpcServiceApplicationTests {

    @Autowired
    RpcService rpcService;
    @Autowired
    LoadBalancerClient client;

    /*测试使用Feign上传文件*/
    @Test
    public void uploadImage() {
        File file = new File("D:/111.txt");
        DiskFileItem fileItem =
                (DiskFileItem)
                        new DiskFileItemFactory()
                                .createItem("file", MediaType.TEXT_PLAIN_VALUE, true, file.getName());

        try (InputStream input = new FileInputStream(file);
             OutputStream os = fileItem.getOutputStream()) {
            IOUtils.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        MultipartFile multi = new CommonsMultipartFile(fileItem);

        log.info(rpcService.upload(multi));
    }

    @Test
    @SneakyThrows
    public void info() {
        System.out.println(rpcService.info());
    }

    @Test
    @SneakyThrows
    public void bigload() {
        System.out.println(rpcService.bigload());
    }

    @Test
    @SneakyThrows
    public void download() {
        ResponseEntity<byte[]> download = rpcService.download("D:/111.txt");
        byte[] body = download.getBody();
        if (body == null) {
            body = new byte[0];
        }
        FileUtils.writeByteArrayToFile(new File("D:/tmp/" + download.getHeaders().getContentDisposition().getFilename()), body);
    }

    @Test
    @SneakyThrows
    public void articleDetail() {
        ArticleDetailVO req = new ArticleDetailVO("allen", "18.5", "love you", "no pain no game", new Date());
        ArticleDetailVO articleDetailVO = rpcService.articleDetail(req);
        System.out.println(articleDetailVO);
    }

    @Test
    @SneakyThrows
    public void object() {
        Resp<ArticleDetailVO> object = rpcService.object();
        System.out.println(object);
    }
}
