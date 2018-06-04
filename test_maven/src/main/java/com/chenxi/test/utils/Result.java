package com.chenxi.test.utils;

import java.util.List;

public class Result {

    private String resultCode;
    private String unmatched;
    private String operator;
    private String message;
    private String code;
    private String result;

    public String getUnmatched() {
        return unmatched;
    }

    public void setUnmatched(String unmatched) {
        this.unmatched = unmatched;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
