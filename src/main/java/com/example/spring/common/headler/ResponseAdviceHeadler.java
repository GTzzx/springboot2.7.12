package com.example.spring.common.headler;

import com.example.spring.common.utils.ResultData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

@RestControllerAdvice
@Slf4j
public class ResponseAdviceHeadler implements ResponseBodyAdvice<Object> {
    @Resource
    private ObjectMapper objectMapper;
    private  final  String stringConventer="org.springframework.http.converter.StringHttpMessageConverter";


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info(returnType.getDeclaringClass().getName());
        return !returnType.getDeclaringClass().getName().contains("springfox");
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof String || o==null){
            HttpHeaders headers = serverHttpResponse.getHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return objectMapper.writeValueAsString(ResultData.success(o));
        }
//        if (stringConventer.equalsIgnoreCase(aClass.getName())){
//            HttpHeaders headers = serverHttpResponse.getHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            return objectMapper.writeValueAsString(ResultData.success(o));
//        }

        if(o instanceof ResultData){
            return o;
        }
        return ResultData.success(o);
    }
}
