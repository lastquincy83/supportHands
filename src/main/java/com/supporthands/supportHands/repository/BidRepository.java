package com.supporthands.supportHands.repository;

import com.supporthands.supportHands.model.Auction;
import com.supporthands.supportHands.model.Bids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BidRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Bids> getAllActiveBids() {
        List<Bids> bidsList = new ArrayList<>();
        bidsList.addAll(jdbcTemplate.query("SELECT * from bid where status != 'CLOSED'", new BeanPropertyRowMapper<>(Bids.class)));
        return bidsList;
    }

    public Bids getBid(int bidId) {
        Bids bid;
        bid = jdbcTemplate.queryForObject("SELECT * from bid where bidid = " + bidId, new BeanPropertyRowMapper<>(Bids.class));
        return bid;
    }

    public int createBid(double coins, double amount, int buyerId, String status, int auctionId) {
        int sellerId;
        Auction auction = jdbcTemplate.queryForObject("SELECT sellerid from auction where auctionid = " + auctionId, new BeanPropertyRowMapper<>(Auction.class));
        if (auction != null) {
            sellerId = auction.getSellerId();
        } else {
            return 0;
        }
        int result;
        result = jdbcTemplate.update("insert into bid (coins, amount, buyerId, status, auctionId, sellerid) values (" + coins + ", " + amount + " ," + buyerId + ", '" + status + "', " + auctionId + ", " + sellerId + ")");
        return result;
    }

    public int updateBid(int buyerId, String status, int auctionId, int bidId) {
        Auction auction = jdbcTemplate.queryForObject("SELECT sellerid from auction where auctionid = " + auctionId, new BeanPropertyRowMapper<>(Auction.class));
        if (auction != null) {
            if (auction.getStatus().equalsIgnoreCase("CLOSED")) {
                return 0;
            }
        } else {
            return 0;
        }
        int result;
        result = jdbcTemplate.update("update bid set status = '" + status + "' where bidId = " + bidId);
        return result;
    }
}
