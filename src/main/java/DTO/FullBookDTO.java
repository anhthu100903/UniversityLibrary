/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Vector;

/**
 *
 * @author User
 */
public class FullBookDTO {

    public FullBookDTO(String ISBN, BookNameDTO bookName, String img, PublisherDTO publisher, Vector<AuthorDTO> authors, Vector<CategoryDTO> categories, String edition, long price, int quantity, String status) {
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.img = img;
        this.publisher = publisher;
        this.authors = authors;
        this.categories = categories;
        this.edition = edition;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public FullBookDTO() {
        this.bookName = new BookNameDTO();
        this.publisher = new PublisherDTO();
        this.authors = new Vector<AuthorDTO>();
        this.categories = new Vector<CategoryDTO>();
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public BookNameDTO getBookName() {
        return bookName;
    }

    public void setBookName(BookNameDTO bookName) {
        this.bookName = bookName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public PublisherDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDTO publisher) {
        this.publisher = publisher;
    }

    public Vector<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Vector<AuthorDTO> authors) {
        this.authors = authors;
    }

    public Vector<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Vector<CategoryDTO> categories) {
        this.categories = categories;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    private String ISBN;
    private BookNameDTO bookName;
    private String img;
    private PublisherDTO publisher;
    private Vector<AuthorDTO> authors;
    private Vector<CategoryDTO> categories;
    private String edition;
    private long price;
    private int quantity;
    private String status;
}
