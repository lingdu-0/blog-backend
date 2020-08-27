package com.wb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = -8922465054818477477L;
    private Integer commentId;
    private Integer commentType;
    private Date commentDate;
    private Integer articleId;
    private Article article;
    private Integer essayId;
    private Essay essay;
    private String commentDetail;
    private User user;
    //点赞作品编号
    private Integer composeId;
}