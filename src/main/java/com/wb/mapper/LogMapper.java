package com.wb.mapper;

import com.wb.entity.Log;
import com.wb.mapper.base.BaseMapper;

public interface LogMapper extends BaseMapper<Log> {
    //删除所有
    int delete();
}
