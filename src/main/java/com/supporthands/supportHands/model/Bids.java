package com.supporthands.supportHands.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bids {

    private int bidId;
    private double coins;
    private double amount;
    private int sellerId;
    private String status;
    private int buyerId;
    private int auctionId;
}
