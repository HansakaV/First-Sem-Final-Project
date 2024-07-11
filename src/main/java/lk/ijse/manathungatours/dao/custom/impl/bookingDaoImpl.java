package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.dao.CrudDAO;
import lk.ijse.manathungatours.dao.SQLUtill;
import lk.ijse.manathungatours.dto.BookingDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class bookingDaoImpl implements CrudDAO<BookingDTO,String> {
    public  String getCurrentId() throws SQLException {
        String sql = "SELECT booking_id FROM bookings ORDER BY booking_id DESC LIMIT 1";
        ResultSet resultSet = SQLUtill.executeQuery(sql);
        if(resultSet.next()) {
            String bookingId = resultSet.getString(1);
            return bookingId;
        }
        return null;
    }

    @Override
    public ArrayList<BookingDTO> getAll() throws SQLException {
        return null;
    }

    public  boolean save(BookingDTO booking) throws SQLException {
        String sql = "INSERT INTO bookings VALUES(?, ?, ?)";
        return SQLUtill.executeUpdate(sql,booking.getBookingId(),booking.getDate(),booking.getPassengerId(),booking.getDesc());

//        boolean b = pstm.executeUpdate() > 0;
//        return b;
    }

    @Override
    public ArrayList<BookingDTO> search(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean update(BookingDTO dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException {
        return null;
    }


}
