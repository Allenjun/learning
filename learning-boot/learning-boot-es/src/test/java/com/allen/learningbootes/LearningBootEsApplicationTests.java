package com.allen.learningbootes;

import com.allen.learningbootes.pojo.PO.LogPO;
import java.util.List;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningBootEsApplicationTests {
    
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    
    @Test
    public void contextLoads() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(QueryBuilders.matchPhrasePrefixQuery("rest", "发送").slop(1).maxExpansions(2))
            .build();
        List<LogPO> list = elasticsearchTemplate.queryForList(searchQuery, LogPO.class);
        System.out.println(list);
    }
    
}
