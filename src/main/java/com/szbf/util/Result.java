package com.szbf.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName Result.java
 * @Description TODO
 * @createTime 2022年05月11日 10:07:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>{


    private String code;
    private String message;
    @JsonIgnore
    private Object[] messageParams;
    private T data;


    public static <T> Result<T> success() {
        ResultBuilder<T> builder = new ResultBuilder<T>();
        return builder
                .code(CommonEnum.SUCCESS.getCode())
                .message(CommonEnum.SUCCESS.getMessage())
                .build();
    }

    public static <T> Result<T> success(T data) {
        ResultBuilder<T> builder = new ResultBuilder<T>();
        return builder
                .data(data)
                .code(CommonEnum.SUCCESS.getCode())
                .message(CommonEnum.SUCCESS.getMessage())
                .build();
    }

    public static <T> Result<T> success(String message, T data) {
        ResultBuilder<T> builder = new ResultBuilder<T>();
        return builder
                .data(data)
                .message(message)
                .code(CommonEnum.SUCCESS.getCode())
                .build();
    }



    public static <T> Result<T> failed() {
        ResultBuilder<T> builder = new ResultBuilder<T>();
        return builder
                .code(CommonEnum.FAILED.getCode())
                .message(CommonEnum.FAILED.getMessage())
                .build();
    }

    public static <T> Result<T> failed(String message) {
        ResultBuilder<T> builder = new ResultBuilder<T>();
        return builder
                .code(CommonEnum.FAILED.getCode())
                .message(message)
                .build();
    }


    public static <T> Result<T> failed(CommonEnum code) {
        ResultBuilder<T> builder = new ResultBuilder<T>();
        return builder
                .code(code.getCode())
                .message(code.getMessage())
                .build();
    }

    public static <T> Result<T> build(Result<T> result) {
        ResultBuilder<T> builder = new ResultBuilder<T>();
        return builder
                .code(result.getCode())
                .message(result.getMessage())
                .data(result.getData())
                .build();
    }


    public static <T> Result<T> isSuccess(boolean flag) {
        ResultBuilder<T> builder = new ResultBuilder<T>();
        if (flag) {
            return builder
                    .code(CommonEnum.SUCCESS.getCode())
                    .message(CommonEnum.SUCCESS.getMessage())
                    .build();
        } else {
            return builder
                    .code(CommonEnum.FAILED.getCode())
                    .message(CommonEnum.FAILED.getMessage())
                    .build();
        }

    }

    public boolean isSuccess() {
        return Objects.equals(this.code, CommonEnum.SUCCESS.getCode());
    }
}
