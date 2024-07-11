package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.dao.SQLUtill;
import lk.ijse.manathungatours.dao.custom.customerDAO;
import lk.ijse.manathungatours.dto.PassengerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class customerDaoImpl implements customerDAO {
    @Override
    public ArrayList<PassengerDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM passengers";
        ResultSet resultSet = SQLUtill.executeQuery(sql);
        ArrayList<PassengerDTO> allPassengers = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            allPassengers.add(new PassengerDTO(id, name, address, tel));
        }
        return allPassengers;
    }
    @Override
    public boolean save(PassengerDTO dto) throws SQLException {
        String sql = "INSERT INTO passengers VALUES(?, ?, ?, ?)";
        return SQLUtill.executeUpdate(sql,dto.getId(),dto.getName(),dto.getAddress(),dto.getTel());

    }

    @Override
    public ArrayList<PassengerDTO> search(String id) throws SQLException {
        String sql = "SELECT * FROM passengers WHERE  passenger_id = ?";
        ResultSet resultSet = SQLUtill.executeQuery(sql,id);
        ArrayList<PassengerDTO> passengerDTOS = new ArrayList<>();
        while (resultSet.next()) {
            String Pid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            passengerDTOS.add(new PassengerDTO(Pid, name, address, tel));
        }
        return passengerDTOS;
    }
    @Override
    public boolean update(PassengerDTO dto) throws SQLException {
        String sql = "UPDATE passengers SET name = ?, address = ?, tel = ? WHERE  passenger_id = ?";
        return SQLUtill.executeUpdate(sql,dto.getId(),dto.getName(),dto.getAddress(),dto.getTel());
    }
    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM passengers WHERE  passenger_id = ?";
        return SQLUtill.executeUpdate(sql,id);
   }
    public  ArrayList<String> getIds() throws SQLException {
        String sql = "SELECT passenger_id FROM passengers";
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
