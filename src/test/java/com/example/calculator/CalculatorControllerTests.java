package com.example.calculator;

import com.example.calculator.controller.CalculatorController;
import com.example.calculator.model.Operation;
import com.example.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @Autowired
    private CalculatorController calculatorController;

    @Test
    void testCalculateAddition() throws Exception {
        when(calculatorService.calculate(eq(Operation.ADD), anyDouble(), anyDouble())).thenReturn(8.0);

        mockMvc.perform(get("/api/calculator/calculate")
                        .param("operation", "ADD")
                        .param("num1", "5")
                        .param("num2", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("8.0"));
    }

    @Test
    void testCalculateSubtraction() throws Exception {
        when(calculatorService.calculate(eq(Operation.SUBTRACT), anyDouble(), anyDouble())).thenReturn(2.0);

        mockMvc.perform(get("/api/calculator/calculate")
                        .param("operation", "SUBTRACT")
                        .param("num1", "5")
                        .param("num2", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("2.0"));
    }



    @Test
    void testCalculateInvalidOperation() throws Exception {
        mockMvc.perform(get("/api/calculator/calculate")
                        .param("operation", "INVALID")
                        .param("num1", "5")
                        .param("num2", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testChainCalculateWithMap() throws Exception {
        when(calculatorService.chainCalculate(anyDouble(), anyMap()))
                .thenReturn(16.0);

        String requestBody = "{\"ADD\": 3, \"MULTIPLY\": 2}";

        mockMvc.perform(post("/api/calculator/chain-calculate")
                        .param("initialValue", "5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("16.0"));
    }
}