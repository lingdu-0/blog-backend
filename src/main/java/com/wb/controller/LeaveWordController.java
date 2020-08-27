package com.wb.controller;

import com.github.pagehelper.PageInfo;
import com.wb.common.Constant;
import com.wb.converter.Converter;
import com.wb.entity.LeaveWord;
import com.wb.entity.Result;
import com.wb.service.LeaveWordService;
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
@RequestMapping("leaveWord")
public class LeaveWordController {

    @Autowired
    private LeaveWordService leaveWordService;

    /**
     * 获取数据并跳转留言首页
     *
     * @param pageNum
     * @return
     */
    @RequestMapping("index/{pageNum}")
    public String index(@PathVariable("pageNum") Integer pageNum, Model model) {
        if (pageNum == null)
            pageNum = 1;
        PageInfo pageInfo = new PageInfo(leaveWordService.findAll(pageNum, Constant.LEAVE_WORD_INDEX_PAGE_SIZE));
        model.addAttribute("leaveWordPageInfo", pageInfo);
        return "notice";
    }

    @RequestMapping("addLeaveWord")
    @ResponseBody
    public LeaveWord addLeaveWord(String leaveWordDetail) {
        LeaveWord leaveWord = new LeaveWord();
        leaveWord.setLeaveWordDetail(leaveWordDetail);
        if (leaveWordService.insert(leaveWord) != 0) {
            return leaveWordService.findById(leaveWord.getLeaveWordId());
        } else {
            return leaveWord;
        }
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public PageInfo selectAll() {
        PageInfo pageInfo = new PageInfo(leaveWordService.findAll(1, Constant.LEAVE_WORD_INDEX_PAGE_SIZE));
        return pageInfo;
    }

    @RequestMapping("del/{leaveWordId}")
    public String del(@PathVariable Integer leaveWordId) {
        leaveWordService.deleteById(leaveWordId);
        return "redirect:/leaveWord/index/1";
    }

    @RequestMapping("goAdd")
    public String goAdd(){
        return "add-notice";
    }

    @RequestMapping("edit/{leaveWordId}")
    public String edit(@PathVariable Integer leaveWordId,Model model){
        model.addAttribute("leaveWord",leaveWordService.findById(leaveWordId));
        return "update-leaveWord";
    }
    @RequestMapping("update")
    public String update(ReqVo reqVo){
        LeaveWord leaveWord = Converter.getLeaveWord(reqVo);
        leaveWordService.update(leaveWord);
        return "redirect:/leaveWord/index/1";
    }
    @RequestMapping("search")
    public String search(String data, Model model) {
        PageInfo pageInfo = new PageInfo(leaveWordService.fuzzyQuery(data));
        model.addAttribute("leaveWordPageInfo", pageInfo);
        return "notice";
    }

    @RequestMapping("deleteIds")
    @ResponseBody
    public Result deleteIds(int[] ids) {
        if (ids == null || ids.length == 0) {
            return Result.failure(PARAM_IS_BLANK);
        }
        int num = leaveWordService.deleteByIds(ids);
        if (num <= 0) {
            return Result.failure(DATA_DELETE_FAILURE);
        }
        return Result.success();
    }
}
