package com.me.template.base;

/**
 * 公共返回信息枚举
 * @author jia
 * @since 2020/1/11 16:46
 */
public enum CommonCode implements ICommonCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(400, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限")
    ;

    private Integer code;
    private String message;
    CommonCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public Integer getCode() {
        return this.code;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
