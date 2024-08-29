package com.example.calculator.service;

import com.example.calculator.model.Operation;

import java.util.Map;

public interface CalculatorService {
    double calculate(Operation operation, double num1, double num2);
    double chainCalculate(double initialValue, Map<Operation, Double> operationsAndValues);
}
