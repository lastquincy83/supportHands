package com.supporthands.supportHands.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RegisterForm {

    private int userId;
    private String username;
    private String password;
    private String firstName;
    private String surname;
    private String emailAddress;
    private String mobileNumber;
    private String bank;
    private String accountType;
    private String accountNumber;

    public RegisterForm() {
        super();
    }
}
