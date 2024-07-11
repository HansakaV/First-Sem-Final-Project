package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.dao.SQLUtill;
import lk.ijse.manathungatours.dao.custom.financialDAO;
import lk.ijse.manathungatours.dto.FinancialDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class financialDaoImpl implements financialDAO {
    public  boolean save(FinancialDTO financial) throws SQLException {
        String sql = "INSERT INTO financial VALUES(?, ?, ?, ?)";
        return SQLUtill.executeUpdate(sql,financial.getBusId(),financial.getDescription(),financial.getIncome(),financial.getCost());
    }
    public  ArrayList<FinancialDTO> search(String id) throws SQLException {
        String sql = "SELECT * FROM financial WHERE  bus_id = ?";
        ResultSet resultSet =SQLUtill.executeQuery(sql,id);

       ArrayList<FinancialDTO> financialDTOS = new ArrayList<>();
        if (resultSet.next()) {
            String Bid = resultSet.getString(1);
            String description = resultSet.getString(2);
            String income = resultSet.getString(3);
            String cost = resultSet.getString(4);
           financialDTOS.add(new FinancialDTO(Bid,description,income,cost));
        }
        return financialDTOS;
    }

    public  boolean update(FinancialDTO financial) throws SQLException {
        String sql = "UPDATE financial SET description = ?, income = ?, cost = ? WHERE  bus_id = ?";
        return SQLUtill.executeUpdate(sql,financial.getBusId(),financial.getDescription(),financial.getIncome(),financial.getCost());
    }

    public  boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM financial WHERE  bus_id = ?";
        return SQLUtill.executeUpdate(sql);
    }

    public  ArrayList<FinancialDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM financial";
        ResultSet resultSet = SQLUtill.executeQuery(sql);
        ArrayList<FinancialDTO> financialsList = new ArrayList<>();
        while (resultSet.next()) {
            String busId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String income = resultSet.getString(3);
            String cost = resultSet.getString(4);

            FinancialDTO f = new FinancialDTO(busId, description, income,cost);
            financialsList.add(f);
        }
        return financialsList;
    }

    public  ArrayList<String> getIds() throws SQLException {
        String sql = "SELECT bus_id FROM financial";
        ResultSet resultSet = SQLUtill.executeQuery(sql);
        ArrayList<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    @Override
    public String getCurrentId() throws SQLException {
        return null;
    }
}
