package ru.neoflex.vacationcalculator.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.neoflex.vacationcalculator.model.VacationPay;

public class ApiResponse {

    private boolean result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VacationPay vacationPay;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public VacationPay getVacationPay() {
        return vacationPay;
    }

    public void setVacationPay(VacationPay vacationPay) {
        this.vacationPay = vacationPay;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
