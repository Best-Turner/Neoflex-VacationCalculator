package ru.neoflex.vacationcalculator.dto;

import java.math.BigDecimal;

public class ApiRequest {
    private BigDecimal averageSalary;
    private Integer vacationDays;

    public ApiRequest(BigDecimal averageSalary) {
        this.averageSalary = averageSalary;
    }

    public BigDecimal getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(BigDecimal averageSalary) {
        this.averageSalary = averageSalary;
    }

    public Integer getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(Integer vacationDays) {
        this.vacationDays = vacationDays;
    }
}
