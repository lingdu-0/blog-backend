package com.wb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private static final long serialVersionUID = 3307478886483463360L;
    private Integer articleId;

    private Integer articleTypeId;

    private ArticleType articleType;

    private String articleTitle;

    private Date articleDate;

    private Integer articleAccess;

    private Integer articleNumber;

    private Integer articlePraise;

    private String articleImg;

    private String articleDetail;

    private String articleDescribe;

    private Integer articleState;

}