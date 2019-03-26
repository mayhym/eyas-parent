package com.eyas.parent.util.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Created by yixuan on 2018/12/27.
 */
@Slf4j
public class ErrorCodeUtil {

    public static String getErrorCode(String app, String type, String source, String errorCode) {
        return app + "#" + type + "#" + source + "#" + errorCode;
    }

    public static String getErrorCode(String type, String source, String errorCode) {
        String projectName = System.getProperty("project.name");
        if (null == projectName || "".equals(projectName)) {
            projectName = "UNKOWN";
        }
        return getErrorCode(projectName, type, source, errorCode);
    }
}
