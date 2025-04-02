/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import DAO.AccountDAO;
import DTO.AccountDTO;

/**
 *
 * @author User
 */
public class AccountBUS {
    protected static AccountDAO accountDAO;

    public AccountBUS() throws ClassNotFoundException, SQLException, IOException {
        accountDAO = new AccountDAO();
    }
    
    public AccountDTO login(String userid) throws SQLException{
        return accountDAO.login(userid);
    }

    public List<AccountDTO> getFullAccount() throws SQLException {
        return accountDAO.getFullAccount();
    }

    public boolean updateAccountStatus(String accountId, boolean isActive) throws SQLException {
        return accountDAO.updateAccountStatus(accountId, isActive);
    }

    public List<AccountDTO> getAccountBySearchCondition(String accountId,LocalDate startDate,LocalDate endDate,Boolean isActive, String selectedRole) throws SQLException {
        return accountDAO.getAccountBySearchCondition(accountId, startDate, endDate, isActive, selectedRole);
    }

    public boolean changePassword(String accountId, String newPassword) throws SQLException {
        return accountDAO.changePassword(accountId, newPassword);
    }
    
    public boolean addAccount(AccountDTO accountDTO){
        return accountDAO.addAccount(accountDTO);
    }
}
