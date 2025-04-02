/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.BorrowDetailBUS;
import DTO.BorrowDTO;
import DTO.BorrowDetailDTO;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class BorrowDAO {

    ConnectDB connectDB;
    
    public BorrowDAO()throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
    }

    //lấy danh sách các phiếu mượn
    public List<BorrowDTO> selectAll() throws SQLException{
        List<BorrowDTO> listBorrow = new ArrayList<>();
        String sql = "select * from borrowing";

        connectDB.connect();
        if (ConnectDB.conn != null){
        try{ 
             PreparedStatement ps = ConnectDB.conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BorrowDTO borrow = new BorrowDTO();
                borrow.setId(Integer.parseInt(rs.getString("id")));
                String readerId = rs.getString("readerID");
                borrow.setReaderID(readerId);
                borrow.setReaderName(getPersonName(readerId));
                borrow.setStaffID(rs.getString("borrowStaffID"));

                // Parse dates
                String borrowDateStr = rs.getString("borrowDate");
                if (borrowDateStr != null && !borrowDateStr.isEmpty()) {
                    borrow.setBorrowDate(LocalDate.parse(borrowDateStr, DateTimeFormatter.ISO_LOCAL_DATE));
                }
                String dueDateStr = rs.getString("dueDate");
                if (dueDateStr != null && !dueDateStr.isEmpty()) {
                    borrow.setDueDate(LocalDate.parse(dueDateStr));
                }
                String returnDateStr = rs.getString("returnDate");
                if (returnDateStr != null && !returnDateStr.isEmpty()) {
                    borrow.setReturnDate(LocalDate.parse(returnDateStr));
                }

                borrow.setDelay("1".equals(rs.getString("delay")));
                borrow.setFine(Long.parseLong(rs.getString("fine")));
                borrow.setIsActive("1".equals(rs.getString("isActive")));

                listBorrow.add(borrow);
            }
        }catch(SQLException e){
        }finally {
            connectDB.disconnect();
        }
        }
        return listBorrow;
    }
    
    public List<BorrowDTO> selectByUserId(String id) throws SQLException{
        List<BorrowDTO> listBorrow = new ArrayList<>();
        String sql = "select * from borrowing where readerID = ?";

        connectDB.connect();
        if (ConnectDB.conn != null){
        try{ 
             PreparedStatement ps = ConnectDB.conn.prepareStatement(sql);
             ps.setString(1, id);
             ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BorrowDTO borrow = new BorrowDTO();
                borrow.setId(Integer.parseInt(rs.getString("id")));
                String readerId = rs.getString("readerID");
                borrow.setReaderID(readerId);
                borrow.setReaderName(getPersonName(readerId));
                String staffId = rs.getString("borrowStaffID");
                borrow.setStaffID(staffId);
                borrow.setStaffName(getPersonName(staffId));


                // Parse dates
                String borrowDateStr = rs.getString("borrowDate");
                if (borrowDateStr != null && !borrowDateStr.isEmpty()) {
                    borrow.setBorrowDate(LocalDate.parse(borrowDateStr, DateTimeFormatter.ISO_LOCAL_DATE));
                }
                String dueDateStr = rs.getString("dueDate");
                if (dueDateStr != null && !dueDateStr.isEmpty()) {
                    borrow.setDueDate(LocalDate.parse(dueDateStr));
                }
                String returnDateStr = rs.getString("returnDate");
                if (returnDateStr != null && !returnDateStr.isEmpty()) {
                    borrow.setReturnDate(LocalDate.parse(returnDateStr));
                }

                borrow.setDelay("1".equals(rs.getString("delay")));
                borrow.setFine(Long.parseLong(rs.getString("fine")));
                borrow.setIsActive("1".equals(rs.getString("isActive")));

                listBorrow.add(borrow);
            }
        }catch(SQLException e){
        }finally {
            connectDB.disconnect();
        }
        }
        return listBorrow;
    }

    public BorrowDTO selectABorrow(int id) throws SQLException {
        String sql = "SELECT * FROM borrowing WHERE id = ?";
        BorrowDTO borrow = new BorrowDTO();

        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    borrow.setId(rs.getInt("id"));
                    borrow.setReaderID(rs.getString("readerID"));
                    borrow.setReaderName(getPersonName(rs.getString("readerID"))); // Lấy tên người mượn

                    borrow.setStaffID(rs.getString("borrowStaffID"));
                    borrow.setStaffName(getPersonName(rs.getString("borrowStaffID"))); // Lấy tên nhân viên mượn

                    String borrowDateStr = rs.getString("borrowDate");
                    if (borrowDateStr != null && !borrowDateStr.isEmpty()) {
                        borrow.setBorrowDate(LocalDate.parse(borrowDateStr, DateTimeFormatter.ISO_LOCAL_DATE));
                    }

                    String dueDateStr = rs.getString("dueDate");
                    if (dueDateStr != null && !dueDateStr.isEmpty()) {
                        borrow.setDueDate(LocalDate.parse(dueDateStr));
                    }

                    String returnDateStr = rs.getString("returnDate");
                    if (returnDateStr != null && !returnDateStr.isEmpty()) {
                        borrow.setReturnDate(LocalDate.parse(returnDateStr));
                    }

                    borrow.setDelay("1".equals(rs.getString("delay")));
                    borrow.setFine(rs.getLong("fine"));
                    borrow.setIsActive("1".equals(rs.getString("isActive")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        } finally {
            connectDB.disconnect();
        }

        return borrow;
    }

    //lấy borrowID từ readerID
    public int getBorrowID(String readerID) throws SQLException {
        String query = "SELECT id FROM borrowing WHERE readerID = ?";
        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
            stmt.setString(1, readerID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return 0;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            connectDB.disconnect();
        }
    }

    //gia hạn phiếu mượn
    public boolean setDelay(int id, Date dueDate) throws SQLException{
        String query = "UPDATE `borrowing` SET `delay`= ?, `dueDate` = ? WHERE `id` = ?";
        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
            stmt.setInt(1, 1);
            stmt.setDate(2, dueDate);
            stmt.setInt(3, id);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connectDB.disconnect();
        }
    }

    //trả sách
    public boolean returnBook(int id, String staffID, double fine) throws SQLException{
        String query = "UPDATE `borrowing` SET `returnStaffID`= ?, `fine`= ?, `returnDate`= CURRENT_DATE, `isActive`= 0 WHERE `id` = ?";
        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
            stmt.setString(1, staffID);
            stmt.setDouble(2, fine);
            stmt.setInt(3, id);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connectDB.disconnect();
        }
    }

    //kiểm tra xem độc giả có đang mượn sách không
    public boolean checkReaderIsBorrowing(String readerID) throws SQLException {
        String query = "SELECT COUNT(*) FROM `borrowing` WHERE isActive = 1 and readerID = ?";

        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
            stmt.setString(1, readerID);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0; //nếu độc giả đang mượn sách return true
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            connectDB.disconnect();
        }
    }

    //kiểm tra xem độc giả đã gia hạn chưa
    public boolean checkReaderHasDelayed(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM `borrowing` WHERE delay = 1 and id = ?";

        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0; //nếu độc giả đã gia hạn return true
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            connectDB.disconnect();
        }
    }

    //lấy bookname từ ISBN
    public String getBookName(String ISBN) throws SQLException {
        String query = "SELECT book.name FROM book JOIN versionofbook ON book.id = versionofbook.bookID\n"
                + "WHERE versionofbook.ISBN = ?";
        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
            stmt.setString(1, ISBN);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    return null;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            connectDB.disconnect();
        }
    }

    //cộng (trừ) số lượng vào kho
    public boolean updateAvailable(String ISBN, int quantity) throws SQLException {
        String query = "UPDATE `versionofbook` SET `available` = `available` + ?  WHERE `ISBN` = ?";
        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setString(2, ISBN);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connectDB.disconnect();
        }
    }

    //kiểm tra xem có tồn tại mã ISBN không
    public boolean checkISBNExistence(String ISBN) throws SQLException {
        String query = "SELECT COUNT(*) FROM `versionofbook` WHERE ISBN = ?";

        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
            stmt.setString(1, ISBN);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            connectDB.disconnect();
        }
    }

    //kiểm tra sách còn trong kho không
    public boolean checkBooksInStock(String ISBN, int quantity) throws SQLException {
        String query = "SELECT COUNT(*) FROM `versionofbook` WHERE ISBN = ? and `available` >= ?";
        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
            stmt.setString(1, ISBN);
            stmt.setInt(2, quantity);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0; //nếu còn trong kho true
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            connectDB.disconnect();
        }
    }

    public double selectPrice(String ISBN) throws SQLException {
        String query = "SELECT price FROM `versionofbook` WHERE ISBN = ?";
        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
            stmt.setString(1, ISBN);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                } else {
                    return 0.0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            connectDB.disconnect();
        }
    }

    //lấy tên từ personID
    public String getPersonName(String id) throws SQLException {
        String query = "SELECT name FROM Person WHERE id = ?";
        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    return null;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            connectDB.disconnect();
        }
    }

    //kiểm tra xem độc giả có bị khóa không
    public boolean checkReaderIDIsLocked(String readerID) throws SQLException {
        String query = "SELECT COUNT(*) FROM `person` WHERE isActive = 0 and id = ?";
        connectDB.connect();
        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
            stmt.setString(1, readerID);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0; //nếu bị khóa return true
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
         throw e;
        } finally {
            connectDB.disconnect();
        }
    }
    
    public boolean AddBorrow(String readerID, String staffID, Date dueDate, List<BorrowDetailDTO> tempBorrowDetails ) throws SQLException {
        boolean flag = false;
        connectDB.connect();

        if (ConnectDB.conn != null) {
            PreparedStatement preparedStatement = null;
            try {
                ConnectDB.conn.setAutoCommit(false); 
                
                String sql = "INSERT INTO borrowing (readerID, borrowStaffID, dueDate) VALUES (?, ?, ?)";
                preparedStatement = ConnectDB.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, readerID);
                preparedStatement.setString(2, staffID);
                preparedStatement.setDate(3, dueDate);

                if (preparedStatement.executeUpdate() > 0) {
                    int generatedID = -1; 
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        generatedID = generatedKeys.getInt(1);
                    }

                    for (BorrowDetailDTO tempBorrowDetail : tempBorrowDetails) {
                        String query = "INSERT INTO `borrowdetail`(`borrowID`, `ISBN`, `quantity`, `description`) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(query)) {
                            stmt.setInt(1, generatedID);
                            stmt.setString(2, tempBorrowDetail.getISBN());
                            stmt.setInt(3, tempBorrowDetail.getQuantity());
                            stmt.setString(4, tempBorrowDetail.getDescription());
                            stmt.executeUpdate();
                        }
                        
                       String updateQuery = "UPDATE `versionofbook` SET `available` = `available`- ? WHERE ISBN = ?";
                       try (PreparedStatement updateStatement = ConnectDB.conn.prepareStatement(updateQuery)) {
                           updateStatement.setInt(1, tempBorrowDetail.getQuantity());
                           updateStatement.setString(2, tempBorrowDetail.getISBN());
                           updateStatement.executeUpdate();
                       }
                    }

                    ConnectDB.conn.commit();
                    flag = true;
                }

            } catch (SQLException e) {
                try {
                    ConnectDB.conn.rollback(); 
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
                e.printStackTrace();

            } finally {
                connectDB.disconnect(); 
            }
        }

        return flag;
    }
    public List<BorrowDTO> getBorrowFromDayToDay(java.sql.Date date1, java.sql.Date date2) throws ClassNotFoundException {
        List<BorrowDTO> borrowList = new ArrayList<>();
        String query = "SELECT * FROM borrowing WHERE borrowDate BETWEEN ? AND ?";

        try {
            // Kết nối cơ sở dữ liệu
            connectDB.connect();

            // Chuẩn bị câu lệnh SQL
            try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
                stmt.setDate(1, date1);
                stmt.setDate(2, date2);

                // Thực thi truy vấn
                try (ResultSet rs = stmt.executeQuery()) {
                    // Duyệt kết quả và thêm vào danh sách
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String readerID = rs.getString("readerID");
                        String staffID = rs.getString("returnStaffID");
                        LocalDate borrowDate = rs.getDate("borrowDate").toLocalDate();
                        LocalDate dueDate = rs.getDate("dueDate").toLocalDate();
                        LocalDate returnDate = rs.getDate("returnDate") != null ? rs.getDate("returnDate").toLocalDate() : null;
                        boolean delay = rs.getBoolean("delay");
                        long fine = rs.getLong("fine");
                        boolean isActive = rs.getBoolean("isActive");

                        // Lấy thông tin chi tiết mượn
                        Vector<BorrowDetailDTO> borrowDetailDTO = new BorrowDetailDAO().getBorrowDetails(id);

                        // Tạo đối tượng BorrowDTO và thêm vào danh sách
                        BorrowDTO borrow = new BorrowDTO(id, readerID, staffID, borrowDate, dueDate, returnDate, delay, fine, isActive, borrowDetailDTO);
                        borrowList.add(borrow);
                    }
                }
            }

            // Đóng kết nối
            connectDB.disconnect();
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Hoặc sử dụng logger để ghi lại lỗi
        }

        return borrowList;
    }


