package com.wb.service;

import com.wb.entity.Album;
import com.wb.service.base.BaseService;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlbumService extends BaseService<Album> {
    /**
     * 查询最后一条记录
     *
     * @return
     */
    Album selectLast();
}
