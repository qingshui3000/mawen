package com.zhao.mawen.common.advice;

import com.alibaba.fastjson.JSON;
import com.zhao.mawen.controller.dto.ResultDTO;
import com.zhao.mawen.common.enums.ExceptionErrorCode;
import com.zhao.mawen.controller.dto.MawenException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class MawenExceptionHadnler{

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            ResultDTO resultDTO;
            if (e instanceof MawenException){
                resultDTO = ResultDTO.errorOf((MawenException) e);
            }else {
                resultDTO =  ResultDTO.errorOf(ExceptionErrorCode.SYS_ERROR);
            }
            response.setContentType("application/json");
            response.setStatus(200);
            response.setCharacterEncoding("utf-8");
            try {
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ex) {
            }
            return null;
        }else {
            if (e instanceof MawenException){
                model.addAttribute("message",e.getMessage());
            }else {
                model.addAttribute("message",ExceptionErrorCode.QUESTION_NOT_FOUND.getMessage());
            }
            return new ModelAndView("error");
        }
    }

}
