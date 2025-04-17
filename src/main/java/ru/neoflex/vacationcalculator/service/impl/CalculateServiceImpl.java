package ru.neoflex.vacationcalculator.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.vacationcalculator.dto.ApiRequest;
import ru.neoflex.vacationcalculator.dto.ApiResponse;
import ru.neoflex.vacationcalculator.exception.ApiException;
import ru.neoflex.vacationcalculator.exception.IncorrectInputException;
import ru.neoflex.vacationcalculator.exception.InvalidSalaryException;
import ru.neoflex.vacationcalculator.model.VacationPay;
import ru.neoflex.vacationcalculator.service.CalculateService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculateServiceImpl implements CalculateService {
    private static final int DEFAULT_AMOUNT_DAYS = 28;
    private static final double AVERAGE_CALENDAR_DAYS_IN_MONTH = 29.3;
    @Value("${app.max-amount-days}")
    private int maxAmountVacationDays;
    @Value("${app.min-amount-days}")
    private int minAmountVacationDays;

    @Override
    public ApiResponse calculateSalary(ApiRequest request) {
        ApiResponse response = new ApiResponse();
        try {
            int validatedAmountDays = checkInputVacationDays(request.getVacationDays());
            BigDecimal avrSalary = checkInputAvrSalary(request.getAverageSalary());
            VacationPay vacationPay = processCalculate(avrSalary, validatedAmountDays);
            response.setResult(true);
            response.setVacationPay(vacationPay);
        } catch (ApiException e) {
            response.setResult(false);
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    private VacationPay processCalculate(BigDecimal avrSalary, Integer vacationDays) {
        BigDecimal averageMonthlySalary = avrSalary.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
        BigDecimal averageDailyEarnings = averageMonthlySalary.divide(BigDecimal.valueOf(AVERAGE_CALENDAR_DAYS_IN_MONTH), 10, RoundingMode.HALF_UP);
        BigDecimal amountVacationPay = averageDailyEarnings.multiply(BigDecimal.valueOf(vacationDays)).setScale(2, RoundingMode.HALF_UP);  // Округление до копеек

        return new VacationPay(amountVacationPay, vacationDays);
    }

    private BigDecimal checkInputAvrSalary(BigDecimal value) throws InvalidSalaryException {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidSalaryException("Зарплата не указана или указанно не верно");
        }
        return value;
    }

    private int checkInputVacationDays(Integer vacationDays) throws IncorrectInputException {
        if (vacationDays == null) {
            return DEFAULT_AMOUNT_DAYS;
        }
        int tempDays = vacationDays;
        if (tempDays < minAmountVacationDays || tempDays > maxAmountVacationDays) {
            throw new IncorrectInputException("Неверно указано количество выходных дней. Число должно быть в диапазоне от " + minAmountVacationDays + " до " + maxAmountVacationDays);
        }
        return tempDays;
    }
}
