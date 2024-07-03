package lk.ijse.manathungatours.repository;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.model.Bus;
import lk.ijse.manathungatours.model.Conductor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConductorRepo {
//    public static boolean save(Conductor conductor) throws SQLException {
//        String sql = "INSERT INTO conductors VALUES(?, ?, ?, ?)";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, conductor.getId());
//        pstm.setObject(2,conductor.getName());
//        pstm.setObject(3, conductor.getAddress());
//        pstm.setObject(4, conductor.getTel());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static Conductor searchById(String id) throws SQLException {
//        String sql = "SELECT * FROM conductors WHERE  conductor_id = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, id);
//
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()) {
//            String Cid = resultSet.getString(1);
//            String name = resultSet.getString(2);
//            String address = resultSet.getString(3);
//            String tel = resultSet.getString(4);
//
//            Conductor conductor = new Conductor(Cid, name, address, tel);
//
//            return conductor ;
//        }
//
//        return null;
//    }
//
//    public static boolean update(Conductor conductor) throws SQLException {
//        String sql = "UPDATE conductors SET name = ?, address = ?, tel = ? WHERE  conductor_id = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, conductor.getId());
//        pstm.setObject(2,conductor.getName());
//        pstm.setObject(3, conductor.getAddress());
//        pstm.setObject(4, conductor.getTel());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static boolean delete(String id) throws SQLException {
//        String sql = "DELETE FROM conductors WHERE  conductor_id = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, id);
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static List<Conductor> getAll() throws SQLException {
//        String sql = "SELECT * FROM conductors";
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery();
//
//        List<Conductor> conductorList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            String id = resultSet.getString(1);
//            String name = resultSet.getString(2);
//            String address = resultSet.getString(3);
//            String tel = resultSet.getString(4);
//
//            Conductor conductor = new Conductor(id, name, address, tel);
//            conductorList.add(conductor);
//        }
//        return conductorList;
//    }
//
//    public static List<String> getIds() throws SQLException {
//        String sql = "SELECT conductor_id FROM conductors";
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
