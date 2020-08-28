package com.wb.controller;

import com.github.pagehelper.PageInfo;
import com.wb.common.Constant;
import com.wb.converter.Converter;
import com.wb.entity.Comment;
import com.wb.entity.Result;
import com.wb.service.CommentService;
import com.wb.vo.ReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.wb.entity.ResultCode.DATA_DELETE_FAILURE;
import static com.wb.entity.ResultCode.PARAM_IS_BLANK;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("comments/{pageNum}")
    public String comments(@PathVariable Integer pageNum, Model model) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo pageInfo = new PageInfo(commentService.findAll(pageNum, Constant.COMMENT_PAGE_SIZE));
        model.addAttribute("commentPageInfo", pageInfo);
        return "comment";
    }

    @RequestMapping("addComment")
    @ResponseBody
    public Comment addComment(Integer type, Integer id, String detail) {
        Comment comment = new Comment();
        switch (type) {
            case 1:
                comment.setCommentType(1);
                comment.setArticleId(id);
                break;
            case 2:
                comment.setCommentType(2);
                comment.setEssayId(id);
                break;
            default:
                break;
        }
        comment.setCommentDetail(detail);
        if (commentService.insert(comment) != 0) {
            return commentService.findById(comment.getCommentId());
        } else {
            return null;
        }
    }


    @RequestMapping("/del/{commentId}")
    public String del(@PathVariable Integer commentId) {
        System.out.println("=======" + commentId);
        commentService.deleteById(commentId);
        return "redirect:/comment/comments/1";
    }

    @RequestMapping("edit/{commentId}")
    public String edit(@PathVariable Integer commentId, Model model) {
        model.addAttribute("comment", commentService.findById(commentId));
        return "update-comment";
    }

    @RequestMapping("update")
    public String update(ReqVo reqVo) {
        Comment comment = Converter.getComment(reqVo);
        commentService.update(comment);
        return "redirect:/comment/comments/1";
    }

    @RequestMapping("search")
    public String search(String data, Model model) {
        PageInfo pageInfo = new PageInfo(commentService.fuzzyQuery(data));
        model.addAttribute("commentPageInfo", pageInfo);
        return "comment";
    }

    @RequestMapping("deleteIds")
    @ResponseBody
    public Result deleteIds(int[] ids) {
        if (ids == null || ids.length == 0) {
            return Result.failure(PARAM_IS_BLANK);
        }
        int num = commentService.deleteByIds(ids);
        if (num <= 0) {
            return Result.failure(DATA_DELETE_FAILURE);
        }
        return Result.success();
    }
}
