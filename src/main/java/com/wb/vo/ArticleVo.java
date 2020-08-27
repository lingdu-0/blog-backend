package com.wb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo {
    private Integer id;
    //文章标题
    private String title;
    //文章内容
    private String content;
    //文章描述
    private String describe;
    //文章类别
    private Integer category;
}
