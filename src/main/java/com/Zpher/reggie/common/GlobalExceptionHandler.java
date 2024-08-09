package com.Zpher.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.Zpher.reggie.common
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/8 15:07
 * @Version 1.0
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {

        log.error(ex.getMessage());
        if (ex.getMessage().contains("Duplicate entry")) {
            String[] strs = ex.getMessage().split(" ");
            String msg = strs[2] + "已存在";
            return R.error(msg);
        }

        return R.error("未知存在");
    }
}
