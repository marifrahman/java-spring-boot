package com.mar.mardemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.mar.mardemo.controllers.CalculatorController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculateControllerTests {

	CalculatorController calculatorController;

    @BeforeEach                                         
    void setUp() {
        calculatorController = new CalculatorController();
    }
	
	@Test
	@DisplayName("Perform calculation based on given input") 
	void calculateSquareRootTest() {
		Integer[] inputs = {5,6,4,1};
		String expected = "8.77";
		var actual = calculatorController.calculateSquareRoot(inputs);
		assertEquals(expected, actual);
	}

}
