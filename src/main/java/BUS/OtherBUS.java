/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.OtherDAO;
import DTO.AuthorDTO;
import DTO.CategoryDTO;
import DTO.PublisherDTO;
import DTO.SupplierDTO;
import java.util.List;

/**
 *
 * @author antra
 */
public class OtherBUS {
    private OtherDAO otherDAO;
    public OtherBUS(){
        otherDAO = new OtherDAO();
    }
    public List<AuthorDTO> loadAuthorData(){
        return otherDAO.loadAuthorData();
    }
    public List<CategoryDTO> loadCategoryData(){
        return otherDAO.loadCategoryData();
    }
    public List<PublisherDTO> loadPublisherData(){
        return otherDAO.loadPublisherData();
    }
    public List<SupplierDTO> loadSupplierData(){
        return otherDAO.loadSupplierData();
    }
    
    public String addAuthor(AuthorDTO author){
        boolean result = otherDAO.addAuthor(author);
        return result? "Thêm tác giả thành công": "Thêm tác giả thất bại";
    }
    
    public String addCategory(CategoryDTO category){
        boolean result = otherDAO.addCategory(category);
        return result? "Thêm thể loại thành công": "Thêm thể loại thất bại";
    }
    
    public String addPublisher(PublisherDTO publisher){
        boolean result = otherDAO.addPublisher(publisher);
        return result? "Thêm nhà xuất bản thành công": "Thêm nhà xuất bản thất bại";
    }
    
    public String addSupplier(SupplierDTO supplier){
        boolean result = otherDAO.addSupplier(supplier);
        return result? "Thêm nhà cung cấp thành công": "Thêm nhà cung cấp thất bại";
    }
    
    public AuthorDTO getAuthorById(int authorId){
        return otherDAO.getAuthorById(authorId);
    }
    
    public CategoryDTO getCategoryById(int categoryId){
        return otherDAO.getCategoryById(categoryId);
    }
    
    public PublisherDTO getPublisherById(int publisherId){
        return otherDAO.getPublisherById(publisherId);
    }
    
    public SupplierDTO getSupplierById(int supplierId){
        return otherDAO.getSupplierById(supplierId);
    }
    
    public String deleteAuthor(AuthorDTO author){
        try {
            boolean isDeleted = otherDAO.deleteAuthor(author);

            if (isDeleted) {
                return "Đã xóa tác giả";
            } else {
                return "Xóa tác giả thất bại: Tác giả không tồn tại hoặc không có thay đổi.";
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            System.out.println("Error: " + e.getMessage());
            return "vui lòng chọn tác giả muốn xóa";
        }
    }
    
    public String deleteCategory(CategoryDTO category){
        try {
            boolean isDeleted = otherDAO.deleteCategory(category);

            if (isDeleted) {
                return "Đã xóa thể loại";
            } else {
                return "Xóa thể loại thất bại: thể loại không tồn tại hoặc không có thay đổi.";
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            System.out.println("Error: " + e.getMessage());
            return "vui lòng chọn thể loại muốn xóa";
        }
    }
    
    public String deletePublisher(PublisherDTO publisher){
        try {
            boolean isDeleted = otherDAO.deletePublisher(publisher);

            if (isDeleted) {
                return "Đã xóa nhà xuất bản";
            } else {
                return "Xóa nhà xuất bản thất bại: nhà xuất bản không tồn tại hoặc không có thay đổi.";
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            System.out.println("Error: " + e.getMessage());
            return "vui lòng chọn nhà xuất bản muốn xóa";
        }
    }
    
    public String deleteSupplier(SupplierDTO supplier){
        try {
            boolean isDeleted = otherDAO.deleteSupplier(supplier);

            if (isDeleted) {
                return "Đã xóa nhà cung cấp";
            } else {
                return "Xóa nhà cung cấp thất bại: Nhà cung cấp không tồn tại hoặc không có thay đổi.";
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            System.out.println("Error: " + e.getMessage());
            return "vui lòng chọn nhà cung cấp muốn xóa";
        }
    }
}
