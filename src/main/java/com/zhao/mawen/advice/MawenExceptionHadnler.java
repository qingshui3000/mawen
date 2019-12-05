package com.zhao.mawen.advice;

import com.zhao.mawen.exception.MawenException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class MawenExceptionHadnler{

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model) {
        if (e instanceof MawenException){
            model.addAttribute("message",e.getMessage());
        }else {
            model.addAttribute("message","error");
        }
        return new ModelAndView("error");
    }

}
