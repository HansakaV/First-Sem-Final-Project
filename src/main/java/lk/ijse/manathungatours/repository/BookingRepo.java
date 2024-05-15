package lk.ijse.manathungatours.repository;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.model.Booking;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRepo {
    public static String getCurrentId() throws SQLException {
        String sql = "SELECT booking_id FROM bookings ORDER BY booking_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String bookingId = resultSet.getString(1);
            return bookingId;
        }
        return null;
    }

    public static boolean save(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings VALUES(?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, booking.getBookingId());
        pstm.setDate(2, (Date) booking.getDate());
        pstm.setString(3, booking.getPassengerId());

        boolean b = pstm.executeUpdate() > 0;

        return b;
    }
}
