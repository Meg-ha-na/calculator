package com.example.calculator.service;

import com.example.calculator.exception.InvalidInputException;
import com.example.calculator.model.Operation;
import com.example.calculator.service.operations.CalculatorOperation;
import com.example.calculator.service.operations.OperationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Autowired
    private OperationFactory operationFactory;

    @Override
    public double calculate(Operation operation, double num1, double num2) {
        validateInput(num1, num2);
        CalculatorOperation calculatorOperation = operationFactory.getOperation(operation);
        return calculatorOperation.calculate(num1, num2);
    }

    @Override
    public double chainCalculate(double initialValue, Map<Operation, Double> operationsAndValues) {
        validateInput(initialValue);
        double result = initialValue;

        for (Map.Entry<Operation, Double> entry : operationsAndValues.entrySet()) {
            Operation operation = entry.getKey();
            double value = entry.getValue();
            validateInput(value);
            result = calculate(operation, result, value);
        }

        return result;
    }

    private void validateInput(double... inputs) {
        for (double input : inputs) {
            if (Double.isNaN(input) || Double.isInfinite(input)) {
                throw new InvalidInputException("Invalid input provided.");
            }
        }
    }
}