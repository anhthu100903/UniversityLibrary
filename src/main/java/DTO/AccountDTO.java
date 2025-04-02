/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class AccountDTO {
    private String id;
    private String password;
    private String name;
    private RoleDTO roleDTO;
    private LocalDate dayCreated;

    public AccountDTO(String id, String password, String name, RoleDTO roleDTO, LocalDate dayCreated, boolean isActive) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.roleDTO = roleDTO;
        this.dayCreated = dayCreated;
        this.isActive = isActive;
    }
    
        public AccountDTO(String id, String password, RoleDTO roleDTO, LocalDate dayCreated) {
        this.id = id;
        this.password = password;
        this.roleDTO = roleDTO;
        this.dayCreated = dayCreated;
    }

    public AccountDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public LocalDate getDayCreated() {
        return dayCreated;
    }

    public void setDayCreated(LocalDate dayCreated) {
        this.dayCreated = dayCreated;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    private boolean isActive;
}
