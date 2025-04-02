/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import DAO.RoleDAO;
import DTO.RoleDTO;

/**
 *
 * @author User
 */
public class RoleBUS {
    protected static RoleDAO roleDAO;

    public RoleBUS() throws ClassNotFoundException, SQLException, IOException {
        roleDAO = new RoleDAO();
    }

    public List<RoleDTO> getAllRoles() throws SQLException {
        return roleDAO.getAllRoles();
    }
}
