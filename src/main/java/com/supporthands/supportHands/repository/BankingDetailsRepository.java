package com.supporthands.supportHands.repository;

import com.supporthands.supportHands.model.BankingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BankingDetailsRepository implements BankingDetailsInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public BankingDetails getUserBankingDetails(int userId) {
        BankingDetails bankingDetails = null;
        try {
            if(userId>0){
                bankingDetails = jdbcTemplate.queryForObject("SELECT * from bankingdetails where userid = " + userId, new BeanPropertyRowMapper<>(BankingDetails.class));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return bankingDetails;
        }
    }

    public int createBankingDetails(int userId, String bank, String accountType, String accountNumber) {
        int result;
        result = jdbcTemplate.update("insert into bankingdetails (userid, bank, accountType, accountNumber) values (" + userId + ", '" + bank + "', '" + accountType + "', '" + accountNumber + "')");
        return result;
    }

    public int updateBankingDetails(int userId, String bank, String accountType, String accountNumber) {
        int result;
        result = jdbcTemplate.update("update bankingdetails set userid =" + userId + "bank = '" + bank + "', accountType = '" + accountType + "', accountNumber = '" + accountNumber + "'");
        return result;
    }
}
