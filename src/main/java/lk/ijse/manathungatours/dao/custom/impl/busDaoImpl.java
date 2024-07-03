package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.model.Bus;
import lk.ijse.manathungatours.model.BusDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class busDaoImpl {
    public boolean update(BusDTO dto) throws SQLException {
        String sql = "UPDATE buses SET no_of_seats = ?, Status = ?, Service = ? WHERE bus_reg_number = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getRegNumber());
        pstm.setObject(2, dto.getSeats());
        pstm.setObject(3, dto.getStatus());
        pstm.setObject(4, dto.getService());

        return pstm.executeUpdate() > 0;
    }

    public boolean save(BusDTO dto) throws SQLException {
        String sql = "INSERT INTO buses VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getRegNumber());
        pstm.setObject(2, dto.getSeats());
        pstm.setObject(3, dto.getStatus());
        pstm.setObject(4, dto.getService());

        return pstm.executeUpdate() > 0;
    }

    public ArrayList<BusDTO> search(String id) throws SQLException {
        String sql = "SELECT * FROM buses WHERE bus_reg_number = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
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

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public ArrayList<String> getCodes() throws SQLException {
        String sql = "SELECT bus_reg_number FROM buses";
        ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
        ArrayList<String> busDTOS = new ArrayList<>();
        while (resultSet.next()) {
            busDTOS.add(resultSet.getString(1));
        }
        return busDTOS;
    }

    public ArrayList<String> checkAvailability() throws SQLException {
        String sql = "SELECT bus_reg_number FROM buses WHERE status='Available'";
        ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();

        ArrayList<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }
    public ArrayList<BusDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM buses";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

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
