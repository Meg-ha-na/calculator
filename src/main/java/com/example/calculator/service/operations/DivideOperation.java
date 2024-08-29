package com.example.calculator.service.operations;

import com.example.calculator.exception.DivisionByZeroException;
import org.springframework.stereotype.Component;

@Component
public class DivideOperation implements CalculatorOperation {

    @Override
    public double calculate(double num1, double num2) {
        if (num2 == 0) {
            throw new DivisionByZeroException("Cannot divide by zero.");
        }
        return num1 / num2;
    }
}