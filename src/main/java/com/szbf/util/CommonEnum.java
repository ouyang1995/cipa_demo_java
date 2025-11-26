package com.szbf.util;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName CommonEnum.java
 * @Description TODO
 * @createTime 2022年05月11日 10:09:00
 */
public enum CommonEnum {
    SUCCESS("message.common.success", null),
    FAILED("message.common.failed", null),
    UNAUTHORIZED("message.common.unauthorized", null),
    DATA_NONEXISTENCE("message.common.data.nonexistence", null),
    SERVER_ERROR("message.common.server.error", null),
    PARAMETERS_MISSING("message.common.parameters.missing", null),
    TIMEOUT("message.common.timeout", null),
    DATA_ALREADY_EXISTS("message.common.data.already.exists", null),
    LOGIN_EXPIRED("message.common.login.expired", null),
    AUTH_FAILED("message.common.auth.failed", null),
    DELETE_FAILD("message.common.delete.faild", null),
    INSERT_FAILD("message.common.insert.faild", null),
    DATA_DELETED("message.common.data.deleted", null),
    ID_MISSING("message.common.id.missing", null),
    UPGRADE("message.common.upgrade", null),
    FREQUENT_OPERATION("message.common.frequent.operation", null),
    UPLOAD_LIMIT("message.common.upload.limit", null),
    DOWNLOAD_LIMIT("message.common.download.limit", null),
    GATEWAY_ERROR("message.common.gateway.error", null),
    ;
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return msg;
    }
    private String code;
    private String msg;
    CommonEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
