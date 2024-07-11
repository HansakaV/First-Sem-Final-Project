package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.dao.SQLUtill;
import lk.ijse.manathungatours.dao.custom.conductorDAO;
import lk.ijse.manathungatours.dto.ConductorDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class conductorDaoImpl implements conductorDAO {
    public ArrayList<ConductorDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM conductors";
        ResultSet resultSet = SQLUtill.executeQuery(sql);
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
        return SQLUtill.executeUpdate(sql,dto.getId(),dto.getName(),dto.getAddress(),dto.getTel());
    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM conductors WHERE  conductor_id = ?";
        return SQLUtill.executeUpdate(sql,id);
    }

    public boolean update(ConductorDTO dto) throws SQLException {
        String sql = "UPDATE conductors SET name = ?, address = ?, tel = ? WHERE  conductor_id = ?";
        return SQLUtill.executeUpdate(sql,dto.getId(),dto.getName(),dto.getAddress(),dto.getTel());
    }

    public ArrayList<ConductorDTO> search(String id) throws SQLException {
        String sql = "SELECT * FROM conductors WHERE  conductor_id = ?";
        ResultSet resultSet = SQLUtill.executeQuery(sql,id);

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
        ResultSet resultSet = SQLUtill.executeQuery(sql);
        ArrayList<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    @Override
    public String getCurrentId() throws SQLException {
        return null;
    }

}
