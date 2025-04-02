/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ImportDAO;
import DTO.ImportDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author User
 */
public class ImportBUS {
    protected static ImportDAO importDAO;
    
    public ImportBUS() throws ClassNotFoundException, SQLException, IOException {
        importDAO = new ImportDAO();
    }
    
    public Vector<ImportDTO> getAll() throws SQLException{
        Vector<ImportDTO> arr = importDAO.getAll();
        for (int i = 0 ; i < arr.size() ; i++){
            arr.get(i).setFullbooks(importDAO.getImportDetail(arr.get(i).getId()));
        }
        return arr;
    }
}
