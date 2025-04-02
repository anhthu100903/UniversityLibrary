/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;
import java.util.Vector;

/**
 *
 * @author User
 */
public class ImportDTO {

    public ImportDTO() {
        this.fullbooks = new Vector<FullBookDTO>();
        this.supplier = new SupplierDTO();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public LocalDate getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public Vector<FullBookDTO> getFullbooks() {
        return fullbooks;
    }

    public void setFullbooks(Vector<FullBookDTO> fullbooks) {
        this.fullbooks = fullbooks;
    }
    
    private int id;
    private SupplierDTO supplier;
    private LocalDate importDate;
    private long fee;
    private Vector<FullBookDTO> fullbooks;
    private AccountDTO account;

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}
