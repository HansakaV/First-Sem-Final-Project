package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.model.Engineer;
import lk.ijse.manathungatours.model.EngineerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class engineerDaoImpl {
    public ArrayList<EngineerDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM enginners";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<EngineerDTO> engineerList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            engineerList.add(new EngineerDTO(id,name,address,tel));
        }
        return engineerList;

    }
    public boolean save(EngineerDTO dto) throws SQLException {
        String sql = "INSERT INTO enginners VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getId());
        pstm.setObject(2,dto.getName());
        pstm.setObject(3, dto.getAddress());
        pstm.setObject(4, dto.getTel());

        return pstm.executeUpdate() > 0;
    }
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM enginners WHERE  enginner_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public boolean update(EngineerDTO engineer) throws SQLException {
        String sql = "UPDATE conductors SET name = ?, address = ?, tel = ? WHERE  enginner_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, engineer.getId());
        pstm.setObject(2,engineer.getName());
        pstm.setObject(3, engineer.getAddress());
        pstm.setObject(4, engineer.getTel());

        return pstm.executeUpdate() > 0;

    }
    public ArrayList<EngineerDTO> search(String id) throws SQLException {
        String sql = "SELECT * FROM enginners WHERE  enginner_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ArrayList<EngineerDTO> engineerDTOS = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String Eid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            engineerDTOS.add(new EngineerDTO(Eid,name,address,tel));
        }
        return  engineerDTOS;
    }
}
