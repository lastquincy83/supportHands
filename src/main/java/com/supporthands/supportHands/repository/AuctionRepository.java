package com.supporthands.supportHands.repository;

import com.supporthands.supportHands.model.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuctionRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ArrayList<Auction> getAllActiveAuctions() {
        ArrayList<Auction> auctionList = new ArrayList<>();
        auctionList.addAll(jdbcTemplate.query("SELECT * from auction where status = 'ACTIVE'", new BeanPropertyRowMapper<>(Auction.class)));
        return auctionList;
    }

    public Auction getAuction(int auctionId) {
        Auction auction;
        auction = jdbcTemplate.queryForObject("SELECT * from auction where auctionid = "+auctionId, new BeanPropertyRowMapper<>(Auction.class));
        return auction;
    }

    public int createAuction(double coins, double amount, int sellerId, String status) {
        int result;
        result = jdbcTemplate.update("insert into auction (coins, amount, sellerid, status, buyerid) values ("+coins+", "+amount+" ,"+sellerId+", '"+status+"', 0)");
        return result;
    }

    public int updateAuction(int auctionId, int buyerId, String status) {
        int result;
        result = jdbcTemplate.update("update auction set buyerId = " + buyerId + ", status = '" + status + "' where auctionId = " + auctionId);
        return result;

    }
}
