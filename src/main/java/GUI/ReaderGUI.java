/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.AccountBUS;
import BUS.PersonBUS;
import DTO.AccountDTO;
import DTO.BorrowDTO;
import DTO.BorrowDetailDTO;
import DTO.PersonDTO;
import DTO.RoleDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ReaderGUI extends javax.swing.JPanel implements BarcodeListener {
    Scanner_Dialog scannerDialog = new Scanner_Dialog();
    String idScan = "";
    PersonBUS personBus;
    AccountBUS accountBUS;
    List<PersonDTO> listReader;
    /**
     * Creates new form ReaderGUI
     */
    public ReaderGUI() {
        personBus = new PersonBUS();
        try {
            accountBUS = new AccountBUS();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReaderGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReaderGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReaderGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        SpinnerNumberModel startYearModel = new SpinnerNumberModel(currentYear, currentYear - 10, currentYear, 1);
        SpinnerNumberModel endYearModel = new SpinnerNumberModel(currentYear, currentYear, currentYear + 10, 1);
        startYearSpinner.setModel(startYearModel);
        endYearSpinner.setModel(endYearModel);
        
        buttonGroup1.add(sinhvienRadioButton);
        buttonGroup1.add(giangvienRadioButton);
        jPanel2.setVisible(false);
        
        myButton1.addActionListener(evt -> addReader());
        loadReaderData(readerTable);
        ClickReaderTable();
        
    }
    
    public void setUpTable(){
        readerTable.setRowCount(0);
            
        for (PersonDTO reader : listReader) {
            RoleDTO role = reader.getRoleID();
            System.out.println("GUI.ReaderGUI.loadReaderData(): " + role.getId());
            System.out.println("GUI.ReaderGUI.loadReaderData(): " + role.getId());
            Object[] row = new Object[]{
                reader.getId(),
                reader.getName(),
                reader.getTel(),
                role.getId().equals("SV") ? "Sinh viên" : "Giảng viên"
            };
            readerTable.addRow(row);
        }
    }
    
    public void loadReaderData(javax.swing.JTable borrowReceiptTable) {
        try {
            listReader = personBus.getAllReader();
            DefaultTableModel model = (DefaultTableModel) readerTable.getModel();
            model.setRowCount(0);
            
            for (PersonDTO reader : listReader) {
                RoleDTO role = reader.getRoleID();
                System.out.println("GUI.ReaderGUI.loadReaderData(): " + role.getId());
                System.out.println("GUI.ReaderGUI.loadReaderData(): " + role.getId());
                Object[] row = new Object[]{
                    reader.getId(),
                    reader.getName(),
                    reader.getTel(),
                    role.getId().equals("SV") ? "Sinh viên" : "Giảng viên"
                };
                model.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(ReaderGUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    public void addReader(){
        String id = readerIDTextField.getText().trim();
        String name = readerNameTextField.getText().trim();
        String tel = readerTelTextField.getText().trim();
        String address = readerAddressTextField.getText().trim();
        String schoolYear="";
        
        if (id.equals("") || name.equals("") || tel.equals("") || address.equals("")){
            JOptionPane.showMessageDialog(this, "Không được bỏ trống trường nào");
            return;
        }
        if (!id.matches("^\\d{10}$")){
            JOptionPane.showMessageDialog(this, "Mã độc giả không hợp lệ");
            return;
        }
        if (!name.matches("^[\\p{L}\\s]+$")){
            JOptionPane.showMessageDialog(this, "Tên không hợp lệ!");
            return;
        }
        if (!tel.matches("^\\d{10}$")){
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
            return;
        }
        if (!sinhvienRadioButton.isSelected() && !giangvienRadioButton.isSelected()){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chức vụ!");
            return;
        }
        try {
            if (personBus.isPersonIdExist(id)){
                JOptionPane.showMessageDialog(this, "Mã số này đã tồn tại!");
                return;
            }
            if (personBus.isPersonPhoneExist(tel)){
                JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại!");
                return;
            } 
        } catch (SQLException ex) {
            Logger.getLogger(ReaderGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        RoleDTO role = new RoleDTO();
        if(sinhvienRadioButton.isSelected()){
            Integer startYear = Integer.valueOf(startYearSpinner.getValue().toString());
            Integer endYear = Integer.valueOf(endYearSpinner.getValue().toString());
            schoolYear = startYear + "-" + endYear;
            
            role.setId("SV");
            role.setName("Sinh viên");
        } else {
            role.setId("GV");
            role.setName("Giảng viên");
            schoolYear = "";
        }
        
        PersonDTO per = new PersonDTO(id, name, tel, address, schoolYear, role);
        
        if(personBus.addPerson(per)){
            JOptionPane.showMessageDialog(this, "Thêm độc giả thành công!");
            refreshReaderTable();
        }else{
            JOptionPane.showMessageDialog(this, "Thêm độc giả thất bại!");
        }      
    }

    
    private void ClickReaderTable() {
        readerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = readerTable.getSelectedRow();
                System.out.println(".mouseClicked()");
                if (selectedRow >= 0) {
                    PersonDTO person = listReader.get(selectedRow);
                    if(person==null){
                        System.out.println(".mouseClicked()");
                        return;
                    }
                    readerDetail1.setPersonDTO(person);
                    readerDetail1.showReaderDetail();
                }
            }
        });

    }
    
    public void refreshReaderTable() {
        loadReaderData(readerTable);
    }
        
    public void searchReaderData(javax.swing.JTable readerTable, String keyword, boolean isSinhVien, boolean isGiangVien) throws Exception {
        listReader = personBus.getAllReader();
        DefaultTableModel model = (DefaultTableModel) readerTable.getModel();
        model.setRowCount(0); // Xóa dữ liệu bảng hiện tại

        for (PersonDTO reader : listReader) {
            // Kiểm tra từ khóa có tồn tại trong tên độc giả hoặc ID độc giả
            boolean matchesKeyword = keyword.isEmpty()
                    || reader.getName().toLowerCase().contains(keyword.toLowerCase())
                    || reader.getId().toLowerCase().contains(keyword.toLowerCase());

            // Kiểm tra loại độc giả (Sinh viên hoặc Giảng viên)
            String roleId = reader.getRoleID().getId(); // Lấy ID vai trò
            boolean matchesRole = !isSinhVien && !isGiangVien
                    || (isSinhVien && "SV".equals(roleId))
                    || (isGiangVien && "GV".equals(roleId));

            // Chỉ thêm vào bảng nếu thỏa mãn cả từ khóa và loại độc giả
            if (matchesKeyword && matchesRole) {
                Object[] row = new Object[]{
                    reader.getId(),
                    reader.getName(),
                    reader.getTel(),
                    roleId.equals("SV") ? "Sinh viên" : "Giảng viên"
                };
                model.addRow(row);
            }
        }
}
    private void performReaderSearch() throws Exception {
        
        String keyword = txtFindReader.getText().trim(); // Lấy từ khóa từ ô tìm kiếm
        boolean isSinhVien = sinhVienCheckBox.isSelected(); // Kiểm tra trạng thái "Sinh viên"
        boolean isGiangVien = giangVienCheckBox.isSelected(); // Kiểm tra trạng thái "Giảng viên"
        searchReaderData(readerTable, keyword, isSinhVien, isGiangVien);
}
    
    private void search(){
        String keyword = txtFindReader.getText().trim();
        Vector<String> role = new Vector<String>();
        if (sinhVienCheckBox.isSelected()){
            role.add("SV");
        }
        if (giangVienCheckBox.isSelected()){
            role.add("GV");
        }
        try {
            listReader = personBus.searchAllPerson(keyword, role, true);
            setUpTable();
        } catch (Exception ex) {
            Logger.getLogger(ReaderGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        readerDetail1 = new GUI.ReaderDetail(this);
        panelBorder1 = new MyDesign.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        readerIDTextField = new MyDesign.MyTextField_Basic();
        scanReaderButton = new MyDesign.MyButton();
        jLabel6 = new javax.swing.JLabel();
        giangvienRadioButton = new javax.swing.JRadioButton();
        sinhvienRadioButton = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        readerNameTextField = new MyDesign.MyTextField_Basic();
        jLabel8 = new javax.swing.JLabel();
        readerTelTextField = new MyDesign.MyTextField_Basic();
        jLabel9 = new javax.swing.JLabel();
        readerAddressTextField = new MyDesign.MyTextField_Basic();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        startYearSpinner = new javax.swing.JSpinner();
        endYearSpinner = new javax.swing.JSpinner();
        myButton1 = new MyDesign.MyButton();
        jLabel2 = new javax.swing.JLabel();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        jLabel11 = new javax.swing.JLabel();
        txtFindReader = new MyDesign.SearchText();
        sinhVienCheckBox = new javax.swing.JCheckBox();
        giangVienCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        readerTable = new MyDesign.MyTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("THÊM ĐỘC GIẢ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mã độc giả");

        scanReaderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/scan.png"))); // NOI18N
        scanReaderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanReaderButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Vai trò");

        giangvienRadioButton.setText("Giảng viên");
        giangvienRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                giangvienRadioButtonActionPerformed(evt);
            }
        });

        sinhvienRadioButton.setText("Sinh viên");
        sinhvienRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinhvienRadioButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tên độc giả");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Số điện thoại");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Địa chỉ");

        readerAddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readerAddressTextFieldActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(246, 250, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Năm học");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startYearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(endYearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startYearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endYearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        myButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/add.png"))); // NOI18N
        myButton1.setText("Thêm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("DANH SÁCH ĐỘC GIẢ");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/search.png"))); // NOI18N

        txtFindReader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindReaderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        panelBorder_Basic1Layout.setHorizontalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFindReader, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder_Basic1Layout.setVerticalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                        .addComponent(txtFindReader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        sinhVienCheckBox.setText("Sinh viên");
        sinhVienCheckBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sinhVienCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinhVienCheckBoxActionPerformed(evt);
            }
        });

        giangVienCheckBox.setText("Giảng viên");
        giangVienCheckBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        giangVienCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                giangVienCheckBoxActionPerformed(evt);
            }
        });

        readerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Số điện thoại", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(readerTable);
        if (readerTable.getColumnModel().getColumnCount() > 0) {
            readerTable.getColumnModel().getColumn(0).setResizable(false);
            readerTable.getColumnModel().getColumn(1).setResizable(false);
            readerTable.getColumnModel().getColumn(2).setResizable(false);
            readerTable.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(readerIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scanReaderButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(sinhvienRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(giangvienRadioButton)
                        .addGap(48, 48, 48))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(readerTelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(readerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(128, 128, 128)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(readerAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 31, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sinhVienCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(giangVienCheckBox)
                        .addGap(21, 21, 21))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(readerIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scanReaderButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sinhvienRadioButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(giangvienRadioButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(readerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(readerTelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(readerAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(sinhVienCheckBox)
                                    .addComponent(giangVienCheckBox))))
                        .addContainerGap(270, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(readerDetail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(readerDetail1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scanReaderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanReaderButtonActionPerformed
        // TODO add your handling code here:
        scannerDialog.initAndShowGUI(this);
    }//GEN-LAST:event_scanReaderButtonActionPerformed

    private void readerAddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readerAddressTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_readerAddressTextFieldActionPerformed

    private void sinhvienRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinhvienRadioButtonActionPerformed
        // TODO add your handling code here:
        jPanel2.setVisible(true);     
    }//GEN-LAST:event_sinhvienRadioButtonActionPerformed

    private void giangvienRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_giangvienRadioButtonActionPerformed
        // TODO add your handling code here:
        jPanel2.setVisible(false);
    }//GEN-LAST:event_giangvienRadioButtonActionPerformed

    private void txtFindReaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindReaderActionPerformed
        search();
    }//GEN-LAST:event_txtFindReaderActionPerformed

    private void sinhVienCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinhVienCheckBoxActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_sinhVienCheckBoxActionPerformed

    private void giangVienCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_giangVienCheckBoxActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_giangVienCheckBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JSpinner endYearSpinner;
    private javax.swing.JCheckBox giangVienCheckBox;
    private javax.swing.JRadioButton giangvienRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private MyDesign.MyButton myButton1;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private MyDesign.MyTextField_Basic readerAddressTextField;
    private GUI.ReaderDetail readerDetail1;
    private MyDesign.MyTextField_Basic readerIDTextField;
    private MyDesign.MyTextField_Basic readerNameTextField;
    private MyDesign.MyTable readerTable;
    private MyDesign.MyTextField_Basic readerTelTextField;
    private MyDesign.MyButton scanReaderButton;
    private javax.swing.JCheckBox sinhVienCheckBox;
    private javax.swing.JRadioButton sinhvienRadioButton;
    private javax.swing.JSpinner startYearSpinner;
    private MyDesign.SearchText txtFindReader;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onBarcodeScanned(String barcode) {
        idScan = barcode;
        System.out.println("Scanned barcode in MainClass: " + barcode);
    }
}
