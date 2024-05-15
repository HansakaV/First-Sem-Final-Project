package lk.ijse.manathungatours.repository;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.model.Conductor;
import lk.ijse.manathungatours.model.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverRepo {
    public static boolean save(Driver driver) throws SQLException {
        String sql = "INSERT INTO drivers VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, driver.getId());
        pstm.setObject(2,driver.getName());
        pstm.setObject(3, driver.getAddress());
        pstm.setObject(4, driver.getTel());

        return pstm.executeUpdate() > 0;
    }

    public static Driver searchById(String id) throws SQLException {
        String sql = "SELECT * FROM drivers WHERE  driver_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String Did = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            Driver driver = new Driver(Did, name, address, tel);

            return driver ;
        }

        return null;
    }

    public static boolean update(Driver driver) throws SQLException {
        String sql = "UPDATE conductors SET name = ?, address = ?, tel = ? WHERE  driver_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, driver.getId());
        pstm.setObject(2,driver.getName());
        pstm.setObject(3, driver.getAddress());
        pstm.setObject(4, driver.getTel());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM drivers WHERE  driver_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Driver> getAll() throws SQLException {
        String sql = "SELECT * FROM drivers";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Driver> driverList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            Driver driver = new Driver(id, name, address, tel);
            driverList.add(driver);
        }
        return driverList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT driver_id FROM drivers";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
}
