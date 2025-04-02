/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author User
 */
public class BorrowDetailDTO {

    private int borrowID;
    private String ISBN;
    private String bookName;
    private String description;
    private int quantity;
    private int lost;
    private int broke;


    public BorrowDetailDTO(String ISBN, String bookName, String description, int quantity, int lost, int broke) {
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.description = description;
        this.quantity = quantity;
        this.lost = lost;
        this.broke = broke;
    }

    public BorrowDetailDTO(int borrowID, String ISBN, String bookName, String description, int quantity) {
        this.borrowID = borrowID;
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.description = description;
        this.quantity = quantity;
    }
    
    public int getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(int borrowID) {
        this.borrowID = borrowID;
    }

    public BorrowDetailDTO() {
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getBroke() {
        return broke;
    }

    public void setBroke(int broke) {
        this.broke = broke;
    }
}
