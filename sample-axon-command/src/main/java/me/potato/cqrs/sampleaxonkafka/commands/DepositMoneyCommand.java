package me.potato.cqrs.sampleaxonkafka.commands;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositMoneyCommand extends BaseCommand<String> {
    private String accountHolder;
    private BigDecimal amount;

    public DepositMoneyCommand(String id, String accountHolder, BigDecimal amount) {
        super(id);
        this.accountHolder = accountHolder;
        this.amount = amount;
    }
}
