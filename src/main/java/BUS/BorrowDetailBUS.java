/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BorrowDetailDAO;
import DTO.BorrowDetailDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anhthu
 */
public class BorrowDetailBUS {

    protected static BorrowDetailDAO detailDAO;
    private BorrowBUS borrowBus;
    private Map<Integer, BorrowDetailDTO> borrowDetailMap = new HashMap<>(); // Lưu BorrowDetailDTO theo chỉ số hàng

    public BorrowDetailBUS() {
        try {
            detailDAO = new BorrowDetailDAO();
            borrowBus = new BorrowBUS();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error initializing database connection: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    // Gọi DAO để thêm 1 chi tiết chi tiết phiếu mượn
    public boolean add(BorrowDetailDTO detail) {
        try {
            return detailDAO.add(detail);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Gọi DAO để cập nhật số lượng của 1 chi tiết phiếu mượn
    public boolean updateQuantity(BorrowDetailDTO detailDTO) {
        try {
            return detailDAO.updateQuantity(detailDTO);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Gọi DAO để cập nhật mô tả của 1 chi tiết phiếu mượn
    public boolean updateDescription(int borrowID, String ISBN, String description) {
        try {
            return detailDAO.updateDesciption(borrowID, ISBN, description);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Gọi DAO để cập nhật số lượng mất và hỏng
    public boolean updateLostAndBroke(int borrowID, String ISBN, int lost, int broke) {
        try {
            return detailDAO.updateLostAndBroke(borrowID, ISBN, lost, broke);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Lớp BUS
    public List<BorrowDetailDTO> loadBorrowDetailData(int borrowID) {
        try {
            List<BorrowDetailDTO> borrowDetailList = detailDAO.selectAll(borrowID);
            return borrowDetailList;
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkBorrowDetailExistence(String ISBN, int borrowID){
        try {
            return detailDAO.checkBorrowDetailExistence(ISBN, borrowID);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailBUS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Vector<BorrowDetailDTO> getBorrowDetails(int borrowID) {
        try {
            return detailDAO.getBorrowDetails(borrowID);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
