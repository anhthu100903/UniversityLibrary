/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PersonDAO;
import DTO.PersonDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class PersonBUS {
    private PersonDAO personDAO;

    public PersonBUS() {
        personDAO = new PersonDAO();
    }

    public List<PersonDTO> getAllStaff() throws Exception {
        return personDAO.getAllStaff();
    }
    
    public List<PersonDTO> getAllReader() throws Exception {
        return personDAO.getAllReaders();
    }
    
    public List<PersonDTO> searchAllPerson(String searchText, Vector<String> role, boolean isReader) throws Exception {
        return personDAO.searchAllPerson(searchText, role, isReader);
    }
    
    public PersonDTO getPersonById(String id){
        try {
            return personDAO.getPersonById(id);
        } catch (SQLException ex) {
            Logger.getLogger(PersonBUS.class.getName()).log(Level.SEVERE, null, ex);
        }return null;
    }
    
    public boolean addPerson(PersonDTO person) {
        try {
            return personDAO.addPerson(person);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean isPersonIdExist(String id) throws SQLException{
        return personDAO.isPersonIdExist(id);
    }
    
    public boolean isPersonPhoneExist(String phone) throws SQLException{
        return personDAO.isPersonPhoneExist(phone);
    }

    public boolean updatePerson(PersonDTO person) {
        try {
            return personDAO.updatePerson(person);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateStaff(PersonDTO person) {
        try {
            return personDAO.updateStaff(person);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePerson(String id) {
        try {
            return personDAO.deletePerson(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<PersonDTO> searchPerson(String keyword) {
        try {
            return personDAO.searchPerson(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public List<PersonDTO> searchReadersWithRole(String keyword, boolean isSinhVienChecked, boolean isGiangVienChecked) {
    try {
        return personDAO.searchReadersWithRole(keyword, isSinhVienChecked, isGiangVienChecked);
    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}

}
