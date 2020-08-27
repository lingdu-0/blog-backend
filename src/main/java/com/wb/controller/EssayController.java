package com.wb.controller;

import com.github.pagehelper.PageInfo;
import com.wb.common.Constant;
import com.wb.converter.Converter;
import com.wb.entity.Essay;
import com.wb.entity.Result;
import com.wb.service.CommentService;
import com.wb.service.EssayService;
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
@RequestMapping("essay")
public class EssayController {

    @Autowired
    private EssayService essayService;
    @Autowired
    private CommentService commentService;

    /**
     * 获取数据并跳转随笔首页
     *
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("essays/{pageNum}")
    public String index(@PathVariable Integer pageNum, Model model) {
        if (pageNum == null)
            pageNum = 1;
        PageInfo pageInfo = new PageInfo(essayService.findAll(pageNum, Constant.ESSAY_PAGE_SIZE));
        model.addAttribute("essayPageInfo", pageInfo);
        return "essay";
    }

    @RequestMapping("goAdd")
    public String goAdd() {
        return "add-essay";
    }

    @RequestMapping("add")
    public String add(ReqVo reqVo) {
        Essay essay = Converter.getEssay(reqVo);
        essayService.insert(essay);
        return "redirect:/essay/essays/1";
    }

    @RequestMapping("edit/{essayId}")
    public String edit(@PathVariable Integer essayId, Model model) {
        model.addAttribute("essay", essayService.findById(essayId));
        return "update-essay";
    }

    @RequestMapping("update")
    public String update(ReqVo reqVo) {
        Essay essay = Converter.getEssay(reqVo);
        essayService.update(essay);
        return "redirect:/essay/essays/1";
    }

    @RequestMapping("del/{essayId}")
    public String del(@PathVariable Integer essayId) {
        essayService.deleteById(essayId);
        return "redirect:/essay/essays/1";
    }
    @RequestMapping("search")
    public String search(String data, Model model) {
        PageInfo pageInfo = new PageInfo(essayService.fuzzyQuery(data));
        model.addAttribute("essayPageInfo", pageInfo);
        return "essay";
    }
    @RequestMapping("deleteIds")
    @ResponseBody
    public Result deleteIds(int[] ids) {
        if (ids == null || ids.length == 0) {
            return Result.failure(PARAM_IS_BLANK);
        }
        int num = essayService.deleteByIds(ids);
        if (num <= 0) {
            return Result.failure(DATA_DELETE_FAILURE);
        }
        return Result.success();
    }
}
