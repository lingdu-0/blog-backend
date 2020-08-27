package com.wb.service.base;

import com.wb.mapper.*;
import com.wb.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    //统一注入dao
    @Autowired
    protected AlbumMapper albumMapper;
    @Autowired
    protected ArticleMapper articleMapper;
    @Autowired
    protected ArticleTypeMapper articleTypeMapper;
    @Autowired
    protected EssayMapper essayMapper;
    @Autowired
    protected LeaveWordMapper leaveWordMapper;
    @Autowired
    protected CommentMapper commentMapper;
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected LogMapper logMapper;
    @Autowired
    protected ServletContext servletContext;
    @Autowired
    protected HttpSession session;
    @Autowired
    protected LogService logService;
}
