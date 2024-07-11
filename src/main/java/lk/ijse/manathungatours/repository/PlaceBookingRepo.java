package lk.ijse.manathungatours.repository;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.dto.PlaceBookingDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceBookingRepo {
//    public static boolean placeOrder(PlaceBookingDTO po) throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        connection.setAutoCommit(false);
//
//        try {
//            boolean isOrderSaved = BookingRepo.save(po.getBooking());
//            if (isOrderSaved) {
//                boolean isOrderDetailSaved = BookingDetailRepo.save(po.getOdList());
//                if (isOrderDetailSaved) {
//                    connection.commit();
//                    return true;
//                }
//            }
//            connection.rollback();
//            return false;
//        } catch (Exception e) {
//            connection.rollback();
//            return false;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }
}
