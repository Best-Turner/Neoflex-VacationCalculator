package ru.neoflex.vacationcalculator.service;

import ru.neoflex.vacationcalculator.dto.ApiRequest;
import ru.neoflex.vacationcalculator.dto.ApiResponse;

public interface CalculateService {

    ApiResponse calculateSalary(ApiRequest request);

}
