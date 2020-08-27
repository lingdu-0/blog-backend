package com.wb.controller;

import com.wb.converter.Converter;
import com.wb.entity.ArticleType;
import com.wb.entity.Result;
import com.wb.service.ArticleTypeService;
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
@RequestMapping("articleType")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping("articleTypes")
    public String articleTypes(Model model) {
        model.addAttribute("articleTypes", articleTypeService.findAll());
        return "category";
    }

    @RequestMapping("add")
    public String add(ArticleType articleType) {
        System.out.println(articleType);
        articleTypeService.insert(articleType);
        return "redirect:/articleType/articleTypes";
    }

    @RequestMapping("del/{articleTypeId}")
    public String del(@PathVariable Integer articleTypeId) {
        articleTypeService.deleteById(articleTypeId);
        return "redirect:/articleType/articleTypes";
    }

    @RequestMapping("edit/{articleTypeId}")
    public String edit(@PathVariable Integer articleTypeId, Model model) {
        model.addAttribute("articleType", articleTypeService.findById(articleTypeId));
        return "update-category";
    }

    @RequestMapping("update")
    public String update(ReqVo reqVo) {
        ArticleType articleType = Converter.getArticleType(reqVo);
        articleTypeService.update(articleType);
        return "redirect:/articleType/articleTypes";
    }

    @RequestMapping("search")
    public String search(String data, Model model) {
//        PageInfo pageInfo = new PageInfo(articleTypeService.fuzzyQuery(data));
        model.addAttribute("articleTypes", articleTypeService.fuzzyQuery(data));
        return "category";
    }
    @RequestMapping("deleteIds")
    @ResponseBody
    public Result deleteIds(int[] ids) {
        if (ids == null || ids.length == 0) {
            return Result.failure(PARAM_IS_BLANK);
        }
        int num = articleTypeService.deleteByIds(ids);
        if (num <= 0) {
            return Result.failure(DATA_DELETE_FAILURE);
        }
        return Result.success();
    }
}
