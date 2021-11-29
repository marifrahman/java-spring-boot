package com.mar.mardemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mar.mardemo.controllers.api.v1.CalculatorController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
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
		ResponseEntity<String> expected = ResponseEntity.ok("8.77");
		var actual = calculatorController.calculateSquareRoot(inputs);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Throws exception if input is null") 
	void calculateSquareRoot_blank_input_throw_exception_Test() throws Exception {
		Integer[] inputs = null;
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
						() ->calculatorController.calculateSquareRoot(inputs) );
		assertTrue(thrown.getMessage().contains("{\"error\":\"Must supply the input parameter, inputs\"}"));				
	}

    @Autowired
    private MockMvc mockMvc;

    //@MockBean
    //private BookRepository mockRepository;
	@Test
    public void calculate_with_blank_400() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/calculator/calculate"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

	@Test
    public void calculate_with_string_400() throws Exception {

		String input = "teststring";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/calculator/calculate?inputs="+input))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
