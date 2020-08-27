package com.wb.controller;

import com.github.pagehelper.PageInfo;
import com.wb.common.Constant;
import com.wb.converter.Converter;
import com.wb.entity.Article;
import com.wb.entity.FTPConfig;
import com.wb.entity.Result;
import com.wb.service.ArticleService;
import com.wb.service.ArticleTypeService;
import com.wb.util.FileUpload;
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
@RequestMapping("article")
public class ArticleController {

    private static final String DEFAULT_IMG = "http://www.wbblog.top:9527/myblog/img/timg.jpg";

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private FTPConfig ftpConfig;

    @RequestMapping("articles/{pageNum}")
    public String articles(@PathVariable Integer pageNum, Model model) {
        if (pageNum == null)
            pageNum = 1;
        PageInfo pageInfo = new PageInfo(articleService.findAll(pageNum, Constant.ARTICLE_PAGE_SIZE));
        pageInfo.getNavigateLastPage();
        model.addAttribute("articlePageInfo", pageInfo);
        return "article";
    }

    @RequestMapping("del/{articleId}")
    public String del(@PathVariable Integer articleId) {
        articleService.deleteById(articleId);
        return "redirect:/article/articles/1";
    }

    @RequestMapping("goAdd")
    public String goAdd(Model model) {
        model.addAttribute("articleTypes", articleTypeService.findAll());
        return "add-article";
    }

    @RequestMapping("edit/{articleId}")
    public String edit(@PathVariable Integer articleId, Model model) {
        model.addAttribute("article", articleService.findById(articleId));
        model.addAttribute("articleTypes", articleTypeService.findAll());
        return "update-article";
    }

    @RequestMapping("add")
    public String add(ReqVo reqVo) {
        Article article = Converter.getArticle(reqVo);
        article.setArticleImg(DEFAULT_IMG);
        if (!reqVo.getFile().isEmpty()) {
            String fileName = FileUpload.upload(ftpConfig, reqVo);
            System.out.println(fileName);
            if (!fileName.equals("")) {
                article.setArticleImg(fileName);
            }
        }
        articleService.insert(article);
        return "redirect:/article/articles/1";
    }

    @RequestMapping("update")
    public String update(ReqVo reqVo) {
        Article article = Converter.getArticle(reqVo);
        articleService.update(article);
        return "redirect:/article/articles/1";
    }


    @RequestMapping("search")
    public String search(String data, Model model) {
        PageInfo pageInfo = new PageInfo(articleService.fuzzyQuery(data));
        model.addAttribute("articlePageInfo", pageInfo);
        return "article";
    }

    @RequestMapping("deleteIds")
    @ResponseBody
    public Result deleteIds(int[] ids) {
        if (ids == null || ids.length == 0) {
            return Result.failure(PARAM_IS_BLANK);
        }
        int num = articleService.deleteByIds(ids);
        if (num <= 0) {
            return Result.failure(DATA_DELETE_FAILURE);
        }
        return Result.success();
    }

}
