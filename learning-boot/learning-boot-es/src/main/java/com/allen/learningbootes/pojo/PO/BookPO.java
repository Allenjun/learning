package com.allen.learningbootes.pojo.PO;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author admin
 * @version 1.0.0
 * @Description
 *      TODO
 * @createTime 2019/08/14 17:15:00
 */
@Document(indexName = "book", type = "test", shards = 1, replicas = 0, refreshInterval = "1s", createIndex = true)
public class BookPO {

    @Field(type = FieldType.Keyword)
    private String title;
    private Double price;
    private String author;
    private String content;

}
