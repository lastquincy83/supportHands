package com.supporthands.supportHands.controller;

import com.supporthands.supportHands.model.Bids;
import com.supporthands.supportHands.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/bid")
public class BidController {

    @Autowired
    BidRepository bidRepository;

    @GetMapping()
    public List<Bids> getAllActiveBids() {
        return bidRepository.getAllActiveBids();
    }

    @GetMapping("/{bidId}")
    public Bids getBid(@PathVariable int bidId) {
        return bidRepository.getBid(bidId);
    }

    @PutMapping("/{coins}/{amount}/{buyerId}/{status}/{auctionId}")
    public int createBids(@PathVariable double coins, @PathVariable double amount, @PathVariable int buyerId, @PathVariable String status, @PathVariable int auctionId) {
        return bidRepository.createBid(coins, amount, buyerId, status, auctionId);
    }

    @PutMapping("/update/{buyerId}/{status}/{auctionId}/{bidId}")
    public int updateBids(@PathVariable int buyerId, @PathVariable String status, @PathVariable int auctionId, @PathVariable int bidId) {
        return bidRepository.updateBid(buyerId, status, auctionId, bidId);
    }
}
