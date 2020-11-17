package com.supporthands.supportHands.controller;

import com.supporthands.supportHands.config.JwtTokenUtil;
import com.supporthands.supportHands.model.Auction;
import com.supporthands.supportHands.model.LoginForm;
import com.supporthands.supportHands.model.User;
import com.supporthands.supportHands.repository.BankingDetailsRepository;
import com.supporthands.supportHands.repository.UserRepository;
import com.supporthands.supportHands.service.JwtUserDetailsService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BankingDetailsRepository bankingDetailsRepository;

    @Autowired
    JwtAuthenticationController jwtAuthenticationController;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
        return "portfolio";
    }
//
//    @GetMapping("/login/{username}")
//    public String loginUser(@PathVariable("username") String username, Model model) {
//        User user = userRepository.getUserByUsername(username);
//        if(user != null) {
//            model.addAttribute("currentUser", username);
//            model.addAttribute("user", user);
//            model.addAttribute("bankingDetails", bankingDetailsRepository.getUserBankingDetails(user.getUserId()));
//        }
//        return "view-account-details";
//    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name = "loginForm")LoginForm loginForm, Model model, HttpSession session) {
        try {
            return jwtAuthenticationController.generateAuthenticationToken(loginForm, model, session);
        } catch (UsernameNotFoundException e) {
            model.addAttribute("InvalidCredentials", "Incorrect username/password");
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            model.addAttribute("Unknown error", "Shits broken man");
            System.out.println(ex.getMessage());
        }
        return "portfolio";
    }

//    @SneakyThrows
//    @PostMapping("/login")
//    public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model, HttpSession session) {
//        try {
//            authenticate(loginForm.getUsername(), loginForm.getPassword(), model);
//
//            final UserDetails userDetails = jwtInMemoryUserDetailsService
//                    .loadUserByUsername(loginForm.getUsername());
//
//            final String token = jwtTokenUtil.generateToken(userDetails);
//            session.setAttribute("Authorization", token);
//            return "redirect:/home";
//        } catch (Exception e) {
//            model.addAttribute("InvalidCredentials", "Incorrect username/password");
//            return "login";
//        }
//    }
//
//    private void authenticate(String username, String password, Model model) throws Exception {
//        Objects.requireNonNull(username);
//        Objects.requireNonNull(password);
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            model.addAttribute("InvalidCredentials", true);
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            model.addAttribute("InvalidCredentials", true);
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }
}
