package com.eyas.parent.exception;


import com.eyas.parent.enumeration.ErrCodeEnum;

/**
 * @author Created by yixuan on 2018/12/27.
 */
public class ParentRuntimeException extends EyasRuntimeException {

    private ErrCodeEnum errCodeEnum;

    public ParentRuntimeException(ErrCodeEnum errCode) {
        super(errCode.getErrCode(), errCode.getErrMsg());
        this.errCodeEnum = errCode;
    }

    public ParentRuntimeException(ErrCodeEnum errCode, String msg) {
        super(errCode.getErrCode(), msg + errCode.getErrMsg());
        this.errCodeEnum = errCode;
    }

    public ParentRuntimeException(ErrCodeEnum errCode, Throwable e) {
        super(errCode.getErrMsg(), errCode.getErrCode(), e);
        this.errCodeEnum = errCode;
    }

    public ParentRuntimeException(ErrCodeEnum errCode, String msg, Throwable e) {
        super(msg, errCode.getErrCode(), e);
        this.errCodeEnum = errCode;
    }

    public ErrCodeEnum getErrCodeEnum() {
        return errCodeEnum;
    }
}
