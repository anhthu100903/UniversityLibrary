/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import DAO.BookDAO;
import DTO.AuthorDTO;
import DTO.BookDTO;
import DTO.BookNameDTO;
import DTO.CategoryDTO;
import DTO.FullBookDTO;
import DTO.ImportDTO;
import DTO.PublisherDTO;
import DTO.SupplierDTO;

/**
 *
 * @author User
 */
public class BookBUS {
    protected static BookDAO bookDAO;
    
    public BookBUS() throws ClassNotFoundException, SQLException, IOException {
        bookDAO = new BookDAO();
    }
    
    public Vector<AuthorDTO> getAllAuthor() throws SQLException{
        return bookDAO.getAllAuthor();
    }
    
    public String addAuthor(AuthorDTO author) throws SQLException{
        if (author.getName().equals("")){
            return "Tên tác giả không được bỏ trống";
        }
        if (author.getYear()== 0 || author.getYear() > 0 && author.getYear() < LocalDate.now().getYear()){
            if (bookDAO.isAuthorExist(author)){
                return "Tác giả này đã có trong cơ sở dữ liệu";
            }else{
                if (bookDAO.addAuthor(author)){
                    return "Thêm tác giả thành công";
                }else{
                    return "Đã có lỗi xảy ra";
                }
            }
        }else{
            return "Năm sinh không hợp lệ";
        }
    }
    
    public Vector<PublisherDTO> getAllPublisher() throws SQLException{
        return bookDAO.getAllPublisher();
    }
    
    public String AddPublisher(PublisherDTO publisher) throws SQLException{
        if (publisher.getName().equals("")){
            return "Tên nhà xuất bản không được bỏ trống";
        }
        if (bookDAO.isPublisherExist(publisher)){
            return "Nhà xuất bản này đã có trong cơ sở dữ liệu";
        }else{
            if(bookDAO.addPublisher(publisher)){
                return "Thêm nhà xuất bản thành công";
            }else{
                return "Đã có lỗi xảy ra";
            }
        }
    }
    
    public Vector<CategoryDTO> getAllCategory() throws SQLException{
        return bookDAO.getAllCategory();
    }
    
    public String AddCategory(CategoryDTO category) throws SQLException{
        if (category.getName().equals("")){
            return "Tên thể loại không được bỏ trống";
        }
        if (bookDAO.isCategoryExist(category)){
            return "Thể loại này đã có trong cơ sở dữ liệu";
        }else{
            if(bookDAO.addCategory(category)){
                return "Thêm thể loại thành công";
            }else{
                return "Đã có lỗi xảy ra";
            }
        }
    }
    
    public Vector<SupplierDTO> getAllSupplier() throws SQLException{
        return bookDAO.getAllSupplier();
    }
    
    public String AddSupplier(SupplierDTO supplier) throws SQLException{
        if (supplier.getName().equals("")){
            return "Tên nhà cung cấp bản không được bỏ trống";
        }
        if (!supplier.getTel().matches("^(0\\d{9}|)$")){
            return "Số điện thoại không hợp lệ";
        }
        if (bookDAO.isSupplierExist(supplier)){
            return "Thông tin bị trùng với nhà cung cấp có sẵn trong cơ sở dữ liệu";
        }else{
            if(bookDAO.addSupplier(supplier)){
                return "Thêm nhà cung cấp thành công";
            }else{
                return "Đã có lỗi xảy ra";
            }
        }
    }
    
     public Vector<BookNameDTO> getAllBookName() throws SQLException{
        return bookDAO.getAllBookName();
    }
    
    public String AddBookName(BookNameDTO bookName) throws SQLException{
        if (bookName.getName().equals("")){
            return "Tên sách không được bỏ trống";
        }
        if (bookDAO.isBookNameExist(bookName)){
            return "Tên sách này đã có trong cơ sở dữ liệu";
        }else{
            if(bookDAO.addBookName(bookName)){
                return "Thêm tên sách thành công";
            }else{
                return "Đã có lỗi xảy ra";
            }
        }
    }
    
    public boolean checkBookName(int id) throws SQLException{
        return bookDAO.isBookSetUp(id);
    }
    
    public Vector<CategoryDTO> getBookCategory(int id) throws SQLException{
        return bookDAO.getBookCategory(id);
    }
    
    public Vector<AuthorDTO> getBookAuthor(int id) throws SQLException{
        return bookDAO.getBookAuthor(id);
    }
    
    public FullBookDTO getFullBook(String ISBN) throws SQLException{
        FullBookDTO fullbook = bookDAO.getFullBook(ISBN);
        return fullbook;
    }
    
    public String isDifferentISBNExist(FullBookDTO fullbook) throws SQLException {
        return bookDAO.isDifferentISBNExist(fullbook);
    }
    
    public boolean AddImport(ImportDTO importing) throws SQLException{
        return bookDAO.AddImport(importing);
    }

    // Get tất cả sách theo search condition, nếu search condition là null sẽ get tất cả sách
    public List<BookDTO> getAllBookByCondition(Map<String, String> searchConditions) throws  SQLException{
        List<BookDTO> books = bookDAO.getAllBookByCondition(searchConditions);
        if (!books.isEmpty()){
            for (BookDTO u : books){
                u.setAuthors(bookDAO.getStringBookAuthor(u.getNameID()));
                u.setCategories(bookDAO.getStringBookCategory(u.getNameID()));
            }
        }
        return books;
    }

    // Xóa sách theo ISBN
    public boolean deleteBookByISBN(String isbn) throws  SQLException{
        return bookDAO.deleteBookByISBN(isbn);
    }
    
    public Vector<BookDTO> getAllBook() throws SQLException{
        Vector<BookDTO> allBook = bookDAO.getAllBook();
        if (!allBook.isEmpty()){
            for (BookDTO u : allBook){
                u.setAuthors(bookDAO.getStringBookAuthor(u.getNameID()));
                u.setCategories(bookDAO.getStringBookCategory(u.getNameID()));
            }
        }
        return allBook;
    }
    
    public boolean arrangeBook(String floor, String shelf, int quantity, String ISBN) throws SQLException{
        return bookDAO.arrangeBook(floor, shelf, quantity, ISBN);
    }
    
    public String getEditionByISBN(String ISBN)throws SQLException{
        return bookDAO.getEditionByISBN(ISBN);
    }
}