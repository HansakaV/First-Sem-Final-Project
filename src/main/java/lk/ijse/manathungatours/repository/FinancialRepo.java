package lk.ijse.manathungatours.repository;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.dto.FinancialDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinancialRepo {
//    public static boolean save(FinancialDTO financial) throws SQLException {
//        String sql = "INSERT INTO financial VALUES(?, ?, ?, ?)";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, financial.getBusId());
//        pstm.setObject(2,financial.getDescription());
//        pstm.setObject(3, financial.getIncome());
//        pstm.setObject(4, financial.getCost());
//
//        return pstm.executeUpdate() > 0;
//    }
//    public static FinancialDTO searchById(String id) throws SQLException {
//        String sql = "SELECT * FROM financial WHERE  bus_id = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, id);
//
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()) {
//            String Bid = resultSet.getString(1);
//            String description = resultSet.getString(2);
//            String income = resultSet.getString(3);
//            String cost = resultSet.getString(4);
//
//            FinancialDTO financial = new FinancialDTO(Bid, description, income, cost);
//
//            return financial ;
//        }
//
//        return null;
//    }
//
//    public static boolean update(FinancialDTO financial) throws SQLException {
//        String sql = "UPDATE financial SET description = ?, income = ?, cost = ? WHERE  bus_id = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, financial.getBusId());
//        pstm.setObject(2,financial.getDescription());
//        pstm.setObject(3, financial.getIncome());
//        pstm.setObject(4, financial.getCost());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static boolean delete(String id) throws SQLException {
//        String sql = "DELETE FROM financial WHERE  bus_id = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, id);
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static List<FinancialDTO> getAll() throws SQLException {
//        String sql = "SELECT * FROM financial";
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery();
//
//        List<FinancialDTO> financialsList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            String busId = resultSet.getString(1);
//            String description = resultSet.getString(2);
//            String income = resultSet.getString(3);
//            String cost = resultSet.getString(4);
//
//            FinancialDTO f = new FinancialDTO(busId, description, income,cost);
//            financialsList.add(f);
//        }
//        return financialsList;
//    }
//
//    public static List<String> getIds() throws SQLException {
//        String sql = "SELECT bus_id FROM financial";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        List<String> idList = new ArrayList<>();
//
//        ResultSet resultSet = pstm.executeQuery();
//        while (resultSet.next()) {
//            String id = resultSet.getString(1);
//            idList.add(id);
//        }
//        return idList;
//    }
}
