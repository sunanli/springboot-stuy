package com.tangwh.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 自定义异常处理
 */
@ControllerAdvice //Controller注解的增强
public class MyException {

    /**
     * 文件大小限制的一个异常才会走这里 返回的文字
     * @param e
     */
//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public void myCustomException(MaxUploadSizeExceededException e, HttpServletResponse response) throws IOException {
//
//        response.setContentType("text/html;charset=utf-8");
//
//        PrintWriter out =response.getWriter();
//        out.write("文件大小超出限制");
//        out.flush();
//        out.close();
//
//    }

    /**
     * 文件大小限制的一个异常才会走这里   返回的页面
     *
     * @param e
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView myCustomException(MaxUploadSizeExceededException e) throws IOException {


        ModelAndView mv = new ModelAndView("myerror");
        mv.addObject("error", "上传文件大小超出限制");

        return mv;

    }
}
