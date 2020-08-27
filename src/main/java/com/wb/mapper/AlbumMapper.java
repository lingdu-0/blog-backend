package com.wb.mapper;

import com.wb.entity.Album;
import com.wb.mapper.base.BaseMapper;

public interface AlbumMapper extends BaseMapper<Album> {
    /**
     * 查询最后一条数据
     *
     * @return
     */
    Album selectLast();
}