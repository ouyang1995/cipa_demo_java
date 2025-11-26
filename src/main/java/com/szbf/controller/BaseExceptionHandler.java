package com.szbf.controller;

        import com.szbf.common.ServiceException;
        import com.szbf.util.Result;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){
        log.error("系统异常：{}",e);
        return Result.failed();
    }

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Result serviceException(Exception e){
        log.error("系统异常：{}",e);
        ServiceException serviceException = (ServiceException) e;
        return Result.failed(e.getMessage());
    }

}
