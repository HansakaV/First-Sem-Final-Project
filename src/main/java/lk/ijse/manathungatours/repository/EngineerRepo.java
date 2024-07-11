package lk.ijse.manathungatours.repository;

public class EngineerRepo {
//    public static boolean save(Engineer engineer) throws SQLException {
//        String sql = "INSERT INTO enginners VALUES(?, ?, ?, ?)";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, engineer.getId());
//        pstm.setObject(2,engineer.getName());
//        pstm.setObject(3, engineer.getAddress());
//        pstm.setObject(4, engineer.getTel());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static Engineer searchById(String id) throws SQLException {
//        String sql = "SELECT * FROM enginners WHERE  enginner_id = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, id);
//
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()) {
//            String Eid = resultSet.getString(1);
//            String name = resultSet.getString(2);
//            String address = resultSet.getString(3);
//            String tel = resultSet.getString(4);
//
//            Engineer engineer = new Engineer(Eid, name, address, tel);
//
//            return engineer ;
//        }
//
//        return null;
//    }
//
//    public static boolean update(Engineer engineer) throws SQLException {
//        String sql = "UPDATE conductors SET name = ?, address = ?, tel = ? WHERE  enginner_id = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, engineer.getId());
//        pstm.setObject(2,engineer.getName());
//        pstm.setObject(3, engineer.getAddress());
//        pstm.setObject(4, engineer.getTel());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static boolean delete(String id) throws SQLException {
//        String sql = "DELETE FROM enginners WHERE  enginner_id = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, id);
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static List<Engineer> getAll() throws SQLException {
//        String sql = "SELECT * FROM enginners";
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery();
//
//        List<Engineer> engineerList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            String id = resultSet.getString(1);
//            String name = resultSet.getString(2);
//            String address = resultSet.getString(3);
//            String tel = resultSet.getString(4);
//
//            Engineer engineer = new Engineer(id, name, address, tel);
//            engineerList.add(engineer);
//        }
//        return engineerList;
//    }
//
//    public static List<String> getIds() throws SQLException {
//        String sql = "SELECT enginner_id FROM enginners";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        List<String> idList = new ArrayList<>();
//
//        ResultSet resultSet = pstm.executeQuery();
//        while (resultSet.next()) {
//            String id = resultSet.getString(1);
//            idList.add(id);
//        }
//        return idList;
//    }
}
