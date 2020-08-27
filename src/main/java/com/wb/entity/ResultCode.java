package com.wb.entity;

/**
 * @ClassName ResultCode
 * @Deacription TODO
 * @Author W
 * @Date 2019/11/23
 * @Version 1.0
 */
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(0, "成功"),
    /* 失败状态码 */
    FAILURE(1, "失败"),
    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),
    PARAM_FORMAT_ERROR(10005, "参数格式错误"),
    PARAM_CHECK_ERROR(10006, "参数校验错误"),
    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    USER_NAME_ALREADY_EXISTS(20006, "用户名已经被使用！"),
    MAILBOX_ALREADY_EXISTS(20007, "邮箱已经被使用！"),
    USER_REGISTRATION_FAILED(20008,"用户注册失败！"),
    /* 业务错误：30001-39999 */
    VERIFICATION_CODE_ERROR(30001, "验证码错误！"),
    VERIFICATION_CODE_NOT_EXIST(30002, "验证码不存在！"),
    VERIFICATION_CODE_INVALID(30003,"验证码已失效！"),

    /* 系统错误：40001-49999 */

    /* 数据错误：50001-599999 */
    DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),
    DATA_INSERT_FAILURE(50004, "数据添加失败"),
    DATA_UPDATE_FAILURE(50005, "数据修改失败"),
    DATA_DELETE_FAILURE(50006, "数据删除失败"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static Integer getCode(String name) {
        return getItem(name).code;
    }


    public static String getMessage(String name) {
        return getItem(name).message;
    }

    public static ResultCode getItem(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }
}

