package com.sunyj.template.base.beans;

import lombok.Data;

/**
 * 封装公共返回类
 * @author jia
 * @since 2020/1/11 16:46
 */
@Data
public class CommonResult {
    private Integer code;
    private String message;
    private Object result;

    private CommonResult(Integer code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    /**
     * 封装静态方法
     */
    public static CommonResult build(Integer code, String message, Object result) {
        return new CommonResult(code, message, result);
    }
    public static CommonResult ok(Object result) {
        return build(CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getMessage(), result);
    }
    public static CommonResult ok() {
        return ok(null);
    }
    public static CommonResult failed(String message) {
        return build(CommonCode.FAILED.getCode(), message, null);
    }
    public static CommonResult failed() {
        return build(CommonCode.FAILED.getCode(), CommonCode.FAILED.getMessage(), null);
    }
    public static CommonResult validateFailed() {
        return build(CommonCode.VALIDATE_FAILED.getCode(), CommonCode.VALIDATE_FAILED.getMessage(), null);
    }
    public static CommonResult unauthorized() {
        return build(CommonCode.UNAUTHORIZED.getCode(), CommonCode.UNAUTHORIZED.getMessage(), null);
    }
    public static CommonResult forbidden() {
        return build(CommonCode.FORBIDDEN.getCode(), CommonCode.FORBIDDEN.getMessage(), null);
    }

}
