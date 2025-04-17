package ru.neoflex.vacationcalculator.exception;

public class IncorrectInputException extends ApiException {
    public IncorrectInputException(String message) {
        super(message);
    }
}
