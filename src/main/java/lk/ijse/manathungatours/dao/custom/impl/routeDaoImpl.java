package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.model.Route;
import lk.ijse.manathungatours.model.RouteDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class routeDaoImpl {
    public ArrayList<RouteDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM routes";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<RouteDTO> routeList = new ArrayList<>();
        while (resultSet.next()) {
            String route = resultSet.getString(1);
            String regNumber = resultSet.getString(2);
            String conductorId = resultSet.getString(3);
            String driverId = resultSet.getString(4);

            routeList.add(new RouteDTO(route,regNumber,conductorId,driverId));
        }
        return routeList;
    }

    public boolean save(RouteDTO routeDTO) throws SQLException {
        String sql = "INSERT INTO routes VALUES(?, ?, ?, ?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, routeDTO.getRoute());
        pstm.setObject(2,routeDTO.getBusReg());
        pstm.setObject(3, routeDTO.getDriverId());
        pstm.setObject(4, routeDTO.getConductorId());

        return pstm.executeUpdate() > 0;

    }
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM route WHERE  route = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;

    }
    public boolean update(RouteDTO routeDTO) throws SQLException {
        String sql = "UPDATE routes SET bus_reg_number = ?, conductor_id = ?, driver_id = ? WHERE  route = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, routeDTO.getRoute());
        pstm.setObject(2,routeDTO.getBusReg());
        pstm.setObject(3, routeDTO.getDriverId());
        pstm.setObject(4, routeDTO.getConductorId());

        return pstm.executeUpdate() > 0;

    }
    public ArrayList<RouteDTO> search(String id) throws SQLException {
        String sql = "SELECT * FROM routes WHERE  route = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<RouteDTO> routeDTOS = new ArrayList<>();
        if (resultSet.next()) {
            String route1 = resultSet.getString(1);
            String regNumber = resultSet.getString(2);
            String conId = resultSet.getString(3);
            String driverId = resultSet.getString(4);

            routeDTOS.add(new RouteDTO(route1,regNumber,conId,driverId));
        }
        return routeDTOS;
    }
}
