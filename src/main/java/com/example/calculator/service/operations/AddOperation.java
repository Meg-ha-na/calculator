package com.example.calculator.service.operations;

import org.springframework.stereotype.Component;

@Component
public class AddOperation implements CalculatorOperation {

    @Override
    public double calculate(double num1, double num2) {

        return num1 + num2;
    }
}
