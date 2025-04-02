/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.BorrowBUS;
import DTO.BorrowDTO;
import DTO.BorrowDetailDTO;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author anhthu
 */
public class BorrowDetailDAO {

    ConnectDB connectDB;
    BorrowBUS borrowBus;
    
    public BorrowDetailDAO()throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
        this.borrowBus = new BorrowBUS();
    }

    public boolean add(BorrowDetailDTO borrowDetail) throws SQLException {
        int rowsAffected = 0;
        String query = "INSERT INTO `borrowdetail`(`borrowID`, `ISBN`, `quantity`, `description`) VALUES (?, ?, ?, ?)";
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(query);
                stmt.setInt(1, borrowDetail.getBorrowID());
                stmt.setString(2, borrowDetail.getISBN());
                stmt.setInt(3, borrowDetail.getQuantity());
                stmt.setString(4, borrowDetail.getDescription());

                rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                connectDB.disconnect();
            }
        }
        return false;
    }

    //cập nhật số lượng sách trong 1 detail
    
    public boolean updateQuantity(BorrowDetailDTO detailDTO) throws SQLException {
        String query = "UPDATE `borrowdetail` SET `quantity` = ? WHERE ISBN = ? AND borrowID = ?";
        connectDB.connect();
        if (ConnectDB.conn != null){
            try {
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(query);
                stmt.setInt(1, detailDTO.getQuantity());
                stmt.setString(2, detailDTO.getISBN());
                stmt.setInt(3, detailDTO.getBorrowID());

                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connectDB.disconnect();
            }
        }
        return false;
    }

    //cập nhật mô tả trong 1 detail
    //cập nhật mô tả trong 1 detail
    public boolean updateDesciption(int borrowID, String ISBN, String description) throws SQLException {
        String query = "UPDATE `borrowdetail` SET `description` = ? WHERE ISBN = ? AND borrowID = ?";
        connectDB.connect();
        if (ConnectDB.conn != null){
            try {
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(query);
                stmt.setString(1, description);
                stmt.setString(2, ISBN);
                stmt.setInt(3, borrowID);

                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connectDB.disconnect();
            }
        }
        return false;
    }


    //cập nhật mô tả trong 1 detail
    public boolean updateLostAndBroke(int borrowID, String ISBN, int lost, int broke) throws SQLException {
        String query = "UPDATE `borrowdetail` SET `lost`= ?, `broke`= ? WHERE ISBN = ? AND borrowID = ?";
        connectDB.connect();
        if (ConnectDB.conn != null){
            try {
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(query);
                stmt.setInt(1, lost);
                stmt.setInt(2, broke);
                stmt.setString(3, ISBN);
                stmt.setInt(4, borrowID);

                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connectDB.disconnect();
            }
        }
        return false;
    }

    public List<BorrowDetailDTO> selectAll(int borrowID) throws SQLException {
        List<BorrowDetailDTO> listBorrowDetail = new ArrayList<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try {
                String sql = "select* from borrowdetail where borrowID = ?";
                PreparedStatement ps = ConnectDB.conn.prepareStatement(sql);
                ps.setInt(1, borrowID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    BorrowDetailDTO borrowDetail = new BorrowDetailDTO();
                    borrowDetail.setISBN(rs.getString("ISBN"));
                    borrowDetail.setQuantity(rs.getInt("quantity"));
                    borrowDetail.setDescription(rs.getString("description"));
                    borrowDetail.setLost(rs.getInt("lost"));
                    borrowDetail.setBroke(rs.getInt("broke"));

                    listBorrowDetail.add(borrowDetail);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                connectDB.disconnect();
            }
        }
        return listBorrowDetail;
    }

    //kiểm tra borrowDetail đã tồn tại chưa
    //kiểm tra borrowDetail đã tồn tại chưa
    public boolean checkBorrowDetailExistence(String ISBN, int borrowID) throws SQLException {
        String query = "SELECT COUNT(*) FROM `borrowdetail` WHERE borrowdetail.ISBN = ? AND borrowID = ?";
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(query);
                stmt.setString(1, ISBN);
                stmt.setInt(2, borrowID);

                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next() && rs.getInt(1) > 0;
                }
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return false;
    }

    public Vector<BorrowDetailDTO> getBorrowDetails(int borrowID) throws SQLException {
        Vector<BorrowDetailDTO> borrowDetails = new Vector<>();
        String sql = "SELECT * FROM borrowdetail WHERE borrowID = ?";
        connectDB.connect();
        if (ConnectDB.conn != null){
        try{
            PreparedStatement statement = ConnectDB.conn.prepareStatement(sql);
            statement.setInt(1, borrowID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                BorrowDetailDTO borrowDetail = new BorrowDetailDTO();
                borrowDetail.setBorrowID(resultSet.getInt("borrowID"));
                borrowDetail.setQuantity(resultSet.getInt("quantity"));
                borrowDetail.setDescription(resultSet.getString("description"));
                borrowDetail.setLost(resultSet.getInt("lost"));
                borrowDetail.setBroke(resultSet.getInt("broke"));
                
                String ISBN = resultSet.getString("ISBN");
                borrowDetail.setISBN(ISBN);
                
                String bookName = borrowBus.getBookNameByISBN(ISBN);
                borrowDetail.setBookName(bookName);
                
                borrowDetails.add(borrowDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectDB.disconnect();
        }
        }
        return borrowDetails;
    }

}
