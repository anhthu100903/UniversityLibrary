/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.AccountDTO;
import DTO.FullBookDTO;
import DTO.ImportDTO;
import DTO.SupplierDTO;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author User
 */
public class ImportDAO {
    ConnectDB connectDB;
    
    public ImportDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
    }
    
    public Vector<ImportDTO> getAll() throws SQLException{
        Vector<ImportDTO> arr = new Vector<ImportDTO>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = """
                             SELECT importing.id, person.id, person.name, importDate, fee, supplier.name
                             FROM importing JOIN person ON importing.staffID = person.id
                             JOIN supplier ON importing.supplierID = supplier.id
                             WHERE importing.isActive = 1""";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                    ImportDTO a = new ImportDTO();
                    a.setId(rs.getInt(1));
                    a.setAccount(new AccountDTO());
                    a.getAccount().setId(rs.getString(2));
                    a.getAccount().setName(rs.getString(3));
                    a.setImportDate(rs.getDate(4).toLocalDate());
                    a.setFee(rs.getLong(5));
                    a.setSupplier(new SupplierDTO());
                    a.getSupplier().setName(rs.getString(6));
                    arr.add(a);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return arr;
    }
    
    public Vector<FullBookDTO> getImportDetail(int id) throws SQLException{
        Vector<FullBookDTO> arr = new Vector<FullBookDTO>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = """
                             SELECT ISBN, quantity 
                             FROM importdetail
                             WHERE importID = ? """;
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                    FullBookDTO a = new FullBookDTO();
                    a.setISBN(rs.getString(1));
                    a.setQuantity(rs.getInt(2));
                    arr.add(a);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return arr;
    }
}
