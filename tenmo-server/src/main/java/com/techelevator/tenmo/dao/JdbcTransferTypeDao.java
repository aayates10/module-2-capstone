package com.techelevator.tenmo.dao;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTransferTypeDao implements TransferTypeDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferTypeDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public TransferType getTransferTypeById(int typeId) {
        String sql = "SELECT transfer_type_id FROM transfer_type, WHERE transfer_type_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,typeId);
        if (results.next()){
            return mapRowToTransferType(results);
        }else{

        }
        return null;

}

    @Override
    public TransferType getTransferTypeByDesc(String desc) {
        String sql = " SELECT transfer_type_id,transfer_type_desc FROM transfer_type, WHERE transfer_type_desc = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,desc);
        if (results.next()){
            return mapRowToTransferType(results);

        }else{

        }
        return null;
    }

    private TransferType mapRowToTransferType(SqlRowSet rs) {
        TransferType transferType = new TransferType();
        transferType.setTypeId(rs.getInt("transfer_type_id"));
        transferType.setTransferTypeDesc(rs.getString("transfer_type_desc"));
        return transferType;


    }


}

