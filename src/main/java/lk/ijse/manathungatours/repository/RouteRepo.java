package lk.ijse.manathungatours.repository;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.model.Driver;
import lk.ijse.manathungatours.model.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteRepo {
//    public static boolean save(Route route) throws SQLException {
//        String sql = "INSERT INTO routes VALUES(?, ?, ?, ?)";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, route.getRoute());
//        pstm.setObject(2,route.getBusReg());
//        pstm.setObject(3, route.getDriverId());
//        pstm.setObject(4, route.getConductorId());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static Route searchById(String id) throws SQLException {
//        String sql = "SELECT * FROM routes WHERE  route = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, id);
//
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()) {
//            String route1 = resultSet.getString(1);
//            String regNumber = resultSet.getString(2);
//            String conId = resultSet.getString(3);
//            String driverId = resultSet.getString(4);
//
//            Route route = new Route(route1, regNumber, conId, driverId);
//
//            return route ;
//        }
//
//        return null;
//    }
//
//    public static boolean update(Route route) throws SQLException {
//        String sql = "UPDATE routes SET bus_reg_number = ?, conductor_id = ?, driver_id = ? WHERE  route = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, route.getRoute());
//        pstm.setObject(2,route.getBusReg());
//        pstm.setObject(3, route.getDriverId());
//        pstm.setObject(4, route.getConductorId());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static boolean delete(String id) throws SQLException {
//        String sql = "DELETE FROM route WHERE  route = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, id);
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static List<Route> getAll() throws SQLException {
//        String sql = "SELECT * FROM routes";
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery();
//
//        List<Route> routeList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            String route = resultSet.getString(1);
//            String regNumber = resultSet.getString(2);
//            String conductorId = resultSet.getString(3);
//            String driverId = resultSet.getString(4);
//
//            Route route1 = new Route(route, regNumber, conductorId,driverId);
//            routeList.add(route1);
//        }
//        return routeList;
//    }
//

}
