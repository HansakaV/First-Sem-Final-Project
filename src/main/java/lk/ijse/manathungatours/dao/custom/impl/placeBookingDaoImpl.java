package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.dto.PlaceBookingDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class placeBookingDaoImpl {
    public static boolean placeOrder(PlaceBookingDTO po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            bookingDaoImpl bookingDao = new bookingDaoImpl();
            boolean isOrderSaved = bookingDao.save(po.getBooking());
            if (isOrderSaved) {
                BookingDetailDaoImpl bookingDetailDao = new BookingDetailDaoImpl();
                boolean isOrderDetailSaved = bookingDetailDao.save(po.getOdList());
                if (isOrderDetailSaved) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
