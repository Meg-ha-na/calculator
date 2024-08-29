package com.example.calculator.exception;

public class DivisionByZeroException extends CalculatorException {
    public DivisionByZeroException(String message) {
        super(message);
    }
}