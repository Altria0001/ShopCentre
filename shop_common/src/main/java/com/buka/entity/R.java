package com.buka.entity;

import lombok.Data;

/**
 * 返回结果实体类
 */
@Data
public class R<T> {
    private boolean flag;//是否成功
    private Integer code;//返回码
    private String message;//返回消息

    private T data;//返回数据

    public R(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T)data;
    }
    public R(String message) {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = message;
    }
    public R(String message,T data) {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = message;
        this.data = data;
    }
    public R(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public R() {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "执行成功";
    }



    public static <T> R<T> ok() {
        return new R<>(true, StatusCode.OK, "操作成功");
    }

    public static <T> R<T> ok(T data) {
        return new R<>(true, StatusCode.OK, "操作成功",data);
    }

    public static <T> R<T> ok(String msg) {
        return new R<>(true, StatusCode.OK, msg);
    }

    public static <T> R<T> ok(String msg, T data) {
        return new R<>(true, StatusCode.OK, msg,data);
    }

    public static <T> R<T> fail() {
        return new R<>(false, StatusCode.ERROR,"操作失败");
    }

    public static <T> R<T> fail(String msg) {
        return new R<>(false, StatusCode.ERROR,msg);
    }

    public static <T>  R<T> fail(T data) {
        return new R<>(false, StatusCode.ERROR,"操作失败",data);
    }

    public static <T> R<T> fail(String msg, T data) {
        return new R<>(false, StatusCode.ERROR,msg,data);
    }

    public static <T> R<T> fail(int code, String msg) {
        return new R<>(false, code,msg,msg);
    }

    public static <T> R<T> trueOrFalse(boolean b) {
        if (b){
            return ok();
        }else {
            return fail();
        }
    }

}
