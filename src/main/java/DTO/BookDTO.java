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
public class BookDTO {
    private String ISBN;
    private int nameID;

    public int getNameID() {
        return nameID;
    }

    public void setNameID(int nameID) {
        this.nameID = nameID;
    }
    private String name;
    private String img;
    private String publisher;
    private Vector<String> authors;
    private Vector<String> categories;
    private String edition;
    private String location;
    private long price;
    private int quantity;
    private int available;
    
    public BookDTO(String ISBN, String name, String img, String publisher, Vector<String> authors, Vector<String> categories, String edition, String location, long price, int quantity, int available) {
        this.ISBN = ISBN;
        this.name = name;
        this.img = img;
        this.publisher = publisher;
        this.authors = authors;
        this.categories = categories;
        this.edition = edition;
        this.location = location;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
    }

    public BookDTO() {
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Vector<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Vector<String> authors) {
        this.authors = authors;
    }

    public Vector<String> getCategories() {
        return categories;
    }

    public void setCategories(Vector<String> categories) {
        this.categories = categories;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
