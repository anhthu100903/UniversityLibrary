/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import connection.ConnectDB;
import java.sql.SQLException;
import java.util.List;
import DTO.PersonDTO;
import DTO.RoleDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
/**
 *
 * @author User
 */
public class PersonDAO {
    private ConnectDB connectDB;

    public PersonDAO() {
        this.connectDB =  new ConnectDB();
    }
    
    public boolean isPersonIdExist(String id) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM person WHERE id = ?";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean isPersonPhoneExist(String phone) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM person WHERE tel = ? AND isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, phone);
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }

    public boolean addPerson(PersonDTO person) throws SQLException {
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                ConnectDB.conn.setAutoCommit(false); 
                String query = "INSERT INTO Person (id, name, tel, address, schoolYear) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = connectDB.getConnection().prepareStatement(query);
                stmt.setString(1, person.getId());
                stmt.setString(2, person.getName());
                stmt.setString(3, person.getTel());
                stmt.setString(4, person.getAddress());
                stmt.setString(5, person.getSchoolYear());
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0){
                  query = "INSERT INTO account (id, password, positionID) VALUES (?, ?, ?)";  
                  PreparedStatement stmt2 = connectDB.getConnection().prepareStatement(query);
                  stmt2.setString(1, person.getId());
                  stmt2.setString(2, "$2a$12$K3UiKrxSiYeApq.JruF.iu5w9EFq0ptPH845afQdEWk5edNrld5BS");
                  stmt2.setString(3, person.getRoleID().getId());
                  int rowsUpdated2 = stmt2.executeUpdate();
                  if (rowsUpdated2 > 0){
                      ConnectDB.conn.commit();
                        flag = true;
                  }    
                }
            }catch (SQLException e) {
                try {
                    ConnectDB.conn.rollback(); 
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
                e.printStackTrace();
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }

    public boolean updatePerson(PersonDTO person) throws SQLException {
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String query = "UPDATE Person SET name = ?, tel = ?, address = ?, schoolYear = ? WHERE id = ?";
                PreparedStatement stmt = connectDB.getConnection().prepareStatement(query);
                stmt.setString(1, person.getName());
                stmt.setString(2, person.getTel());
                stmt.setString(3, person.getAddress());
                stmt.setString(4, person.getSchoolYear());
                stmt.setString(5, person.getId());
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0){
                  flag = true;   
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean updateStaff(PersonDTO person) throws SQLException {
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String query = "UPDATE person JOIN account ON person.id = account.id SET person.name = ?, tel = ?, address = ?, positionID = ? WHERE person.id = ?";
                PreparedStatement stmt = connectDB.getConnection().prepareStatement(query);
                stmt.setString(1, person.getName());
                stmt.setString(2, person.getTel());
                stmt.setString(3, person.getAddress());
                stmt.setString(4, person.getRoleID().getId());
                stmt.setString(5, person.getId());
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0){
                  flag = true;   
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean deletePerson(String id) throws SQLException {
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String query = "UPDATE Person SET isActive = 0 WHERE id = ?";
                PreparedStatement stmt = connectDB.getConnection().prepareStatement(query);
                stmt.setString(1, id);
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0){
                  flag = true;   
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public List<PersonDTO> searchAllPerson(String searchText, Vector<String> role, boolean isReader) throws SQLException {
    List<PersonDTO> readerList = new ArrayList<>();
    try {
        connectDB.connect();

        // If no roles are provided, assign default roles based on isReader
        if (role == null || role.isEmpty()) {
            role = new Vector<>();
            if(isReader){
                role.add("SV");
                role.add("GV");
            }else{
                role.add("QL");
                role.add("TK");
                role.add("TT");
            }  
        }

        StringBuilder rolePlaceholders = new StringBuilder();
        for (int i = 0; i < role.size(); i++) {
            if (i > 0) {
                rolePlaceholders.append(", ");
            }
            rolePlaceholders.append("?");
        }

        String query = "SELECT person.*, account.positionID FROM person " +
                       "INNER JOIN account ON person.id = account.id " +
                       "WHERE person.isActive = 1 AND account.positionID IN (" + rolePlaceholders + ")";

        if (searchText != null && !searchText.isEmpty()) {
            query += " AND (person.name LIKE ? OR person.tel LIKE ?)";
        }

        System.out.println(query);
        
        try (PreparedStatement pstmt = connectDB.getConnection().prepareStatement(query)) {
            // Set role parameters
            int index = 1;
            for (String roleValue : role) {
                pstmt.setString(index++, roleValue);
            }

            // Set search text parameters if applicable
            if (searchText != null && !searchText.isEmpty()) {
                String searchPattern = "%" + searchText + "%";
                pstmt.setString(index++, searchPattern);
                pstmt.setString(index++, searchPattern);
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                RoleDTO roleDTO = new RoleDTO(rs.getString("positionID"), null);
                readerList.add(new PersonDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("tel"),
                    rs.getString("address"),
                    rs.getString("schoolYear"),
                    roleDTO
                ));
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.disconnect();
        }

        return readerList;
    }

    
    public ArrayList<PersonDTO> searchPerson(String keyword) throws SQLException {
        connectDB.connect();
        String query = "SELECT * FROM Person WHERE name LIKE ? OR tel LIKE ?";
        PreparedStatement stmt = connectDB.getConnection().prepareStatement(query);
        stmt.setString(1, "%" + keyword + "%");
        stmt.setString(2, "%" + keyword + "%");
        ResultSet rs = stmt.executeQuery();

        ArrayList<PersonDTO> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new PersonDTO(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("tel"),
                rs.getString("address"),
                rs.getString("schoolYear")
            ));
        }
        return result;
    }
    public List<PersonDTO> getAllStaff() throws SQLException {
        connectDB.connect();
//        String query = "SELECT * FROM Person WHERE isActive = 1";
        String query = "SELECT person.*, account.positionID, role.name FROM person\n" +
                            "JOIN account ON person.id = account.id\n" +
                            "JOIN role ON account.positionID = role.id\n" +
                            "WHERE account.positionID IN ('QL', 'TT', 'TK')";
        Statement stmt = connectDB.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<PersonDTO> staffList = new ArrayList<>();
        while (rs.next()) {
            RoleDTO roleDTO = new RoleDTO(rs.getString("positionID"), rs.getString("role.name"));
            staffList.add(new PersonDTO(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("tel"),
                rs.getString("address"),
                rs.getString("schoolYear"),
                roleDTO
            ));
        }
        return staffList;
    }
    
    public List<PersonDTO> getAllReaders() throws SQLException  {
        List<PersonDTO> readerList = new ArrayList<>();
        try {
            connectDB.connect();
            String query = "SELECT person.*, account.positionID FROM person\n" +
                            "INNER JOIN account ON person.id = account.id\n" +
                            "WHERE account.positionID IN ('SV', 'GV') AND person.isActive = 1";
            Statement stmt = connectDB.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                RoleDTO roleDTO = new RoleDTO(rs.getString("positionID"), null);
                readerList.add(new PersonDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("tel"),
                    rs.getString("address"),
                    rs.getString("schoolYear"),
                    roleDTO
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.disconnect();
        }
        return readerList;
    }
    
    public PersonDTO getPersonById(String id) throws SQLException {
        try {
            connectDB.connect();
            PreparedStatement stmt = connectDB.getConnection().prepareStatement("SELECT * FROM person WHERE id = ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new PersonDTO(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("tel"),
                rs.getString("address"),
                rs.getString("schoolYear")
            );
            } else
                return null;
        } catch (SQLException e) {
          throw e;
        }
    }
    
    public List<PersonDTO> searchReadersWithRole(String keyword, boolean isSinhVienChecked, boolean isGiangVienChecked) {
        List<PersonDTO> listReader = new ArrayList<>();
        String sql = "SELECT * FROM Person WHERE (id LIKE ? OR name LIKE ? OR tel LIKE ?)"; // Truy vấn cơ bản

        // Điều kiện để lọc theo vai trò
        if (isSinhVienChecked && !isGiangVienChecked) {
            sql += " AND roleId = 'SV'"; // Chỉ tìm Sinh viên
        } else if (isGiangVienChecked && !isSinhVienChecked) {
            sql += " AND roleId = 'GV'"; // Chỉ tìm Giảng viên
        }

        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%"; // Tìm kiếm với LIKE
            stmt.setString(1, searchKeyword);
            stmt.setString(2, searchKeyword);
            stmt.setString(3, searchKeyword);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String address = rs.getString("address");
                String schoolYear = rs.getString("schoolYear");
                RoleDTO role = new RoleDTO(rs.getString("roleId"), rs.getString("roleName"));
                PersonDTO person = new PersonDTO(id, name, tel, address, schoolYear, role);
                listReader.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listReader;
    }


}

