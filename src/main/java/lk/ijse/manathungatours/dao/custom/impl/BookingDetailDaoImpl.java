package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.dao.CrudDAO;
import lk.ijse.manathungatours.dao.SQLUtill;
import lk.ijse.manathungatours.dto.BookingDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDetailDaoImpl implements CrudDAO <BookingDetailDTO,String>{
    public   boolean save(List<BookingDetailDTO> odList) throws SQLException {
        for (BookingDetailDTO od : odList) {
            boolean isSaved = save(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<BookingDetailDTO> getAll() throws SQLException {
        return null;
    }

    public   boolean save(BookingDetailDTO od) throws SQLException {
        String sql = "INSERT INTO booking_detail VALUES(?, ?, ?, ?,?)";
        return SQLUtill.executeUpdate(sql,od.getBookingId(),od.getRegNumber(),od.getDate(),od.getDescription(),od.getCost());
    }

    @Override
    public ArrayList<BookingDetailDTO> search(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean update(BookingDetailDTO dto) throws SQLException {
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

    @Override
    public String getCurrentId() throws SQLException {
        return null;
    }
}
