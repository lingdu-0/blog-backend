package com.wb.controller;

import com.github.pagehelper.PageInfo;
import com.wb.entity.Result;
import com.wb.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.wb.entity.ResultCode.DATA_DELETE_FAILURE;
import static com.wb.entity.ResultCode.PARAM_IS_BLANK;

@Controller
@RequestMapping("log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("index/{pageNum}")
    public String index(@PathVariable Integer pageNum, Model model) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo pageInfo = new PageInfo(logService.findAll(pageNum, 10));
        model.addAttribute("logPageInfo", pageInfo);
        return "log";
    }

    @RequestMapping("del/{loginLogId}")
    public String del(@PathVariable Integer loginLogId) {
        logService.deleteById(loginLogId);
        return "redirect:/log/index/1";
    }

    @RequestMapping("delAll")
    public String delAll() {
        logService.delete();
        return "redirect:/log/index/1";
    }

    @RequestMapping("search")
    public String search(String data, Model model) {
        PageInfo pageInfo = new PageInfo(logService.fuzzyQuery(data));
        model.addAttribute("logPageInfo", pageInfo);
        return "log";
    }

    @RequestMapping("deleteIds")
    @ResponseBody
    public Result deleteIds(int[] ids) {
        if (ids == null || ids.length == 0) {
            return Result.failure(PARAM_IS_BLANK);
        }
        int num = logService.deleteByIds(ids);
        if (num <= 0) {
            return Result.failure(DATA_DELETE_FAILURE);
        }
        return Result.success();
    }
}
