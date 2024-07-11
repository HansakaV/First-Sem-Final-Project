package lk.ijse.manathungatours.repository;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.dto.BookingDetailDTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookingDetailRepo {
//    public static boolean save(List<BookingDetailDTO> odList) throws SQLException {
//        for (BookingDetailDTO od : odList) {
//            boolean isSaved = save(od);
//            if(!isSaved) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean save(BookingDetailDTO od) throws SQLException {
//
//        System.out.println(od.getRegNumber());
//        String sql = "INSERT INTO booking_detail VALUES(?, ?, ?, ?,?)";
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        pstm.setString(1, od.getBookingId());
//        pstm.setString(2, od.getRegNumber());
//        pstm.setString(3, od.getCost());
//        pstm.setString(4, od.getDescription());
//        pstm.setDate(5, (Date) od.getDate());
//
//
//
//        return pstm.executeUpdate() > 0;    //false ->  |
//    }
}
