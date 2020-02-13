package me.potato.cqrs.sampleaxonkafka.commands;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountCreateCommand  extends BaseCommand<String>{
    private String accountHolder;
    private String accountHolderName;

    public AccountCreateCommand(String id, String accountHolder, String accountHolderName) {
        super(id);
        this.accountHolder = accountHolder;
        this.accountHolderName = accountHolderName;
    }

}
