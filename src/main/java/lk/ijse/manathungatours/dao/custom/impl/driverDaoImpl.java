package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.dao.SQLUtill;
import lk.ijse.manathungatours.dao.custom.driverDAO;
import lk.ijse.manathungatours.dto.DriverDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class driverDaoImpl implements driverDAO {
    public ArrayList<DriverDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM drivers";
        ResultSet resultSet = SQLUtill.executeQuery(sql);
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
        return SQLUtill.executeUpdate(sql,dto.getId(),dto.getName(),dto.getAddress(),dto.getTel());
    }
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM drivers WHERE  driver_id = ?";
        return SQLUtill.executeUpdate(sql);

    }
    public boolean update(DriverDTO driver) throws SQLException {
        String sql = "UPDATE conductors SET name = ?, address = ?, tel = ? WHERE  driver_id = ?";
        return SQLUtill.executeUpdate(sql);
    }
    public ArrayList<DriverDTO> search(String id) throws SQLException {
        String sql = "SELECT * FROM drivers WHERE  driver_id = ?";
        ResultSet resultSet = SQLUtill.executeQuery(sql);
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
    public  ArrayList<String> getIds() throws SQLException {
        String sql = "SELECT driver_id FROM drivers";
        ResultSet resultSet =SQLUtill.executeQuery(sql);
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
