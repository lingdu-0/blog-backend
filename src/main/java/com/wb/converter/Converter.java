package com.wb.converter;

import com.wb.entity.*;
import com.wb.vo.ReqVo;
import com.wb.vo.UserVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Converter {
    public static Article getArticle(ReqVo reqVo) {
        Article article = new Article();
        article.setArticleId(reqVo.getId());
        article.setArticleTitle(reqVo.getTitle());
        article.setArticleDetail(reqVo.getContent());
        article.setArticleDescribe(reqVo.getDescribe());
        article.setArticleState(reqVo.getState());
        //article.setArticleImg();
        article.setArticleTypeId(reqVo.getCategory());
        return article;
    }

    public static Comment getComment(ReqVo reqVo) {
        Comment comment = new Comment();
        comment.setCommentId(reqVo.getId());
        comment.setCommentDetail(reqVo.getContent());
        return comment;
    }

    public static LeaveWord getLeaveWord(ReqVo reqVo) {
        LeaveWord leaveWord = new LeaveWord();
        leaveWord.setLeaveWordId(reqVo.getId());
        //leaveWord.setLeaveWordName(reqVo.getName());
        leaveWord.setLeaveWordDetail(reqVo.getContent());
        return leaveWord;
    }

    public static ArticleType getArticleType(ReqVo reqVo) {
        ArticleType articleType = new ArticleType();
        articleType.setArticleTypeId(reqVo.getId());
        articleType.setArticleTypeName(reqVo.getName());
        return articleType;
    }

    public static Essay getEssay(ReqVo reqVo) {
        Essay essay = new Essay();
        essay.setEssayId(reqVo.getId());
        essay.setEssayDetail(reqVo.getContent());
        essay.setEssayTitle(reqVo.getTitle());
        essay.setEssayState(reqVo.getState());
        return essay;
    }

    public static Album getAlbum(ReqVo reqVo) {
        Album album = new Album();
        if (reqVo.getId() != null) {
            album.setAlbumId(reqVo.getId());
        }
        album.setAlbumTitle(reqVo.getTitle());
        album.setAlbumAddress(reqVo.getAddress());
        return album;
    }

    /**
     * userVo转user
     *
     * @param userVo
     * @return
     */
    public User getUser(UserVo userVo) {
        User user = new User();
        user.setUserId(StringUtils.isEmpty(userVo.getUserId()) ? null : Integer.valueOf(userVo.getUserId()));
        user.setUserName(userVo.getUserName());
        user.setPassword(userVo.getPassword());
        user.setUserEmail(userVo.getUserEmail());
        user.setUserState(StringUtils.isEmpty(userVo.getUserState()) ? null : Integer.valueOf(userVo.getUserState()));
        user.setUserLevel(StringUtils.isEmpty(userVo.getUserLevel()) ? null : Integer.valueOf(userVo.getUserLevel()));
        return user;
    }
}
