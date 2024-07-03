package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.model.Driver;
import lk.ijse.manathungatours.model.DriverDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class driverDaoImpl {
    public ArrayList<DriverDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM drivers";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<DriverDTO> driverList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            driverList.add(new DriverDTO(id,name,address,tel));
        }
        return driverList;

    }
    public boolean save(DriverDTO dto) throws SQLException {
        String sql = "INSERT INTO drivers VALUES(?, ?, ?, ?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getId());
        pstm.setObject(2,dto.getName());
        pstm.setObject(3, dto.getAddress());
        pstm.setObject(4, dto.getTel());

        return pstm.executeUpdate() > 0;
    }
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM drivers WHERE  driver_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public boolean update(DriverDTO driver) throws SQLException {
        String sql = "UPDATE conductors SET name = ?, address = ?, tel = ? WHERE  driver_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, driver.getId());
        pstm.setObject(2,driver.getName());
        pstm.setObject(3, driver.getAddress());
        pstm.setObject(4, driver.getTel());

        return pstm.executeUpdate() > 0;
    }
    public ArrayList<DriverDTO> search(String id) throws SQLException {
        String sql = "SELECT * FROM drivers WHERE  driver_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<DriverDTO> dtos = new ArrayList<>();
        if (resultSet.next()) {
            String Did = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            dtos.add(new DriverDTO(Did,name,address,tel));
        }
        return dtos;
    }
}
