package com.supporthands.supportHands.controller;

import com.supporthands.supportHands.model.BankingDetails;
import com.supporthands.supportHands.model.RegisterForm;
import com.supporthands.supportHands.model.User;
import com.supporthands.supportHands.repository.BankingDetailsInterface;
import com.supporthands.supportHands.repository.BankingDetailsRepository;
import com.supporthands.supportHands.repository.UserInterface;
import com.supporthands.supportHands.repository.UserRepository;
import com.supporthands.supportHands.service.JwtUserDetailsService;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Dates;

import java.net.PasswordAuthentication;
import java.security.KeyStore;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BankingDetailsRepository bankingDetailsRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute(name = "registerForm") RegisterForm registerForm, Model model) {
        ArrayList<String> informationMissing = new ArrayList<>();
        if (registerForm.getMobileNumber() == null || registerForm.getMobileNumber().length() < 10 || !userRepository.findByMobileNumber(registerForm.getMobileNumber()).isEmpty()) {

            informationMissing.add("Please enter valid Mobile Number");
            model.addAttribute("informationMissing", informationMissing);
            return "register";
        }

        if (registerForm.getUsername() == null || !userRepository.findByUsernameIgnoreCase(registerForm.getUsername()).isEmpty()) {
            informationMissing.add("Username already exists, please use a unique username.");
            model.addAttribute("informationMissing", informationMissing);
            return "register";
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setEmailAddress(registerForm.getEmailAddress());
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        user.setFirstName(registerForm.getFirstName());
        user.setSurname(registerForm.getSurname());
        user.setMobileNumber(registerForm.getMobileNumber());
        user.setUsername(registerForm.getUsername());
        user.setStatus("ACTIVE");


        Date date = Calendar.getInstance().getTime();
        user.setCreatedBy(registerForm.getUsername());
        user.setCreationDate(new Timestamp(date.getTime()));
        user.setLastModifiedDate(new Timestamp(date.getTime()));


        user = userRepository.save(user);

        if (user.getUserId() > 0) {
            bankingDetailsRepository.createBankingDetails(user.getUserId(), registerForm.getBank(), registerForm.getAccountType(), registerForm.getAccountNumber());
            return "login";
        }

        model.addAttribute("Registration unsuccessful", true);

        return "/register";
    }
}
