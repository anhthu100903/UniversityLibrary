/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author User
 */
public class AuthorDTO {

    public AuthorDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
    private int id;
    private String name;
    private int year;

    public AuthorDTO(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public AuthorDTO(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public AuthorDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
