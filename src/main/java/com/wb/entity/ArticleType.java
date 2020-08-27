package com.wb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章类别类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleType implements Serializable {
    private static final long serialVersionUID = -3833943348844438725L;
    private Integer articleTypeId;

    private String articleTypeName;
}