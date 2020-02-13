package me.potato.cqrs.sampleaxonkafka.events;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountCreatedEvent extends BaseEvents<String>{
    private String accHolder;
    private String accHolderName;
    private BigDecimal balance;

    public AccountCreatedEvent(String id, String accHolder, String accHolderName, BigDecimal balance) {
        super(id);
        this.accHolder = accHolder;
        this.accHolderName = accHolderName;
        this.balance = balance;
    }
}
