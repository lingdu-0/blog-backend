package com.wb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album implements Serializable {
    private static final long serialVersionUID = 7966356896800094509L;
    private Integer albumId;

    private String albumTitle;

    private Date albumDate;

    private String albumAddress;

    private String albumImg;

    private Integer albumPraise;
}