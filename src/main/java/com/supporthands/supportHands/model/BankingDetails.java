package com.supporthands.supportHands.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BankingDetails {

    private int userId;
    private String bank;
    private String accountType;
    private String accountNumber;
}
