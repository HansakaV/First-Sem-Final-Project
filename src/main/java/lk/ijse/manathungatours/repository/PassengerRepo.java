package lk.ijse.manathungatours.repository;

import lk.ijse.manathungatours.db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerRepo {
   /* public static boolean save(Passenger passenger) throws SQLException {
        String sql = "INSERT INTO passengers VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, passenger.getId());
        pstm.setObject(2,passenger.getName());
        pstm.setObject(3, passenger.getAddress());
        pstm.setObject(4, passenger.getTel());

        return pstm.executeUpdate() > 0;
    }*/

   /* public static Passenger searchById(String id) throws SQLException {
        String sql = "SELECT * FROM passengers WHERE  passenger_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String Pid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            Passenger passenger = new Passenger(Pid, name, address, tel);

            return  passenger;
        }

        return null;
    }*/

   /* public static boolean update(Passenger passenger) throws SQLException {
        String sql = "UPDATE passengers SET name = ?, address = ?, tel = ? WHERE  passenger_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, passenger.getId());
        pstm.setObject(2,passenger.getName());
        pstm.setObject(3, passenger.getAddress());
        pstm.setObject(4, passenger.getTel());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM passengers WHERE  passenger_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }*/

   /* public static List<Passenger> getAll() throws SQLException {
        String sql = "SELECT * FROM passengers";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Passenger> passengerList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            Passenger passenger = new Passenger(id, name, address, tel);
           passengerList.add(passenger);
        }
        return passengerList;
    }*/

//    public static List<String> getIds() throws SQLException {
//        String sql = "SELECT passenger_id FROM passengers";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        List<String> idList = new ArrayList<>();
//
//        ResultSet resultSet = pstm.executeQuery();
//        while (resultSet.next()) {
//            String id = resultSet.getString(1);
//            idList.add(id);
//        }
//        return idList;
//    }
}
