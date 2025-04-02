/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.RoleDTO;
import connection.ConnectDB;

/**
 *
 * @author User
 */
public class RoleDAO {
    ConnectDB connectDB;

    public RoleDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
    }

    public List<RoleDTO> getAllRoles() throws SQLException {
        List<RoleDTO> roles = new ArrayList<>(); // Khởi tạo danh sách RoleDTO
        connectDB.connect();
        if (ConnectDB.conn != null) {
            try {
                String sql = "SELECT id, name FROM role";

                System.out.println(sql);

                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                // Lặp qua ResultSet để lấy tất cả tài khoản
                while (rs.next()) {
                    RoleDTO role = new RoleDTO(rs.getString("id"), rs.getString("name"));
                    roles.add(role);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // In ra lỗi nếu có
            } finally {
                connectDB.disconnect();
            }
        }
        return roles; // Trả về danh sách các tài khoản
    }
}
