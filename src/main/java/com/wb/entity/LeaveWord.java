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
public class LeaveWord implements Serializable {
    private static final long serialVersionUID = -5114443984385764265L;
    private Integer leaveWordId;
    private String leaveWordDetail;
    private Date leaveWordDate;
    private User user;
}