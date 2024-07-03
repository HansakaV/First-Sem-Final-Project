package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.model.Bus;
import lk.ijse.manathungatours.model.BusDTO;
import lk.ijse.manathungatours.model.PassengerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class customerDaoImpl {
    public ArrayList<PassengerDTO> getAllPassengers() throws SQLException {
        String sql = "SELECT * FROM passengers";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
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

    public boolean save(PassengerDTO dto) throws SQLException {
        String sql = "INSERT INTO passengers VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getId());
        pstm.setObject(2, dto.getName());
        pstm.setObject(3, dto.getAddress());
        pstm.setObject(4, dto.getTel());
        return pstm.executeUpdate() > 0;

    }

    public ArrayList<PassengerDTO> searchById(String id) throws SQLException {
        String sql = "SELECT * FROM passengers WHERE  passenger_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();
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
    public boolean update(PassengerDTO dto) throws SQLException {
        String sql = "UPDATE passengers SET name = ?, address = ?, tel = ? WHERE  passenger_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getId());
        pstm.setObject(2,dto.getName());
        pstm.setObject(3, dto.getAddress());
        pstm.setObject(4, dto.getTel());

        return pstm.executeUpdate() > 0;
    }
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM passengers WHERE  passenger_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public ArrayList<BusDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM buses";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<BusDTO> busDTOS = new ArrayList<>();

        while (resultSet.next()) {
            String regNum = resultSet.getString(1);
            String seats = resultSet.getString(2);
            String status = resultSet.getString(3);
            String service = resultSet.getString(4);
            busDTOS.add(new BusDTO(regNum,seats,status,service));

        }
        return busDTOS;
    }
}
