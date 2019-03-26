package com.eyas.parent.dto;

import com.eyas.parent.exception.EyasRuntimeException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author Created by yixuan on 2018/12/27.
 */
@Slf4j
@Data
public class EyasResult<T> implements Serializable {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    public static EyasResult ok(){
        return ok(null);
    }

    public static <T> EyasResult ok(T data){
        EyasResult<T> result = new EyasResult<>();
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static EyasResult fail(String errCode, String errMsg){
        EyasResult result = new EyasResult();
        result.setSuccess(false);
        result.setErrCode(errCode);
        result.setErrMsg(errMsg);
        return result;
    }

    public static EyasResult fail(EyasRuntimeException e){
        return fail(e.getCode(), e.getMsg());
    }


}
