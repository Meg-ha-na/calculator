package com.example.calculator.controller;

import com.example.calculator.model.Operation;
import com.example.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculate")
    public double calculate(@RequestParam Operation operation,
                            @RequestParam double num1,
                            @RequestParam double num2) {
        return calculatorService.calculate(operation, num1, num2);
    }

    @PostMapping("/chain-calculate")
    public double chainCalculate(@RequestParam double initialValue,
                                 @RequestBody Map<Operation, Double> operationsAndValues) {
        return calculatorService.chainCalculate(initialValue, operationsAndValues);
    }
}