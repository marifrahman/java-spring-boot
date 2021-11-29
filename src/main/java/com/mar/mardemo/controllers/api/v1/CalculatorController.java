package com.mar.mardemo.controllers.api.v1;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RequestMapping("/api/v1/calculator")
@RestController
public class CalculatorController extends BaseController {

    private static final int itemLimit = 3;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    
    /** 
     * @param inputs
     * @return ResponseEntity<String>
     */
    @GetMapping("/calculate")
    public ResponseEntity<String> calculateSquareRoot(@RequestParam Integer[] inputs)
    {
        if(null == inputs ) {
            throw new IllegalArgumentException("{\"error\":\"Must supply the input parameter, inputs\"}");
        }

        List<Integer> list = Arrays.asList(inputs);
        Double summedValue = list.stream()
                                 .sorted(Collections.reverseOrder())
                                 .limit(itemLimit)
                                 .mapToDouble(x -> {return Math.pow(x, 2);})
                                 .sum();
                                        
        return ResponseEntity.ok(df.format(Math.sqrt(summedValue)));
    }
   
}
