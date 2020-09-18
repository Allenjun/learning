package com.allen.learningbootes.pojo.PO;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/08/08 12:11:00
 */

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "logstash-learning-2019.08.08", type = "doc")
public class LogPO {

    private String logLevel;
    private String serviceName;
    private String pid;
    private String thread;
    private String classname;
    private String rest;
}
