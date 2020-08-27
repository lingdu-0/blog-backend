package com.wb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 随笔类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Essay implements Serializable {
    private static final long serialVersionUID = -8653142292859582745L;
    private Integer essayId;

    private String essayTitle;

    private Date essayDate;

    private Integer essayPraise;

    private Integer essayNumber;

    private String essayImg;

    private Integer essayState;

    private String essayDetail;

    private Integer zanState;

    private Integer commentState;

}