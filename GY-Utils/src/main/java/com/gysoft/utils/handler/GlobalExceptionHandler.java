package com.gysoft.utils.handler;

import com.gysoft.utils.bean.ResponseResult;
import com.gysoft.utils.exception.*;
import com.gysoft.utils.util.http.bean.CustomHttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult<Object> exceptionHandler(HttpServletRequest req, Exception e) {
        if (e.getMessage().indexOf("提示") != -1) {
            return ResponseResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
        return ResponseResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务内部错误");
    }

    @ExceptionHandler(value = ResultException.class)
    @ResponseBody
    public ResponseResult<Object> resultExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResponseResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoPermissionException.class)
    @ResponseBody
    public ResponseResult<Object> noPermissionExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResponseResult.fail(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    @ExceptionHandler(value = IllegalStateException.class)
    @ResponseBody
    public ResponseResult<Object> illegalStateExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResponseResult.fail(HttpStatus.MULTI_STATUS.value(), e.getMessage());
    }

    @ExceptionHandler(value = ParamInvalidException.class)
    @ResponseBody
    public ResponseResult<Object> paramInvalidExceptionHandler(HttpServletRequest req, ParamInvalidException e) throws Exception {
        return ResponseResult.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    @ResponseBody
    public ResponseResult<Object> dataNotFoundExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResponseResult.fail(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseBody
    public ResponseResult<Object> userNotFoundExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResponseResult.fail(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(value = PasswordErrorException.class)
    @ResponseBody
    public ResponseResult<Object> passwordErrorExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResponseResult.fail(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(), e.getMessage());
    }

    @ExceptionHandler(value = LoginTimeOutException.class)
    @ResponseBody
    public ResponseResult<Object> loginTimeOutExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResponseResult.fail(HttpStatus.REQUEST_TIMEOUT.value(), e.getMessage());
    }

    @ExceptionHandler(value = URLTimeOutException.class)
    @ResponseBody
    public ResponseResult<Object> urlTimeOutExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResponseResult.fail(HttpStatus.REQUEST_TIMEOUT.value(), e.getMessage());
    }

    @ExceptionHandler(value = AlreadyExistException.class)
    @ResponseBody
    public ResponseResult<Object> alreadyExistExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResponseResult.fail(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = FileCovertException.class)
    @ResponseBody
    public ResponseResult<Object> fileCovertExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResponseResult.fail(CustomHttpStatus.FILE_COVERTT.value(), e.getMessage());
    }

}

