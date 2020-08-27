package com.wb.service.impl;

import com.github.pagehelper.PageHelper;
import com.wb.entity.Article;
import com.wb.entity.Comment;
import com.wb.entity.Essay;
import com.wb.service.CommentService;
import com.wb.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {
    @Override
    public Comment findById(Integer id) {
        return commentMapper.findById(id);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int deleteById(Integer id) {
        logService.insertOne(servletContext, session, "通过ID删除评论");
        return commentMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteByIds(int[] ids) {
        logService.insertOne(servletContext, session, "通过ID批量删除评论");
        return commentMapper.deleteByIds(ids);
    }

    /**
     * 添加评论并及时修改对应文章的评论数
     *
     * @param comment
     * @return
     */
    @Override
    @Transactional
//    @CacheEvict
    public int insert(Comment comment) {
        switch (comment.getCommentType()) {
            case 1:
                Article article = new Article();
                article.setArticleNumber(1);
                article.setArticleId(comment.getArticleId());
                articleMapper.update(article);
                break;
            case 2:
                Essay essay = new Essay();
                essay.setEssayId(comment.getEssayId());
                essay.setEssayNumber(1);
                essayMapper.update(essay);
                break;
        }
        return commentMapper.insert(comment);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int update(Comment comment) {
        logService.insertOne(servletContext, session, "修改评论");
        return commentMapper.update(comment);
    }

    @Override
    public List<Comment> findAll(Integer pageNum, Integer pageSize) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, pageSize);
        return commentMapper.selectAll();
    }

    @Override
    public List<Comment> fuzzyQuery(String data) {
        PageHelper.startPage(1, 10);
        return commentMapper.fuzzyQuery(data);
    }

    @Override
    public List<Comment> findByTypeId(Integer typeId, Integer id, Integer pageSize) {
        PageHelper.startPage(1, pageSize);
        return commentMapper.findByIds(typeId, id);
    }
}
