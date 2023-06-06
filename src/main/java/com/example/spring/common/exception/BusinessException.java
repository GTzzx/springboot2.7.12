package com.example.spring.common.exception;

/**
 * 自定义业务异常类
 */
public class BusinessException extends RuntimeException{
    private final int code;
    private final  String message;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