//    public List<BorrowDTO> getBorrowFromDayToDay(java.sql.Date date1,java.sql.Date date2){
//         List<BorrowDTO> borrowList = new ArrayList<>();
//        String query = "SELECT * FROM borrowing WHERE borrowDate BETWEEN ? AND ?";
//
//        try {
//            // Kết nối cơ sở dữ liệu
//            connectDB.connect();
//
//            // Chuẩn bị câu lệnh SQL
//            PreparedStatement stmt = connectDB.getConnection().prepareStatement(query);
//            stmt.setDate(1, date1);
//            stmt.setDate(2, date2);
//
//            // Thực thi truy vấn
//            ResultSet rs = stmt.executeQuery();
//
//            // Duyệt kết quả và thêm vào danh sách
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String readerID = rs.getString("readerID");
//                String staffID = rs.getString("returnStaffID");
//                LocalDate borrowDate = rs.getDate("borrowDate").toLocalDate();
//                LocalDate dueDate = rs.getDate("dueDate").toLocalDate();
//                LocalDate returnDate = rs.getDate("returnDate") != null ? rs.getDate("returnDate").toLocalDate() : null;
//                boolean delay = rs.getBoolean("delay");
//                long fine = rs.getLong("fine");
//                boolean isActive = rs.getBoolean("isActive");
//
//                // Lấy thông tin chi tiết mượn
//                Vector<BorrowDetailDTO> borrowDetailDTO;
//                try {
//                    borrowDetailDTO = new BorrowDetailDAO().getBorrowDetails(id);
//                }
//            
//                // Tạo đối tượng BorrowDTO và thêm vào danh sách
//                BorrowDTO borrow = new BorrowDTO(id, readerID, staffID, borrowDate, dueDate, returnDate, delay, fine, isActive, borrowDetailDTO);
//                borrowList.add(borrow);
//            }
//
//            // Đóng kết nối
//            rs.close();
//            stmt.close();
//            connectDB.disconnect();
//
//        } catch (SQLException e) {
//            e.printStackTrace(); // Xử lý lỗi nếu có
//        }
//
//        return borrowList;
//    }
}
