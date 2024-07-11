package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.dao.SQLUtill;
import lk.ijse.manathungatours.dao.custom.busDAO;
import lk.ijse.manathungatours.dto.BusDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class busDaoImpl implements busDAO {
    public boolean update(BusDTO dto) throws SQLException {
        String sql = "UPDATE buses SET no_of_seats = ?, Status = ?, Service = ? WHERE bus_reg_number = ?";
        return  SQLUtill.executeUpdate(sql,dto.getRegNumber(),dto.getSeats(),dto.getService(),dto.getStatus());
    }

    public boolean save(BusDTO dto) throws SQLException {
        String sql = "INSERT INTO buses VALUES(?, ?, ?, ?)";
        return  SQLUtill.executeUpdate(sql,dto.getRegNumber(),dto.getSeats(),dto.getService(),dto.getStatus());
    }

    public ArrayList<BusDTO> search(String id) throws SQLException {
        String sql = "SELECT * FROM buses WHERE bus_reg_number = ?";
        ResultSet resultSet = SQLUtill.executeQuery(sql,id);
        ArrayList<BusDTO> busDTOS = new ArrayList<>();
        if (resultSet.next()) {
            String regNum = resultSet.getString(1);
            String seats = resultSet.getString(2);
            String status = resultSet.getString(3);
            String service = resultSet.getString(4);
            busDTOS.add(new BusDTO(regNum, seats, status, service));
        }
        return busDTOS;
    }
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM buses WHERE bus_reg_number = ?";
        return SQLUtill.executeUpdate(sql,id);
    }
    public ArrayList<String> getIds() throws SQLException {
        String sql = "SELECT bus_reg_number FROM buses";
        ResultSet resultSet = SQLUtill.executeQuery(sql);
        ArrayList<String> busDTOS = new ArrayList<>();
        while (resultSet.next()) {
            busDTOS.add(resultSet.getString(1));
        }
        return busDTOS;
    }

    @Override
    public String getCurrentId() throws SQLException {
        return null;
    }

    public ArrayList<String> checkAvailability() throws SQLException {
        String sql = "SELECT bus_reg_number FROM buses WHERE status='Available'";
        ResultSet resultSet = SQLUtill.executeQuery(sql);
        ArrayList<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }
    public ArrayList<BusDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM buses";
        ResultSet resultSet = SQLUtill.executeQuery(sql);

        ArrayList<BusDTO> busList = new ArrayList<>();
        while (resultSet.next()) {
            String regNum = resultSet.getString(1);
            String seats = resultSet.getString(2);
            String status = resultSet.getString(3);
            String service = resultSet.getString(4);

            busList.add(new BusDTO(regNum,seats,status,service));
        }
        return busList;
    }

}
