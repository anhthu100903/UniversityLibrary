/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author User
 */
public class PublisherDTO {

    public PublisherDTO(String name) {
        this.name = name;
    }
    private int id;
    private String name;

    public PublisherDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PublisherDTO() {
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
}
