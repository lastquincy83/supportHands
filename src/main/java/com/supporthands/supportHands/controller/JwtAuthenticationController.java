package com.supporthands.supportHands.controller;

import com.supporthands.supportHands.config.JwtTokenUtil;
import com.supporthands.supportHands.model.JwtRequest;
import com.supporthands.supportHands.model.JwtResponse;
import com.supporthands.supportHands.model.LoginForm;
import com.supporthands.supportHands.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Objects;


@Controller
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuctionController auctionController;

    @Qualifier("jwtUserDetailsService")
    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String generateAuthenticationToken(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model, HttpSession httpSession)
            throws Exception {

        authenticate(loginForm.getUsername(), loginForm.getPassword());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(loginForm.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        httpSession.setAttribute("Authorization", "Bearer "+token);
        httpSession.setAttribute("currentUser", loginForm.getUsername());
        System.out.println(token);
        return auctionController.getAuctionForm(model, httpSession);
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}