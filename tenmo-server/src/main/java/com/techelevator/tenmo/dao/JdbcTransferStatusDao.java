package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTransferStatusDao implements TransferStatusDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferStatusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TransferStatus getTransferStatusById(int statusId) {
        String sql = "SELECT transfer_status_id FROM transfer_status, WHERE transfer_status_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,statusId);
        if (results.next()){
            return mapRowToTransferStatus(results);
        }else{

        }
        return null;
    }

    @Override
    public TransferStatus getTransferStatusByDesc(String desc) {
        String sql = " SELECT transfer_status_id,transfer_status_desc FROM transfer_status, WHERE transfer_status_desc = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,desc);
        if (results.next()){
            return mapRowToTransferStatus(results);

        }else{

        }
        return null;
    }

    private TransferStatus mapRowToTransferStatus(SqlRowSet rs){
        TransferStatus transferStatus = new TransferStatus();
        transferStatus.setStatusId(rs.getInt("transfer_status_id"));
        transferStatus.setStatusDesc(rs.getString("transfer_status_desc"));
        return transferStatus;
    }


}
