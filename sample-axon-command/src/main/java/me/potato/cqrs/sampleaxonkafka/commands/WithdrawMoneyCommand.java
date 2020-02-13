package me.potato.cqrs.sampleaxonkafka.commands;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class WithdrawMoneyCommand extends BaseCommand<String>{
    private String accountHolder;
    private BigDecimal amount;

    public WithdrawMoneyCommand(String id, String accountHolder, BigDecimal amount) {
        super(id);
        this.accountHolder = accountHolder;
        this.amount = amount;
    }
}
