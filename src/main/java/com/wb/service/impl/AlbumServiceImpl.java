package com.wb.service.impl;

import com.github.pagehelper.PageHelper;
import com.wb.entity.Album;
import com.wb.service.AlbumService;
import com.wb.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumServiceImpl extends BaseServiceImpl<Album> implements AlbumService {

    @Override
    public Album findById(Integer id) {
        return albumMapper.findById(id);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int deleteById(Integer id) {
        logService.insertOne(servletContext, session, "通过ID删除图片");
        return albumMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteByIds(int[] ids) {
        logService.insertOne(servletContext, session, "通过ID批量删除图片");
        return articleMapper.deleteByIds(ids);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int insert(Album album) {
        logService.insertOne(servletContext, session, "新增图片");
        return albumMapper.insert(album);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int update(Album album) {
        logService.insertOne(servletContext, session, "修改图片");
        return albumMapper.update(album);
    }

    @Override
    public List<Album> findAll(Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, pageSize);
        return albumMapper.selectAll();
    }

    @Override
    public List<Album> fuzzyQuery(String data) {
        PageHelper.startPage(1, 10);
        return albumMapper.fuzzyQuery(data);
    }

    @Override
    public Album selectLast() {
        return albumMapper.selectLast();
    }
}
