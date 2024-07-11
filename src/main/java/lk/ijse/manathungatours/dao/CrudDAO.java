package lk.ijse.manathungatours.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> {
    public ArrayList<T> getAll() throws SQLException ;
    public boolean save(T dto) throws SQLException ;
    public ArrayList<T> search(ID id) throws SQLException ;
    public boolean update(T dto) throws SQLException ;
    public boolean delete(ID id) throws SQLException ;
    public  ArrayList<String> getIds() throws SQLException ;
    public  String getCurrentId() throws SQLException;
}
