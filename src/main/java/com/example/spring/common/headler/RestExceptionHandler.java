package com.example.spring.common.headler;


import com.example.spring.common.exception.BusinessException;
import com.example.spring.common.utils.ResultData;
import com.example.spring.dao.entity.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * 默认全局异常处理。
     *
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return ResultData.fail(ReturnCode.RC500.getCode(), e.getMessage());
    }

    /**
     * 自定义异常监控
     */
    @ExceptionHandler(BusinessException.class)
    public ResultData<String> businessException(BusinessException e) {
        log.error("业务异常信息 ex={}", e.getMessage(), e);
        return ResultData.fail(e.getCode(), e.getMessage());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultData<Object> businessException(MethodArgumentNotValidException e) {
        log.error("业务异常信息 ex={}",e.getMessage(), e);
        String message =e.getAllErrors()//获取所有error
                            .stream()//转化为流
                            .map(ObjectError::getDefaultMessage)//格式转化
                            .collect(Collectors.joining(","));//拼接流
        return ResultData.fail(ReturnCode.RC101.getCode(),message);
    }

}