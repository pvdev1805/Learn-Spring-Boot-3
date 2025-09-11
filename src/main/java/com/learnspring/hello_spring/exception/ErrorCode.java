package com.learnspring.hello_spring.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized error"),
    USER_EXISTS(1001, "User existed"),
    USERNAME_INVALID(1002, "Username must be at least 3 characters long"),
    INVALID_PASSWORD(1003, "Password must be at least 8 characters long"),
    INVALID_KEY(1004, "Invalid key"),
    USER_NOT_EXISTED(1005, "User not existed")
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
