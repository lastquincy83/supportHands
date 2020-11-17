package com.supporthands.supportHands.controller;

import com.supporthands.supportHands.model.User;
import com.supporthands.supportHands.repository.BankingDetailsRepository;
import com.supporthands.supportHands.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BankingDetailsRepository bankingDetailsRepository;

//    @GetMapping(path = "/getusernames")
//    public List<User> getAllUserNames() {
//        return userRepository.getAllUserNames();
//    }

    @GetMapping("/{userId}/{currentUser}")
    public String getUser(@PathVariable int userId, @PathVariable String currentUser , Model model) {
        model.addAttribute("currentUser", currentUser);
        model.addAttribute(userRepository.findUserByUserId(userId));
        model.addAttribute(bankingDetailsRepository.getUserBankingDetails(userId));
        return "view-account-details";
    }

//    @PutMapping("/{firstName}/{surname}/{emailAddress}/{mobileNumber}/{status}")
//    public int createUser(@PathVariable String firstName, @PathVariable String surname, @PathVariable String emailAddress, @PathVariable String mobileNumber, @PathVariable String status) {
//        return userRepository.createUser(firstName, surname, emailAddress, mobileNumber, status, username, password);
//    }

//    @PutMapping("/update/{userId}/{status}")
//    public int updateUserStatus(@PathVariable int userId, @PathVariable String status) {
//        return userRepository.updateUserStatus(userId, status);
//    }

}
