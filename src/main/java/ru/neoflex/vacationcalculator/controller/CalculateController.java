package ru.neoflex.vacationcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.vacationcalculator.dto.ApiRequest;
import ru.neoflex.vacationcalculator.dto.ApiResponse;
import ru.neoflex.vacationcalculator.service.CalculateService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CalculateController {

    private final CalculateService calculateService;

    @Autowired
    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }


    @GetMapping("/calculate")
    public ResponseEntity<ApiResponse> calculate(@ModelAttribute ApiRequest request) {

        ApiResponse apiResponse = calculateService.calculateSalary(request);
        if (!apiResponse.isResult()) {
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
