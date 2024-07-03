package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.db.DbConnection;
import lk.ijse.manathungatours.model.Conductor;
import lk.ijse.manathungatours.model.ConductorDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class conductorDaoImpl {
    public ArrayList<ConductorDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM conductors";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<ConductorDTO> conductorList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            conductorList.add(new ConductorDTO(id, name, address, tel));
        }
        return conductorList;

    }

    public boolean save(ConductorDTO dto) throws SQLException {
        String sql = "INSERT INTO conductors VALUES(?, ?, ?, ?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getId());
        pstm.setObject(2,dto.getName());
        pstm.setObject(3, dto.getAddress());
        pstm.setObject(4, dto.getTel());

        return pstm.executeUpdate() > 0;

    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM conductors WHERE  conductor_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;

    }

    public boolean update(ConductorDTO dto) throws SQLException {
        String sql = "UPDATE conductors SET name = ?, address = ?, tel = ? WHERE  conductor_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, dto.getId());
        pstm.setObject(2, dto.getName());
        pstm.setObject(3, dto.getAddress());
        pstm.setObject(4, dto.getTel());

        return pstm.executeUpdate() > 0;

    }

    public ArrayList<ConductorDTO> search(String id) throws SQLException {
        String sql = "SELECT * FROM conductors WHERE  conductor_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<ConductorDTO> conductorDTOS = new ArrayList<>();
        if (resultSet.next()) {
            String Cid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            conductorDTOS.add(new ConductorDTO(Cid, name, address, tel));
        }
        return conductorDTOS;
    }
    public ArrayList<String> getIds() throws SQLException {
        String sql = "SELECT conductor_id FROM conductors";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ArrayList<String> idList = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

}
