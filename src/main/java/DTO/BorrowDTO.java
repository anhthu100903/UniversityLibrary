/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author User
 */
public class BorrowDTO {

    public BorrowDTO(int id, String readerID, String staffID, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate, boolean delay, long fine, boolean isActive, Vector<BorrowDetailDTO> borrowDetailDTO) {
        this.id = id;
        this.readerID = readerID;
        this.staffID = staffID;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.delay = delay;
        this.fine = fine;
        this.isActive = isActive;
        this.borrowDetailDTO = borrowDetailDTO;
    }

    public BorrowDTO(int id, String readerID, String readerName, String staffID, String staffName, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate, boolean delay, long fine, boolean isActive, Vector<BorrowDetailDTO> borrowDetailDTO) {
        this.id = id;
        this.readerID = readerID;
        this.readerName = readerName;
        this.staffID = staffID;
        this.staffName = staffName;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.delay = delay;
        this.fine = fine;
        this.isActive = isActive;
        this.borrowDetailDTO = borrowDetailDTO;
    }

    public BorrowDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public boolean isDelay() {
        return delay;
    }

    public void setDelay(boolean delay) {
        this.delay = delay;
    }
    
    public long getFine() {
        return fine;
    }

    public void setFine(long fine) {
        this.fine = fine;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Vector<BorrowDetailDTO> getBorrowDetailDTO() {
        return borrowDetailDTO;
    }

    public void setBorrowDetailDTO(Vector<BorrowDetailDTO> borrowDetailDTO) {
        this.borrowDetailDTO = borrowDetailDTO;
    }
    
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    
    private int id;
    private String readerID;
    private String readerName;
    private String staffID;
    private String staffName;
    private LocalDate  borrowDate;
    private LocalDate  dueDate;
    private LocalDate  returnDate;
    private boolean delay;
    private long fine;
    private boolean isActive;
    private Vector<BorrowDetailDTO> borrowDetailDTO;
}
