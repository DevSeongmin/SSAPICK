package com.ssapick.server.core.response;

import com.nimbusds.jose.shaded.gson.Gson;
import com.ssapick.server.core.exception.ErrorCode;
import net.minidev.json.annotate.JsonIgnore;

public class ErrorResponse extends BaseResponse<Void> {
    private final static ErrorResponse EMPTY = new ErrorResponse();

    @JsonIgnore
    private Void data;

    private ErrorResponse() {
        this.success = false;
        this.status = ErrorCode.SERVER_ERROR.getCode();
        this.message = ErrorCode.SERVER_ERROR.getMessage();
    }

    private ErrorResponse(ErrorCode errorCode) {
        this.success = false;
        this.status = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    private ErrorResponse(ErrorCode errorCode, String message) {
        this.success = false;
        this.status = errorCode.getCode();
        this.message = message;
    }

    private ErrorResponse(ErrorCode errorCode, Object errors) {
        this.success = false;
        this.status = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.errors = errors;
    }

    public static ErrorResponse empty() {
        return EMPTY;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
