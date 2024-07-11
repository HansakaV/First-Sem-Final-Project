package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.dao.SQLUtill;
import lk.ijse.manathungatours.dao.custom.routeDAO;
import lk.ijse.manathungatours.dto.RouteDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class routeDaoImpl implements routeDAO {
    public ArrayList<RouteDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM routes";
        ResultSet resultSet = SQLUtill.executeQuery(sql);
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
        return SQLUtill.executeUpdate(sql,routeDTO.getRoute(),routeDTO.getBusReg(),routeDTO.getDriverId(),routeDTO.getConductorId());
    }
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM route WHERE  route = ?";
      return SQLUtill.executeUpdate(sql,id);
    }

    @Override
    public ArrayList<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException {
        return null;
    }

    public boolean update(RouteDTO routeDTO) throws SQLException {
        String sql = "UPDATE routes SET bus_reg_number = ?, conductor_id = ?, driver_id = ? WHERE  route = ?";
        return  SQLUtill.executeUpdate(sql,routeDTO.getRoute(),routeDTO.getBusReg(),routeDTO.getDriverId(),routeDTO.getConductorId());
    }
    public ArrayList<RouteDTO> search(String id) throws SQLException {
        String sql = "SELECT * FROM routes WHERE  route = ?";
        ResultSet resultSet = SQLUtill.executeQuery(sql,id);
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
