package com.mar.mardemo.controllers;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private static final int itemLimit = 3;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    @GetMapping("/calculate")
    public String calculateSquareRoot(@RequestParam Integer[] inputs)
    {
        // TODO_1: Find highest 3 numbers in the array
        // TODO_2: Compute Sqr of each
        // TODO_3: Sum the sqred numbers
        // TODO_4: Sqrroot of the sum
        // TODO_5: Use java Stream to compute
        // TODO_6: Included unit tests

        List<Integer> list = Arrays.asList(inputs);
        /*List<Integer> sortedList = list.stream()
                                       .sorted(Collections.reverseOrder())
                                                .limit(itemLimit)
                                                .collect(Collectors.toList());*/
        Double summedValue = list.stream()
                                 .sorted(Collections.reverseOrder())
                                 .limit(itemLimit)
                                 .mapToDouble(x -> {return Math.pow(x, 2);})
                                 .sum();
                                        
        return df.format(Math.sqrt(summedValue));
    }
}
