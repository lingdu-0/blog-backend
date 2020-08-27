package com.wb.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.List;

import static com.wb.entity.ResultCode.*;

/**
 * @ClassName Result
 * @Deacription 封装响应结果
 * @Author W
 * @Date 2019/11/23
 * @Version 1.0
 */

@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 4055308751591794598L;
    private Integer code;
    private String msg;
    private Object data;


    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.msg = resultCode.message();
    }

    /**
     * 请求成功不带返回值
     */
    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 请求成功带返回值
     *
     * @param data
     */
    public static Result success(Object data) {
        Result result = success();
        result.setData(data);
        return result;
    }

    /**
     * 请求失败不带返回值
     *
     * @param resultCode
     * @return
     */
    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 请求失败带返回值
     *
     * @param resultCode
     * @param data
     * @return
     */
    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    /**
     * @return com.wb.wbblog.pojo.entity.Result
     * @Author W
     * @Description 自定义错误编码和信息
     * @Date 2019/12/27
     * @Param [code, msg]
     */
    public static Result failure(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 获取校验参数错误信息
     *
     * @param results
     * @param error
     * @return
     */
    public static Result getResult(BindingResult results, JSONObject error) {
        List<FieldError> fieldErrors = results.getFieldErrors();
        List<ObjectError> objectErrors = results.getAllErrors();
        for (int i = 0; i < objectErrors.size(); i++) {
            error.put(fieldErrors.get(i).getField(), objectErrors.get(i).getDefaultMessage());
        }
        return Result.failure(PARAM_CHECK_ERROR, error);
    }
}

