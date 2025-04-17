package ru.neoflex.vacationcalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class VacationPay {

    private BigDecimal vacationPay;

    @JsonIgnore
    private int amountDays;

    public VacationPay(BigDecimal vacationPay, int amountDays) {
        this.vacationPay = vacationPay;
        this.amountDays = amountDays;
    }

    public BigDecimal getVacationPay() {
        return vacationPay;
    }

    public void setVacationPay(BigDecimal vacationPay) {
        this.vacationPay = vacationPay;
    }

    public int getAmountDays() {
        return amountDays;
    }

    public void setAmountDays(int amountDays) {
        this.amountDays = amountDays;
    }
}
