package com.example.calculator;

import com.example.calculator.exception.DivisionByZeroException;
import com.example.calculator.exception.InvalidInputException;
import com.example.calculator.model.Operation;
import com.example.calculator.service.CalculatorService;
import com.example.calculator.service.CalculatorServiceImpl;
import com.example.calculator.service.operations.OperationFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculatorServiceTests {

    @Mock
    private OperationFactory operationFactory;

    @InjectMocks
    private CalculatorServiceImpl calculatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddition() {
        when(operationFactory.getOperation(Operation.ADD)).thenReturn((num1, num2) -> num1 + num2);
        double result = calculatorService.calculate(Operation.ADD, 5, 3);
        assertEquals(8, result);
    }

    @Test
    void testSubtraction() {
        when(operationFactory.getOperation(Operation.SUBTRACT)).thenReturn((num1, num2) -> num1 - num2);
        double result = calculatorService.calculate(Operation.SUBTRACT, 5, 3);
        assertEquals(2, result);
    }

    @Test
    void testMultiplication() {
        when(operationFactory.getOperation(Operation.MULTIPLY)).thenReturn((num1, num2) -> num1 * num2);
        double result = calculatorService.calculate(Operation.MULTIPLY, 5, 3);
        assertEquals(15, result);
    }

    @Test
    void testDivision() {
        when(operationFactory.getOperation(Operation.DIVIDE)).thenReturn((num1, num2) -> num1 / num2);
        double result = calculatorService.calculate(Operation.DIVIDE, 9, 3);
        assertEquals(3, result);
    }

    @Test
    void testDivisionByZeroThrowsException() {
        when(operationFactory.getOperation(Operation.DIVIDE)).thenReturn((num1, num2) -> {
            if (num2 == 0) throw new DivisionByZeroException("Cannot divide by zero.");
            return num1 / num2;
        });

        DivisionByZeroException exception = assertThrows(DivisionByZeroException.class, () -> {
            calculatorService.calculate(Operation.DIVIDE, 9, 0);
        });

        assertEquals("Cannot divide by zero.", exception.getMessage());
    }

    @Test
    void testInvalidInputThrowsException() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            calculatorService.calculate(Operation.ADD, Double.NaN, 3);
        });

        assertEquals("Invalid input provided.", exception.getMessage());
    }

}