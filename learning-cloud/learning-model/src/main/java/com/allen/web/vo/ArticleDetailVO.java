package com.allen.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVO {

    private String author;
    private String price;
    private String descrption;
    private String title;
    private Date publishTime;
}
