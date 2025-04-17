package ru.neoflex.vacationcalculator.exception;

public class InvalidSalaryException extends ApiException {
    public InvalidSalaryException(String message) {
        super(message);
    }
}
