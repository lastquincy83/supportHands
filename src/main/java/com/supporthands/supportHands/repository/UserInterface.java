package com.supporthands.supportHands.repository;

import com.supporthands.supportHands.model.User;

public interface UserInterface {

    User getUser(String mobileNumber);

    int createUser(String firstName, String surname, String emailAddress, String mobileNumber, String status, String username, String password);
}
