/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import DTO.AuthorDTO;
import DTO.BookDTO;
import DTO.BookNameDTO;
import DTO.CategoryDTO;
import DTO.FullBookDTO;
import DTO.ImportDTO;
import DTO.PublisherDTO;
import DTO.SupplierDTO;
import connection.ConnectDB;

/**
 *
 * @author User
 */
public class BookDAO {
    ConnectDB connectDB;
    
    public BookDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
    }
    
    public Vector<AuthorDTO> getAllAuthor() throws SQLException{
        Vector<AuthorDTO> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT author.* FROM author where isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   AuthorDTO u = new AuthorDTO();
                   u.setId(rs.getInt(1));
                   u.setName(rs.getString(2));
                   u.setYear(rs.getInt(3));
                   result.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public boolean isAuthorExist(AuthorDTO author) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM author WHERE UPPER(author.name) = UPPER(?) AND author.year = ? AND isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, author.getName());
                stmt.setInt(2, author.getYear());
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean addAuthor(AuthorDTO author) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "INSERT INTO author(name, year) values (?, ?)";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, author.getName());
                stmt.setInt(2, author.getYear());
          
                if(stmt.executeUpdate()>0){
                    flag=true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public Vector<PublisherDTO> getAllPublisher() throws SQLException{
        Vector<PublisherDTO> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT publisher.* FROM publisher where isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   PublisherDTO u = new PublisherDTO();
                   u.setId(rs.getInt(1));
                   u.setName(rs.getString(2));
                   result.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public boolean isPublisherExist(PublisherDTO publisher) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM publisher WHERE UPPER(publisher.name) = UPPER(?) AND isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, publisher.getName());
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean addPublisher(PublisherDTO publisher) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "INSERT INTO publisher(name) values (?)";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, publisher.getName());
          
                if(stmt.executeUpdate()>0){
                    flag=true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public Vector<CategoryDTO> getAllCategory() throws SQLException{
        Vector<CategoryDTO> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT category.* FROM category where isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   CategoryDTO u = new CategoryDTO();
                   u.setId(rs.getInt(1));
                   u.setName(rs.getString(2));
                   result.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public boolean isCategoryExist(CategoryDTO category) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM category WHERE UPPER(category.name) = UPPER(?) AND isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, category.getName());
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean addCategory(CategoryDTO category) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "INSERT INTO category(name) values (?)";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, category.getName());
          
                if(stmt.executeUpdate()>0){
                    flag=true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public Vector<SupplierDTO> getAllSupplier() throws SQLException{
        Vector<SupplierDTO> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT supplier.* FROM supplier where isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   SupplierDTO u = new SupplierDTO();
                   u.setId(rs.getInt(1));
                   u.setName(rs.getString(2));
                   u.setAddress(rs.getString(3));
                   u.setTel(rs.getString(4));
                   result.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public boolean isSupplierExist(SupplierDTO supplier) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM supplier WHERE UPPER(category.name) = UPPER(?) OR tel = ? AND isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, supplier.getName());
                stmt.setString(2, supplier.getTel());
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean addSupplier(SupplierDTO supplier) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "INSERT INTO supplier(name, address, tel) values (?, ?, ?)";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, supplier.getName());
                stmt.setString(2, supplier.getAddress());
                stmt.setString(3, supplier.getTel());
          
                if(stmt.executeUpdate()>0){
                    flag=true;
                }
                 
            }catch(SQLException e){
                e.printStackTrace();
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public Vector<BookNameDTO> getAllBookName() throws SQLException{
        Vector<BookNameDTO> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT book.* FROM book where isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   BookNameDTO u = new BookNameDTO();
                   u.setId(rs.getInt(1));
                   u.setName(rs.getString(2));
                   result.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public boolean isBookNameExist(BookNameDTO bookName) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM book WHERE UPPER(book.name) = UPPER(?) AND isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, bookName.getName());
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean addBookName(BookNameDTO bookName) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "INSERT INTO book(name) values (?)";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, bookName.getName());
          
                if(stmt.executeUpdate()>0){
                    flag=true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean isBookSetUp(int bookID) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = """
                             SELECT * 
                             FROM book JOIN bookauthor ON book.id = bookauthor.bookID
                                       JOIN author ON author.id = bookauthor.authorID
                                       JOIN bookcategory ON book.id = bookcategory.bookID
                                       JOIN category ON category.id = bookcategory.categoryID
                             WHERE book.isActive = 1 AND book.id = ? """;
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setInt(1, bookID);
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public Vector<AuthorDTO> getBookAuthor(int id) throws SQLException{
        Vector<AuthorDTO> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = """
                             SELECT author.* 
                             FROM book JOIN bookauthor ON book.id = bookauthor.bookID
                             JOIN author ON author.id = bookauthor.authorID
                             WHERE book.isActive = 1 AND book.id = ?""";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   AuthorDTO u = new AuthorDTO();
                   u.setId(rs.getInt(1));
                   u.setName(rs.getString(2));
                   u.setYear(rs.getInt(3));
                   result.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public Vector<String> getStringBookAuthor(int id) throws SQLException{
        Vector<String> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = """
                             SELECT author.* 
                             FROM book JOIN bookauthor ON book.id = bookauthor.bookID
                             JOIN author ON author.id = bookauthor.authorID
                             WHERE book.isActive = 1 AND book.id = ?""";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   result.add(rs.getString(2));
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public Vector<CategoryDTO> getBookCategory(int id) throws SQLException{
        Vector<CategoryDTO> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = """
                             SELECT category.* 
                             FROM book JOIN bookcategory ON book.id = bookcategory.bookID
                             JOIN category ON category.id = bookcategory.categoryID
                             WHERE book.isActive = 1 AND book.id = ?""";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   CategoryDTO u = new CategoryDTO();
                   u.setId(rs.getInt(1));
                   u.setName(rs.getString(2));
                   result.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public Vector<String> getStringBookCategory(int id) throws SQLException{
        Vector<String> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = """
                             SELECT category.* 
                             FROM book JOIN bookcategory ON book.id = bookcategory.bookID
                             JOIN category ON category.id = bookcategory.categoryID
                             WHERE book.isActive = 1 AND book.id = ?""";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   result.add(rs.getString(2));
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public FullBookDTO getFullBook(String ISBN) throws SQLException{
        FullBookDTO fullbook = new FullBookDTO();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = """
                             SELECT ISBN, bookID, book.name AS bookName, img, publisherID, publisher.name, edition, price
                             FROM versionofbook JOIN book ON versionofbook.bookID = book.id 
                             JOIN publisher ON versionofbook.publisherID = publisher.id
                             WHERE versionofbook.ISBN = ? """;
                
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, ISBN);
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   fullbook.setISBN(rs.getString(1));
                   fullbook.setBookName(new BookNameDTO(rs.getInt(2), rs.getString(3)));
                   fullbook.setImg(rs.getString(4));
                   fullbook.setPublisher(new PublisherDTO(rs.getInt(5), rs.getString(6)));
                   fullbook.setEdition(rs.getString(7));
                   fullbook.setPrice(rs.getInt(8));
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return fullbook;
    }
    
    public Vector<BookDTO> getAllBook() throws SQLException{
        Vector<BookDTO> fullbook = new Vector<BookDTO>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = """
                             SELECT ISBN, bookID, book.name AS bookName, img, publisherID, publisher.name, edition, price, quantity, available, location
                             FROM versionofbook JOIN book ON versionofbook.bookID = book.id 
                             JOIN publisher ON versionofbook.publisherID = publisher.id
                             """;
                
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   BookDTO u = new BookDTO();
                   u.setISBN(rs.getString(1));
                   u.setNameID(rs.getInt(2));
                   u.setName(rs.getString(3));
                   u.setImg(rs.getString(4));
                   u.setPublisher(rs.getString(6));
                   u.setEdition(rs.getString(7));
                   u.setPrice(rs.getLong(8));
                   u.setQuantity(rs.getInt(9));
                   u.setAvailable(rs.getInt(10));
                   u.setLocation(rs.getString(11));
                   fullbook.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return fullbook;
    }
    
    public String isDifferentISBNExist(FullBookDTO fullbook) throws SQLException{
        String ISBN = "";
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM versionofbook WHERE versionofbook.bookID = ? AND versionofbook.publisherID = ? AND UPPER(versionofbook.edition) = UPPER(?)";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setInt(1, fullbook.getBookName().getId());
                stmt.setInt(2, fullbook.getPublisher().getId());
                stmt.setString(3, fullbook.getEdition());
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   ISBN = rs.getString(1);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return ISBN;
    }
    
    public boolean AddImport(ImportDTO importReceipt) throws SQLException {
        boolean flag = false;
        connectDB.connect();

        if (ConnectDB.conn != null) {
            PreparedStatement preparedStatement = null;
            try {
                ConnectDB.conn.setAutoCommit(false); 

                String sql = "INSERT INTO importing(supplierID, staffID, fee) VALUES (?, ?, ?)";
                preparedStatement = ConnectDB.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, importReceipt.getSupplier().getId());
                preparedStatement.setString(2, importReceipt.getAccount().getId());
                preparedStatement.setLong(3, importReceipt.getFee());

                if (preparedStatement.executeUpdate() > 0) {
                    int generatedID = -1; 
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        generatedID = generatedKeys.getInt(1);
                    }

                    for (FullBookDTO u : importReceipt.getFullbooks()) {
                        switch (u.getStatus()) {
                            case "ISBNExisted":
                                sql = "UPDATE versionofbook SET quantity = quantity + ? WHERE ISBN = ?";
                                try (PreparedStatement updateStatement = ConnectDB.conn.prepareStatement(sql)) {
                                    updateStatement.setInt(1, u.getQuantity());
                                    updateStatement.setString(2, u.getISBN());
                                    updateStatement.executeUpdate();
                                }
                                break;

                            case "bookNameExisted":
                                sql = "INSERT INTO versionofbook(ISBN, bookID, img, publisherID, edition, price, quantity) values (?, ?, ?, ?, ?, ?, ?)";
                                try (PreparedStatement insertStatement = ConnectDB.conn.prepareStatement(sql)) {
                                    insertStatement.setString(1, u.getISBN());
                                    insertStatement.setInt(2, u.getBookName().getId());
                                    insertStatement.setString(3, "/asset/img/book/" + u.getISBN() + ".jpg");
                                    insertStatement.setInt(4, u.getPublisher().getId());
                                    insertStatement.setString(5, u.getEdition());
                                    insertStatement.setLong(6, u.getPrice());
                                    insertStatement.setInt(7, u.getQuantity());
                                    insertStatement.executeUpdate();
                                }
                                break;

                            default:
                                sql = "INSERT INTO bookauthor(authorID, bookID) values (?, ?)";
                                for (AuthorDTO au : u.getAuthors()) {
                                    try (PreparedStatement authorStatement = ConnectDB.conn.prepareStatement(sql)) {
                                        authorStatement.setInt(1, au.getId());
                                        authorStatement.setInt(2, u.getBookName().getId());
                                        authorStatement.executeUpdate();
                                    }
                                }

                                sql = "INSERT INTO bookcategory(categoryID, bookID) values (?, ?)";
                                for (CategoryDTO cate : u.getCategories()) {
                                    try (PreparedStatement categoryStatement = ConnectDB.conn.prepareStatement(sql)) {
                                        categoryStatement.setInt(1, cate.getId());
                                        categoryStatement.setInt(2, u.getBookName().getId());
                                        categoryStatement.executeUpdate();
                                    }
                                }

                                sql = "INSERT INTO versionofbook(ISBN, bookID, img, publisherID, edition, price, quantity) values (?, ?, ?, ?, ?, ?, ?)";
                                try (PreparedStatement versionStatement = ConnectDB.conn.prepareStatement(sql)) {
                                    versionStatement.setString(1, u.getISBN());
                                    versionStatement.setInt(2, u.getBookName().getId());
                                    versionStatement.setString(3, "/asset/img/book/" + u.getISBN() + ".jpg");
                                    versionStatement.setInt(4, u.getPublisher().getId());
                                    versionStatement.setString(5, u.getEdition());
                                    versionStatement.setLong(6, u.getPrice());
                                    versionStatement.setInt(7, u.getQuantity());
                                    versionStatement.executeUpdate();
                                }
                                break;
                        }
                    }

                    sql = "INSERT INTO importdetail(importID, ISBN, quantity) values (?, ?, ?)";
                    for (FullBookDTO u : importReceipt.getFullbooks()) {
                        try (PreparedStatement detailStatement = ConnectDB.conn.prepareStatement(sql)) {
                            detailStatement.setInt(1, generatedID);
                            detailStatement.setString(2, u.getISBN());
                            detailStatement.setInt(3, u.getQuantity());
                            detailStatement.executeUpdate();
                        }
                    }

                    ConnectDB.conn.commit();
                    flag = true;
                }

            } catch (SQLException e) {
                try {
                    ConnectDB.conn.rollback(); 
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
                e.printStackTrace();

            } finally {
                connectDB.disconnect(); 
            }
        }

        return flag;
    }

    public List<BookDTO> getAllBookByCondition(Map<String, String> searchConditions) throws SQLException {
        connectDB.connect();
        List<BookDTO> books = new ArrayList<>();
        String sql = generateGetAllBookSQL();
    
        // Tạo điều kiện tìm kiếm
        StringBuilder conditionBuilder = new StringBuilder(" WHERE 1=1");
        if (searchConditions != null && !searchConditions.isEmpty()) {
            for (Map.Entry<String, String> entry : searchConditions.entrySet()) {
                if (conditionBuilder.length() > 0) {
                    conditionBuilder.append(" AND ");
                }
                // Sử dụng LIKE cho điều kiện
                conditionBuilder.append(entry.getKey()).append(" LIKE ?");
            }
        }
    
        // Nếu có điều kiện thì thêm vào câu SQL
        if (conditionBuilder.length() > 0) {
            sql += conditionBuilder.toString();
        }

        System.out.println(sql);
        System.out.println(searchConditions);

        if (ConnectDB.conn != null) {
            try (PreparedStatement pstmt = ConnectDB.conn.prepareStatement(sql)) {
                // Thiết lập giá trị cho PreparedStatement
                int index = 1;
                if (searchConditions != null) {
                    for (Map.Entry<String, String> entry : searchConditions.entrySet()) {
                        pstmt.setString(index++, "%" + entry.getValue() + "%");
                    }
                }
        
                ResultSet rs = pstmt.executeQuery();
                Map<String, BookDTO> bookMap = new HashMap<>();
        
                while (rs.next()) {
                    String isbn = rs.getString("ISBN");
                    BookDTO book = bookMap.get(isbn);
        
                    if (book == null) {
                        book = new BookDTO();
                        book.setISBN(isbn);
                        book.setName(rs.getString("BookName"));
                        book.setNameID(rs.getInt("BookID"));
                        book.setImg(rs.getString("img"));
                        book.setPublisher(rs.getString("PublisherName"));
                        book.setEdition(rs.getString("edition"));
                        book.setLocation(rs.getString("location"));
                        book.setPrice(rs.getLong("price"));
                        book.setQuantity(rs.getInt("quantity"));
                        book.setAvailable(rs.getInt("available"));
                        bookMap.put(isbn, book);
                    }
                }
        
                books.addAll(bookMap.values());
            }
        }
    
        return books;
    }

    private String generateGetAllBookSQL() {
        return "SELECT "
                + "b.id AS BookID, "
                + "b.name AS BookName, "
                + "b.isActive AS BookIsActive, "
                + "v.ISBN, "
                + "v.img, "
                + "v.publisherID, "
                + "p.name AS PublisherName, "
                + "v.edition, "
                + "v.location, "
                + "v.price, "
                + "v.quantity, "
                + "v.available, "
                + "ba.authorID AS AuthorID, "
                + "a.name AS AuthorName, "
                + "bc.categoryID AS CategoryID, "
                + "c.name AS CategoryName "
                + "FROM book b "
                + "LEFT JOIN versionofbook v ON b.id = v.bookID "
                + "LEFT JOIN publisher p ON v.publisherID = p.id "
                + "LEFT JOIN bookauthor ba ON b.id = ba.bookID "
                + "LEFT JOIN author a ON ba.authorID = a.id "
                + "LEFT JOIN bookcategory bc ON b.id = bc.bookID "
                + "LEFT JOIN category c ON bc.categoryID = c.id ";
    }

    public boolean deleteBookByISBN(String isbn) throws SQLException {
        connectDB.connect();
        String checkSql = "SELECT v.available, v.quantity, COALESCE(i.quantity, 0) AS importQuantity " +
                          "FROM versionofbook v " +
                          "LEFT JOIN importdetail i ON v.ISBN = i.ISBN " +
                          "WHERE v.ISBN = ?";
        
        String deleteSql = "DELETE FROM versionofbook WHERE ISBN = ?";
        
        boolean isDeleted = false; // Biến để theo dõi trạng thái xóa
        if (ConnectDB.conn != null) {
            try {
                // Bắt đầu giao dịch
                ConnectDB.conn.setAutoCommit(false); // Tắt chế độ tự động commit
                
                PreparedStatement checkStmt = ConnectDB.conn.prepareStatement(checkSql);
                checkStmt.setString(1, isbn);
                ResultSet rs = checkStmt.executeQuery();
    
                if (rs.next()) {
                    int available = rs.getInt("available");
                    int quantity = rs.getInt("quantity");
                    int importQuantity = rs.getInt("importQuantity");
    
                    // Kiểm tra điều kiện xóa
                    if (available == 0 && quantity == 0 && importQuantity == 0) {
                        // Thực hiện xóa
                        PreparedStatement deleteStmt = ConnectDB.conn.prepareStatement(deleteSql);
                        deleteStmt.setString(1, isbn);
                        int rowsAffected = deleteStmt.executeUpdate();
                        ConnectDB.conn.commit(); // Commit giao dịch
                        isDeleted = (rowsAffected > 0); // Xóa thành công nếu có ít nhất 1 hàng bị xóa
                    } else {
                        System.out.println("Cannot delete book: Condition not met. Book: available != 0 or quantity != 0 or importQuantity != 0");
                    }
                }
            } catch (SQLException e) {
                try {
                    ConnectDB.conn.rollback(); // Rollback nếu có lỗi
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
                e.printStackTrace();
            } finally {
                connectDB.disconnect(); // Đảm bảo kết nối được đóng
            }
        }
        return isDeleted; // Trả về trạng thái xóa
    }

    public boolean arrangeBook(String floor, String shelf, int quantity, String ISBN) throws SQLException{
        boolean flag = true;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String location = floor + "-" + shelf;
                String sql = "UPDATE versionofbook SET available = available + ?, quantity = quantity - ?, location = ? WHERE ISBN = ?";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setInt(1, quantity);
                stmt.setInt(2, quantity);
                stmt.setString(3, location);
                stmt.setString(4, ISBN);
                if(stmt.executeUpdate()>0){
                    flag=true;
                }
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public String getEditionByISBN(String ISBN) throws SQLException{
        String edition = null; 
        connectDB.connect();   

        if (ConnectDB.conn != null) { // Kiểm tra kết nối
            try {
                // Câu lệnh SQL để truy vấn
                String sql = "SELECT edition FROM versionofbook WHERE isbn = ?";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);
                stmt.setString(1, ISBN); // Gán giá trị ISBN vào câu truy vấn

                // Thực thi truy vấn
                ResultSet rs = stmt.executeQuery();

                // Xử lý kết quả
                if (rs.next()) {
                    edition = rs.getString("edition"); // Lấy giá trị cột edition
                }

            } catch (SQLException e) {
                System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
            } finally {
                connectDB.disconnect(); // Đóng kết nối
            }
        }

        return edition; // Trả về phiên bản hoặc null nếu không tìm thấy
    }
}
