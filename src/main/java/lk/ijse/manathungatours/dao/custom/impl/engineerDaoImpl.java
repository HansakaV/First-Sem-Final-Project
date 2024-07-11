package lk.ijse.manathungatours.dao.custom.impl;

import lk.ijse.manathungatours.dao.SQLUtill;
import lk.ijse.manathungatours.dao.custom.enginnerDAO;
import lk.ijse.manathungatours.dto.EngineerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class engineerDaoImpl implements enginnerDAO {
    public ArrayList<EngineerDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM enginners";
        ResultSet resultSet = SQLUtill.executeQuery(sql);
        ArrayList<EngineerDTO> engineerList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            engineerList.add(new EngineerDTO(id,name,address,tel));
        }
        return engineerList;

    }
    public boolean save(EngineerDTO dto) throws SQLException {
        String sql = "INSERT INTO enginners VALUES(?, ?, ?, ?)";
        return SQLUtill.executeUpdate(sql,dto.getId(),dto.getName(),dto.getAddress(),dto.getTel());
    }
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM enginners WHERE  enginner_id = ?";
        return SQLUtill.executeUpdate(sql,id);
    }
    public boolean update(EngineerDTO engineer) throws SQLException {
        String sql = "UPDATE conductors SET name = ?, address = ?, tel = ? WHERE  enginner_id = ?";
        return SQLUtill.executeUpdate(sql,engineer.getId(),engineer.getName(),engineer.getAddress(),engineer.getTel());
    }
    public ArrayList<EngineerDTO> search(String id) throws SQLException {
        String sql = "SELECT * FROM enginners WHERE  enginner_id = ?";
        ResultSet resultSet = SQLUtill.executeQuery(sql);
        ArrayList<EngineerDTO> engineerDTOS = new ArrayList<>();
        if (resultSet.next()) {
            String Eid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            engineerDTOS.add(new EngineerDTO(Eid,name,address,tel));
        }
        return  engineerDTOS;
    }
    public  ArrayList<String> getIds() throws SQLException {
       String sql = "SELECT enginner_id FROM enginners";
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
