package com.bardotoco.domain.useCases.utils;

public class CashierNotOpenedException extends RuntimeException{
    public CashierNotOpenedException(String message) {
        super(message);
    }
}
