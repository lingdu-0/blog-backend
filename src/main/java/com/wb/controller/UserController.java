package com.wb.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wb.converter.Converter;
import com.wb.entity.Log;
import com.wb.entity.Result;
import com.wb.entity.User;
import com.wb.service.LogService;
import com.wb.service.UserService;
import com.wb.util.MD5Utils;
import com.wb.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.wb.entity.ResultCode.*;

@Controller
@RequestMapping("user")
//@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    private static final String LOCAL_IP = "127.0.0.1";
    @Autowired
    private Converter converter;

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("doLogin")
    public String doLogin(String username, String password, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.login(username, MD5Utils.code(password));
        if (user != null) {
            if (user.getUserLevel() != 9) {
                redirectAttributes.addFlashAttribute("message", "用户权限不够");
                return "redirect:/user/login";
            }
            user.setPassword(null);
            session.setAttribute("user", user);
            Log log = new Log();
            log.setUser(user);
            ServletContext application = request.getServletContext();
            application.setAttribute("ip", getIp(request));
            log.setLogIp(getIp(request));
            log.setOperation("登录");
            logService.insert(log);
            return "redirect:/common/index";
        } else {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误!");
            return "redirect:/user/login";
        }
    }

    @RequestMapping("loginOut")
    public String loginOut(HttpSession session, HttpServletRequest request) {
        logService.insertOne(request.getServletContext(),session,"登出");
        session.removeAttribute("user");
        return "redirect:/user/login";
    }

    @RequestMapping("index/{pageNum}")
    public String index(@PathVariable Integer pageNum, Model model) {
        if (pageNum == null)
            pageNum = 1;
        PageInfo pageInfo = new PageInfo(userService.findAll(pageNum, 10));
        model.addAttribute("userPageInfo", pageInfo);
        return "user";
    }

    @RequestMapping("goAdd")
    public String goAdd() {
        return "add-user";
    }

    @RequestMapping("add")
    public String add(User user) {
        if (user != null) {
            String password = user.getPassword();
            user.setPassword(MD5Utils.code(password));
            userService.insert(user);
        }
        return "redirect:/user/index/1";
    }

    @RequestMapping("edit/{userId}")
    public String edit(@PathVariable Integer userId, Model model) {
        model.addAttribute("user", userService.findById(userId));
        return "update-user";
    }

    @RequestMapping("update")
    @ResponseBody
    public Result update(@Validated UserVo userVo, BindingResult bindingResult) {
        System.out.println(userVo);
        JSONObject map = new JSONObject();
        //校验参数
        if (bindingResult.hasErrors()) {
            return Result.getResult(bindingResult, map);
        }
        User user = converter.getUser(userVo);
        String password = user.getPassword();
        user.setPassword(MD5Utils.code(password));
        userService.update(user);
//        if (user != null) {
//            String password = user.getPassword();
//            user.setPassword(MD5Utils.code(password));
//            userService.update(user);
//        }
        return Result.success();
    }

    @RequestMapping("del/{userId}")
    public String del(@PathVariable Integer userId) {
        userService.deleteById(userId);
        return "redirect:/user/index/1";
    }

    @RequestMapping("search")
    public String search(String data, Model model) {
        PageInfo pageInfo = new PageInfo(userService.fuzzyQuery(data));
        model.addAttribute("userPageInfo", pageInfo);
        return "user";
    }

    private String getIp(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? LOCAL_IP : ip;
    }

    @RequestMapping("deleteIds")
    @ResponseBody
    public Result deleteIds(int[] ids) {
        if (ids == null || ids.length == 0) {
            return Result.failure(PARAM_IS_BLANK);
        }
        int num = userService.deleteByIds(ids);
        if (num <= 0) {
            return Result.failure(DATA_DELETE_FAILURE);
        }
        return Result.success();
    }

    @PostMapping("findByUserEmail")
    @ResponseBody
    public Result findByUserEmail(String userEmail) {
        if (StringUtils.isEmpty(userEmail)) {
            return Result.failure(PARAM_IS_BLANK);
        }
        if (userService.findByUserEmail(userEmail) > 0) {
            return Result.failure(MAILBOX_ALREADY_EXISTS);
        }
        return Result.success();
    }

    @PostMapping("findByUserName")
    @ResponseBody
    public Result findByUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return Result.failure(PARAM_IS_BLANK);
        }
        if (userService.findByUserName(userName) > 0) {
            return Result.failure(USER_NAME_ALREADY_EXISTS);
        }
        return Result.success();
    }
}
