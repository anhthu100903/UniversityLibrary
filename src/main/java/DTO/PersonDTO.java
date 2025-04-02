/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author User
 */
public class PersonDTO {
    private String id;
    private String name;
    private String tel;
    private String address;
    private String schoolYear;
    private RoleDTO roleDTO;
    
    public PersonDTO(String id, String name, String tel, String address, String schoolYear) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.schoolYear = schoolYear;
    }

    public PersonDTO(String id, String name, String tel, String address, String schoolYear, RoleDTO roleDTO) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.schoolYear = schoolYear;
        this.roleDTO = roleDTO;
    }
    
    

    public RoleDTO getRoleID() {
        return roleDTO;
    }

    public void setRoleID(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public PersonDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
}
