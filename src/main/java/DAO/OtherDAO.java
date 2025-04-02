package DAO;

import DTO.AuthorDTO;
import DTO.CategoryDTO;
import DTO.PublisherDTO;
import DTO.SupplierDTO;
import connection.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author antra
 */
public class OtherDAO {
    private ConnectDB db;
    public OtherDAO(){
        db = new ConnectDB();
    }
    public List<AuthorDTO> loadAuthorData(){
        List<AuthorDTO> authors = new ArrayList<>();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sqlQuery = "SELECT * FROM author WHERE isActive = 1";
            stmt = conn.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();
            while(rs.next()){
                AuthorDTO author = new AuthorDTO();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setYear(rs.getInt("year"));
                authors.add(author);
            }
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return authors;
    }
    
    public List<CategoryDTO> loadCategoryData(){
        List<CategoryDTO> categories = new ArrayList<>();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sqlQuery = "SELECT * FROM category WHERE isActive = 1";
            stmt = conn.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();
            while(rs.next()){
                CategoryDTO category = new CategoryDTO();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return categories;
    }
    
    public List<PublisherDTO> loadPublisherData(){
        List<PublisherDTO> publishers = new ArrayList<>();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sqlQuery = "SELECT * FROM publisher WHERE isActive = 1";
            stmt = conn.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();
            while(rs.next()){
                PublisherDTO publisher = new PublisherDTO();
                publisher.setId(rs.getInt("id"));
                publisher.setName(rs.getString("name"));
                publishers.add(publisher);
            }
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return publishers;
    }
    
    public List<SupplierDTO> loadSupplierData(){
        List<SupplierDTO> suppliers = new ArrayList<>();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sqlQuery = "SELECT * FROM supplier WHERE isActive = 1";
            stmt = conn.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();
            while(rs.next()){
                SupplierDTO supplier = new SupplierDTO();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                suppliers.add(supplier);
            }
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return suppliers;
    }
    
    public boolean addAuthor(AuthorDTO author){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sql = "INSERT INTO author (year, name) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, author.getYear());
            stmt.setString(2, author.getName()); // Số nguyên
//            stmt.setBoolean(3, author.get); // Số thực
            int rowsInserted = stmt.executeUpdate();
            if(!(rowsInserted > 0)){
                return false;
            }
        }catch(SQLException e){
            System.out.println("Lỗi: " + e.getMessage());
            return false;
        }finally {
            try {
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return true;
    }
    
    public boolean addCategory(CategoryDTO category){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sql = "INSERT INTO category (name) VALUES (?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, category.getName());
            int rowsInserted = stmt.executeUpdate();
            if(!(rowsInserted > 0)){
                return false;
            }
        }catch(SQLException e){
            System.out.println("Lỗi: " + e.getMessage());
            return false;
        }finally {
            try {
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return true;
    }
    
     public boolean addPublisher(PublisherDTO publisher){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sql = "INSERT INTO publisher (name) VALUES (?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, publisher.getName());
            int rowsInserted = stmt.executeUpdate();
            if(!(rowsInserted > 0)){
                return false;
            }
        }catch(SQLException e){
            System.out.println("Lỗi: " + e.getMessage());
            return false;
        }finally {
            try {
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return true;
    }
     
     public boolean addSupplier(SupplierDTO supplier){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sql = "INSERT INTO supplier (name, address, tel) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getAddress());
            stmt.setString(3, supplier.getTel());

            int rowsInserted = stmt.executeUpdate();
            if(!(rowsInserted > 0)){
                return false;
            }
        }catch(SQLException e){
            System.out.println("Lỗi: " + e.getMessage());
            return false;
        }finally {
            try {
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return true;
    }
    
    public boolean deleteAuthor(AuthorDTO author){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sql = "UPDATE author SET isActive = 0 WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, author.getId());
            int rowsUpdated = stmt.executeUpdate();
            if(!(rowsUpdated > 0)){
                return false;
            }
        }catch(SQLException e){
            System.out.println("Error closing resources: " + e.getMessage());
        }
        return true;
    }
    
    public boolean deleteCategory(CategoryDTO category){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sql = "UPDATE category SET isActive = 0 WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, category.getId());
            int rowsUpdated = stmt.executeUpdate();
            if(!(rowsUpdated > 0)){
                return false;
            }
        }catch(SQLException e){
            System.out.println("Error closing resources: " + e.getMessage());
        }
        return true;
    }
    
    public boolean deletePublisher(PublisherDTO publisher){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sql = "UPDATE publisher SET isActive = 0 WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, publisher.getId());
            int rowsUpdated = stmt.executeUpdate();
            if(!(rowsUpdated > 0)){
                return false;
            }
        }catch(SQLException e){
            System.out.println("Error closing resources: " + e.getMessage());
        }
        return true;
    }
    
    public boolean deleteSupplier(SupplierDTO supplier){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sql = "UPDATE supplier SET isActive = 0 WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, supplier.getId());
            int rowsUpdated = stmt.executeUpdate();
            if(!(rowsUpdated > 0)){
                return false;
            }
        }catch(SQLException e){
            System.out.println("Error closing resources: " + e.getMessage());
        }
        return true;
    }
    
    public AuthorDTO getAuthorById(int authorId){
        AuthorDTO author = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sqlQuery = "SELECT * FROM author WHERE id = ?";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setInt(1, authorId);
            rs = stmt.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int year = rs.getInt("year");
                author = new AuthorDTO(id, name, year);
            }else {
                System.out.println("No author found with ID: " + authorId);
            }
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return author;
    }
    
    public CategoryDTO getCategoryById(int categoryId){
        CategoryDTO category = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sqlQuery = "SELECT * FROM category WHERE id = ?";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setInt(1, categoryId);
            rs = stmt.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                category = new CategoryDTO(id, name);
            }else {
                System.out.println("No category found with ID: " + categoryId);
            }
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return category;
    }
    
    public PublisherDTO getPublisherById(int publisherId){
        PublisherDTO publisher = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sqlQuery = "SELECT * FROM publisher WHERE id = ?";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setInt(1, publisherId);
            rs = stmt.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                publisher = new PublisherDTO(id, name);
            }else {
                System.out.println("No publisher found with ID: " + publisherId);
            }
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return publisher;
    }
    
    public SupplierDTO getSupplierById(int supplierId){
        SupplierDTO supplier = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sqlQuery = "SELECT * FROM supplier WHERE id = ?";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setInt(1, supplierId);
            rs = stmt.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String tel = rs.getString("tel");
                supplier = new SupplierDTO(id, name, address, tel);
            }else {
                System.out.println("No author found with ID: " + supplierId);
            }
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return supplier;
    }
    
}
