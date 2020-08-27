package com.wb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 留言类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {
    private static final long serialVersionUID = 5205052908933664118L;
    private Integer logId;
    private User user;
    private String operation;
    private String logIp;
    private Date date;
}
