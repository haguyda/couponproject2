package com.hagayproject.demo12.exceptions;


public class CouponSystemException extends Exception {
    public CouponSystemException(ErrMsg errMsg) {
        super(errMsg.getMessage());
    }
}
