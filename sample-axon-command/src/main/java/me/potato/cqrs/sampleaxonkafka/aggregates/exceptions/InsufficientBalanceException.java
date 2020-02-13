package me.potato.cqrs.sampleaxonkafka.aggregates.exceptions;

public class InsufficientBalanceException extends RuntimeException {

    private static final long serialVersionUID = -4379468755761411525L;

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
