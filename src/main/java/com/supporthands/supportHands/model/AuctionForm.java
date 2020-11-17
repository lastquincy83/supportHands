package com.supporthands.supportHands.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuctionForm {
    private int auctionId;
    private double coins;
    private double amount;
    private int sellerId;
    private String status;
    private int buyerId;

    public AuctionForm() {
        super();
    }
}
