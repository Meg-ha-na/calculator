package com.example.calculator.service.operations;

import com.example.calculator.exception.UnsupportedOperationException;
import com.example.calculator.model.Operation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OperationFactory {

    private final Map<Operation, CalculatorOperation> operations = new HashMap<>();

    public OperationFactory(AddOperation addOperation, SubtractOperation subtractOperation,
                            MultiplyOperation multiplyOperation, DivideOperation divideOperation) {
        operations.put(Operation.ADD, addOperation);
        operations.put(Operation.SUBTRACT, subtractOperation);
        operations.put(Operation.MULTIPLY, multiplyOperation);
        operations.put(Operation.DIVIDE, divideOperation);
    }

    public CalculatorOperation getOperation(Operation operation) {
        CalculatorOperation calculatorOperation = operations.get(operation);
        if (calculatorOperation == null) {
            throw new UnsupportedOperationException("Operation not supported.");
        }
        return calculatorOperation;
    }
}