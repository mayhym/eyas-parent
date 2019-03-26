package com.eyas.parent.enumeration;


import com.eyas.parent.util.base.ErrorCodeUtil;

public enum ErrCodeEnum {
    /**
     * 参数为空
     */
    NULL_PARAM_ERROR(ErrorCodeUtil.getErrorCode(SystemConstant.DOMAIN, ErrorCodeTypeEnum.PARAMETER.getCode(), ErrorCodeSourceEnum.INTERNAL.getCode(), "100001"), "参数为空"),
    /**
     * 数学数字0
     */
    MATH_ZERO(ErrorCodeUtil.getErrorCode(SystemConstant.DOMAIN, ErrorCodeTypeEnum.BIZ.getCode(), ErrorCodeSourceEnum.INTERNAL.getCode()), "100001")
    ;
    ;


    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误
     */
    private String errMsg;

    ErrCodeEnum(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


    public String getErrCode() {
        return errCode;
    }


    public String getErrMsg() {
        return errMsg;
    }
}
