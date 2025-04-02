/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.PersonBUS;
import DTO.PersonDTO;
import DTO.RoleDTO;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class StaffDetail extends javax.swing.JPanel {
    private PersonDTO personDTO;
    StaffGUI staffGUI;
    /**
     * Creates new form StaffDetail
     */
    public StaffDetail(StaffGUI staffGUI) {
        initComponents();
        this.staffGUI = staffGUI;
        showStaffDetail();
    }
    
    public void showStaffDetail(){
        if (personDTO == null){
            panelBorder1.setVisible(false);
        }else{
            panelBorder1.setVisible(true);
            setValue(); //Mấy cái cá nhân nhét hết vô hàm này
            loadValueStaff(personDTO);
        }
    }
    
    public void setPersonDTO(PersonDTO personDTO){
        this.personDTO = personDTO;
    }
    
    public void setValue(){
        
    }
    
    public void loadValueStaff(PersonDTO personDTO){
        staffIdLabel.setText(personDTO.getId());
        roleComboBox.setSelectedItem(personDTO.getRoleID().getName());
        readerNameTextField.setText(personDTO.getName());
        readerTelTextField.setText(personDTO.getTel());
        readerAddressTextField.setText(personDTO.getAddress());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new MyDesign.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        readerNameTextField = new MyDesign.MyTextField_Basic();
        jLabel4 = new javax.swing.JLabel();
        readerTelTextField = new MyDesign.MyTextField_Basic();
        jLabel5 = new javax.swing.JLabel();
        readerAddressTextField = new MyDesign.MyTextField_Basic();
        staffIdLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        editStaffButton = new MyDesign.MyButton();
        deleteStaffButton1 = new MyDesign.MyButton();
        roleComboBox = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("THÔNG TIN NHÂN VIÊN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã nhân viên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tên độc giả");

        readerNameTextField.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Số điện thoại");

        readerTelTextField.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Địa chỉ");

        readerAddressTextField.setForeground(new java.awt.Color(0, 0, 0));

        staffIdLabel.setText("2021");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Vai trò");

        editStaffButton.setBackground(new java.awt.Color(22, 113, 221));
        editStaffButton.setForeground(new java.awt.Color(255, 255, 255));
        editStaffButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/action-refresh-white.png"))); // NOI18N
        editStaffButton.setColor(new java.awt.Color(22, 113, 221));
        editStaffButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editStaffButton.setLabel("Cập nhật ");
        editStaffButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editStaffButtonActionPerformed(evt);
            }
        });

        deleteStaffButton1.setForeground(new java.awt.Color(248, 67, 67));
        deleteStaffButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/action-delete-white.png"))); // NOI18N
        deleteStaffButton1.setColor(new java.awt.Color(255, 241, 241));
        deleteStaffButton1.setColorOver(new java.awt.Color(255, 241, 241));
        deleteStaffButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteStaffButton1.setLabel("Xoá ");
        deleteStaffButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStaffButton1ActionPerformed(evt);
            }
        });

        roleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Thủ kho", "Thủ thư" }));
        roleComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(staffIdLabel))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(readerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(readerTelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(readerAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(74, 74, 74))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(editStaffButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteStaffButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(staffIdLabel))
                .addGap(15, 15, 15)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(roleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(readerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(readerTelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(readerAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editStaffButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteStaffButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editStaffButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editStaffButtonActionPerformed
        // TODO add your handling code here:
        if (personDTO == null) {
            JOptionPane.showMessageDialog(this, "Không có thông tin nhân viên để cập nhật!");
            return;
        }
        String newName = readerNameTextField.getText();
        String newTel = readerTelTextField.getText();
        String newAddress = readerAddressTextField.getText();

         if (newName.isEmpty() || newTel.isEmpty() || newAddress.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        if (!newName.matches("^[\\p{L}\\s]+$")){
            JOptionPane.showMessageDialog(this, "Tên không hợp lệ!");
            return;
        }
        if (!newTel.matches("^\\d{10}$")){
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
            return;
        }
        if(personDTO.getRoleID().getId().equals("QL")){
            JOptionPane.showMessageDialog(this, "Không thể chỉnh sửa người có chức vụ này!");
            return;
        }
        
        int r = roleComboBox.getSelectedIndex();
        switch(r){
            case 0:
                personDTO.setRoleID(new RoleDTO("QL", "Quản lý"));
                break;
            case 1:
                personDTO.setRoleID(new RoleDTO("TK", "Thủ kho"));
                break;
            case 2:
                personDTO.setRoleID(new RoleDTO("TT", "Thủ thư"));
                break;
        }

        personDTO.setName(newName);
        personDTO.setTel(newTel);
        personDTO.setAddress(newAddress);

        boolean isUpdated = new PersonBUS().updateStaff(personDTO); // Gọi BUS để cập nhật
        if (isUpdated) {
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên thành công!");
            staffGUI.loadStaffTable();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin thất bại!");
        }
        
    }//GEN-LAST:event_editStaffButtonActionPerformed

    private void deleteStaffButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStaffButton1ActionPerformed
        // TODO add your handling code here:
        if (personDTO == null) {
            JOptionPane.showMessageDialog(this, "Không có thông tin độc giả để xóa!");
            return;
        }
        if(personDTO.getRoleID().getId().equals("QL")){
            JOptionPane.showMessageDialog(this, "Không thể chỉnh sửa người có chức vụ này!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa nhân viên này?", 
            "Xác nhận", JOptionPane.YES_NO_OPTION);
        boolean isDeleted = new PersonBUS().deletePerson(personDTO.getId());
        if (confirm == JOptionPane.YES_OPTION) {
            if (isDeleted) {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
                personDTO = null; // Xóa thông tin đang hiển thị
                showStaffDetail();
                staffGUI.loadStaffTable();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại!");
            }
        }   
    }//GEN-LAST:event_deleteStaffButton1ActionPerformed

    private void roleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roleComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton deleteStaffButton1;
    private MyDesign.MyButton editStaffButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.MyTextField_Basic readerAddressTextField;
    private MyDesign.MyTextField_Basic readerNameTextField;
    private MyDesign.MyTextField_Basic readerTelTextField;
    private javax.swing.JComboBox<String> roleComboBox;
    private javax.swing.JLabel staffIdLabel;
    // End of variables declaration//GEN-END:variables
}
