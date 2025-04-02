/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.AccountDTO;
import DTO.RoleDTO;
import at.favre.lib.crypto.bcrypt.BCrypt;
import connection.ConnectDB;

/**
 *
 * @author User
 */
public class AccountDAO {
    ConnectDB connectDB;

    public AccountDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
    }
    
    public AccountDTO login(String userid) throws SQLException{
        AccountDTO a = new AccountDTO();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{    
                String sql = "SELECT account.id, account.password, role.id, role.name, person.name, account.dayCreated, account.isActive FROM account JOIN role ON account.positionID = role.id JOIN person ON account.id = person.id WHERE person.isActive = 1 AND account.id = ?";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, userid);
                ResultSet rs = stmt.executeQuery();
                
                while(rs.next()) {
                    a.setId(rs.getString(1));
                    a.setPassword(rs.getString(2));
                    a.setRoleDTO(new RoleDTO(rs.getString(3), rs.getString(4)));
                    a.setName(rs.getString(5));
                    a.setDayCreated(rs.getDate(6).toLocalDate());
                    a.setIsActive(rs.getBoolean(7));
                }
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return a;
    }

    public List<AccountDTO> getFullAccount() throws SQLException {
        List<AccountDTO> accounts = new ArrayList<>(); // Khởi tạo danh sách AccountDTO
        connectDB.connect();
        if (ConnectDB.conn != null) {
            try {
                String sql = "SELECT a.id AS account_id, p.name AS account_name, " +
                            "a.positionID AS role_id, a.dayCreated AS created_date, " +
                            "a.isActive AS status, r.name AS role_name " +
                            "FROM account a " +
                            "JOIN person p ON a.id = p.id " +
                            "JOIN role r ON a.positionID = r.id " +
                            "WHERE p.isActive = 1";

                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                // Lặp qua ResultSet để lấy tất cả tài khoản
                while (rs.next()) {
                    AccountDTO account = new AccountDTO(); // Tạo một đối tượng AccountDTO mới
                    account.setId(rs.getString("account_id"));
                    account.setName(rs.getString("account_name"));
                    account.setIsActive(rs.getBoolean("status"));

                    // Tạo và gán RoleDTO
                    RoleDTO roleDTO = new RoleDTO(rs.getString("role_id"), rs.getString("role_name"));
                    account.setRoleDTO(roleDTO);

                    // Chuyển đổi từ java.sql.Date sang LocalDate
                    Date createdDate = rs.getDate("created_date");
                    if (createdDate != null) {
                        LocalDate localDate = createdDate.toLocalDate();
                        account.setDayCreated(localDate);
                    }

                    accounts.add(account); // Thêm tài khoản vào danh sách
                }
            } catch (SQLException e) {
                e.printStackTrace(); // In ra lỗi nếu có
            } finally {
                connectDB.disconnect();
            }
        }
        return accounts; // Trả về danh sách các tài khoản
    }

    public boolean updateAccountStatus(String accountId, boolean    isActive) throws SQLException {
        boolean flag = false;
        String sql = "UPDATE account SET isActive = ? WHERE id = ?"; // Câu truy vấn để khóa tài khoản
        connectDB.connect();
        if (ConnectDB.conn != null) {
            try {
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);
                stmt.setInt(1, isActive ? 1 : 0); // Cập nhật trạng thái
                stmt.setString(2, accountId);
                int rowsAffected = stmt.executeUpdate(); // Thực hiện cập nhật
                flag = rowsAffected > 0; // Kiểm tra xem có hàng nào được cập nhật không
            } catch (SQLException e) {
                e.printStackTrace(); // In ra lỗi nếu có
            } finally {
                connectDB.disconnect();
            }
        }

        return flag;
    }

    public List<AccountDTO> getAccountBySearchCondition(String accountId, LocalDate startDate, LocalDate endDate, Boolean isActive, String selectedRole) throws SQLException {
        List<AccountDTO> accounts = new ArrayList<>(); // Khởi tạo danh sách AccountDTO
        connectDB.connect();
        if (ConnectDB.conn != null) {
            try {
                StringBuilder sql = new StringBuilder("SELECT a.id AS account_id, p.name AS account_name, " +
                        "a.positionID AS role_id, a.dayCreated AS created_date, " +
                        "a.isActive AS status, r.name AS role_name " +
                        "FROM account a " +
                        "JOIN person p ON a.id = p.id " +
                        "JOIN role r ON a.positionID = r.id " +
                        "WHERE p.isActive = 1");
    
                // Thêm điều kiện cho accountId
                if (accountId != null && !accountId.isEmpty()) {
                    sql.append(" AND a.id LIKE ?");
                }
                // Thêm điều kiện cho startDate
                if (startDate != null) {
                    sql.append(" AND a.dayCreated >= ?");
                }
                // Thêm điều kiện cho endDate
                if (endDate != null) {
                    sql.append(" AND a.dayCreated <= ?");
                }
                // Thêm điều kiện cho isActive
                if (isActive != null) {
                    sql.append(" AND a.isActive = ?");
                }
                // Thêm điều kiện cho role
                if (selectedRole != null && !selectedRole.isEmpty()) {
                    sql.append(" AND r.name = ?");
                }
    
                System.out.println(sql.toString());
    
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql.toString());
    
                int paramIndex = 1; // Chỉ số cho tham số PreparedStatement
    
                // Thiết lập các tham số cho PreparedStatement
                if (accountId != null && !accountId.isEmpty()) {
                    stmt.setString(paramIndex++, "%" + accountId + "%"); // Thiết lập tham số cho id
                }
                if (startDate != null) {
                    stmt.setDate(paramIndex++, Date.valueOf(startDate)); // Thiết lập tham số cho startDate và Chuyển LocalDate thành java.sql.Date
                }
                if (endDate != null) {
                    stmt.setDate(paramIndex++, Date.valueOf(endDate)); // Thiết lập tham số cho endDate và Chuyển LocalDate thành java.sql.Date
                }
                if (isActive != null) {
                    stmt.setBoolean(paramIndex++, isActive); // Thiết lập cho isActive
                }
                if (selectedRole != null && !selectedRole.isEmpty()) {
                    stmt.setString(paramIndex++, selectedRole); // Thiết lập tham số cho role
                }

                System.out.println(stmt.toString());
    
                ResultSet rs = stmt.executeQuery();
    
                // Lặp qua ResultSet để lấy tất cả tài khoản
                while (rs.next()) {
                    AccountDTO account = new AccountDTO(); // Tạo một đối tượng AccountDTO mới
                    account.setId(rs.getString("account_id"));
                    account.setName(rs.getString("account_name"));
                    account.setIsActive(rs.getBoolean("status"));
    
                    // Tạo và gán RoleDTO
                    RoleDTO roleDTO = new RoleDTO(rs.getString("role_id"), rs.getString("role_name"));
                    account.setRoleDTO(roleDTO);
    
                    // Chuyển đổi từ java.sql.Date sang LocalDate
                    Date createdDate = rs.getDate("created_date");
                    if (createdDate != null) {
                        LocalDate localDate = createdDate.toLocalDate();
                        account.setDayCreated(localDate);
                    }
    
                    accounts.add(account); // Thêm tài khoản vào danh sách
                }
            } catch (SQLException e) {
                e.printStackTrace(); // In ra lỗi nếu có
            } finally {
                connectDB.disconnect();
            }
        }
        return accounts; // Trả về danh sách các tài khoản
    }

    public boolean changePassword(String accountId, String newPassword) throws SQLException {
        boolean isUpdated = false; // Biến để kiểm tra xem có cập nhật thành công không
        connectDB.connect();
        
        if (ConnectDB.conn != null) {
            ConnectDB.conn.setAutoCommit(false); // Tắt chế độ tự động commit

            // Mã hóa mật khẩu mới bằng BCrypt
            String hashedPassword = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());
    
            String sql = "UPDATE account SET password = ? WHERE id = ?"; // Giả sử bảng account có cột password
    
            try (PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql)) {
                stmt.setString(1, hashedPassword); // Gán giá trị mật khẩu mới
                stmt.setString(2, accountId); // Gán ID tài khoản
    
                int rowsAffected = stmt.executeUpdate(); // Thực hiện cập nhật
                if (rowsAffected > 0) {
                    isUpdated = true; // Nếu có ít nhất một hàng được cập nhật
                }
    
                ConnectDB.conn.commit(); // Commit giao dịch nếu không có lỗi
            } catch (SQLException e) {
                ConnectDB.conn.rollback(); // Rollback nếu có lỗi
                e.printStackTrace(); // In ra lỗi nếu có
            } finally {
                connectDB.disconnect(); // Ngắt kết nối với cơ sở dữ liệu
            }
        }

        return isUpdated; // Trả về true nếu cập nhật thành công, ngược lại là false
    }

    public boolean addAccount(AccountDTO accountDTO){
        try {
            connectDB.connect();
            String query = "INSERT INTO `account`(`id`, `password`, `positionID`, `dayCreated`) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connectDB.getConnection().prepareStatement(query);
            stmt.setString(1, accountDTO.getId());
            stmt.setString(2, accountDTO.getPassword());
            System.out.println("DAO.AccountDAO.addAccount()"+accountDTO.getRoleDTO().getId());
            stmt.setString(3, accountDTO.getRoleDTO().getId());
            java.sql.Date sqlDate = java.sql.Date.valueOf(accountDTO.getDayCreated());
            stmt.setDate(4, sqlDate);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
