package com.zhao.mawen.dto;

import com.zhao.mawen.exception.ExceptionErrorCode;
import com.zhao.mawen.exception.IExceptionErrorCode;
import com.zhao.mawen.exception.MawenException;
import lombok.Data;

@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(MawenException code){
        return errorOf(code.getCode(),code.getMessage());
    }

    public static ResultDTO errorOf(IExceptionErrorCode code){
        return errorOf(code.getCode(),code.getMessage());
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return  resultDTO;
    }
}
