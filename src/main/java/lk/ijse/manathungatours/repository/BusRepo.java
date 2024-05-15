package lk.ijse.manathungatours.repository;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.model.Bus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusRepo {
    public static boolean save(Bus bus) throws SQLException {
        String sql = "INSERT INTO buses VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, bus.getRegNumber());
        pstm.setObject(2, bus.getSeats());
        pstm.setObject(3, bus.getStatus());
        pstm.setObject(4, bus.getService());

        return pstm.executeUpdate() > 0;
    }

    public static Bus searchById(String id) throws SQLException {
        String sql = "SELECT * FROM buses WHERE bus_reg_number = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String regNum = resultSet.getString(1);
            String seats = resultSet.getString(2);
            String status = resultSet.getString(3);
            String service = resultSet.getString(4);

            Bus bus = new Bus(regNum, seats, status, service);

            return bus;
        }

        return null;
    }

    public static boolean update(Bus bus) throws SQLException {
        String sql = "UPDATE buses SET no_of_seats = ?, Status = ?, Service = ? WHERE bus_reg_number = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, bus.getRegNumber());
        pstm.setObject(2, bus.getSeats());
        pstm.setObject(3, bus.getStatus());
        pstm.setObject(4, bus.getService());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM buses WHERE bus_reg_number = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Bus> getAll() throws SQLException {
        String sql = "SELECT * FROM buses";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Bus> busList = new ArrayList<>();

        while (resultSet.next()) {
            String regNum = resultSet.getString(1);
            String seats = resultSet.getString(2);
            String status = resultSet.getString(3);
            String service = resultSet.getString(4);

            Bus bus = new Bus(regNum, seats, status, service);
            busList.add(bus);
        }
        return busList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT bus_reg_number FROM buses";
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


    public static List<String> getCodes() throws SQLException {
        String sql = "SELECT bus_reg_number FROM buses";
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection()
                .prepareStatement(sql)
                .executeQuery();

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

    public static List<String> getAvailbleBuses() throws SQLException {
        String sql = "SELECT bus_reg_number FROM buses WHERE status='Available'";
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection()
                .prepareStatement(sql)
                .executeQuery();

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

}
