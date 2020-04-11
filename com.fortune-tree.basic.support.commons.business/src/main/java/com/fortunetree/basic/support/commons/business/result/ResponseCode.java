package com.fortunetree.basic.support.commons.business.result;

/**
 * @author ZhouJumbo
 */

public enum ResponseCode {

    SUCCESS(0, "OK"),
    ERROR(1000, "Unknown Error"),
    DUPLICATE_REQUEST(10003, ""),
    PARAM_NULL(2000, "参数空指针异常，null"),
    PARAM_INVALID(2001, "参数验证失败,不符合要求"),
    PARAM_INJECTION_RISK(2002, "参数验证失败,参数不符合防注入要求"),
    FAILURE_REQUEST(4000, "请求失败，请求类型、参数、头信息可能存在问题，请检查后重试"),
    UNAUTHORIZED(4001, "请求要求用户的身份认证"),
    NO_PERMISSION(4003, "请求无权限访问该资源"),
    SERVER_ERROR(5000, "服务器内部错误"),
    SERVER_RESULT_NULL(5001, "返回结果不存在或为空值"),
    SERVER_RESULT_ERROR(5002, "返回结果解析异常"),
    SERVER_JSON_ERROR(5003, "服务JSON数据解析异常"),
    SERVER_IO_ERROR(5004, "服务IO异常"),
    SERVER_NET_ERROR(5005, "服务NET异常");


    private final int value;

    private final String reasonPhrase;

    ResponseCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String reasonPhrase(){
        return this.reasonPhrase;
    }

    public static boolean success(int value) {
        return SUCCESS.value() == value;
    }
}
