package com.supporthands.supportHands.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginForm {

    private String username;
    private String password;

    public LoginForm() {
        super();
    }
}
