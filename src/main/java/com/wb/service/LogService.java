package com.wb.service;

import com.wb.entity.Log;
import com.wb.service.base.BaseService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public interface LogService extends BaseService<Log> {
    //删除所有记录
    int delete();

    int insertOne(ServletContext application, HttpSession session, String operation);

}
