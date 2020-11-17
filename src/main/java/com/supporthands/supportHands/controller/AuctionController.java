package com.supporthands.supportHands.controller;

import com.supporthands.supportHands.config.JwtTokenUtil;
import com.supporthands.supportHands.model.Auction;
import com.supporthands.supportHands.model.AuctionForm;
import com.supporthands.supportHands.repository.AuctionRepository;
import com.supporthands.supportHands.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AuctionController {

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/home")
    public String getAuctionForm(Model model, HttpSession httpSession)throws Exception {
        model.addAttribute("currentUser", httpSession.getAttribute("currentUser"));
        model.addAttribute("Authorization", "Bearer " + httpSession.getAttribute("Authorization"));
        model.addAttribute("auctions", auctionRepository.getAllActiveAuctions());
        return "home";
    }

    @PostMapping("auctions")
    public void process(final HttpServletRequest request, final HttpServletResponse response,
    final ServletContext servletContext, final ITemplateEngine templateEngine)throws Exception {

        List<Auction> auctions = auctionRepository.getAllActiveAuctions();
        WebContext webContext = new WebContext(request, response, servletContext, request.getLocale());
        webContext.setVariable("auctions", auctions);

        templateEngine.process("home", webContext, response.getWriter());
    }

    @RequestMapping("auctions/home")
    public String getAllActiveAuctions(Model model) {
        model.addAttribute("auctions", auctionRepository.getAllActiveAuctions());
        return "home";
    }

    @GetMapping("/auction/{auctionId}")
    public Auction getAuction(@PathVariable int auctionId) {
        return auctionRepository.getAuction(auctionId);
    }

    @PutMapping("/{coins}/{amount}/{sellerId}/{status}")
    public int createAuction(@PathVariable double coins, @PathVariable double amount, @PathVariable int sellerId, @PathVariable String status) {
        return auctionRepository.createAuction(coins, amount, sellerId, status);
    }

    @PutMapping("/update/{auctionId}/{buyerId}/{status}")
    public int updateAuction(@PathVariable int auctionId, @PathVariable int buyerId, @PathVariable String status) {
        return auctionRepository.updateAuction(auctionId, buyerId, status);
    }
}
