/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import BUS.BookBUS;
import DTO.AccountDTO;
import DTO.AuthorDTO;
import DTO.BookNameDTO;
import DTO.CategoryDTO;
import DTO.FullBookDTO;
import DTO.ImportDTO;
import DTO.PublisherDTO;
import DTO.SupplierDTO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author User
 */
public class AddBookDialog extends javax.swing.JDialog {
    DecimalFormat formatter = new DecimalFormat("#,###");
    BookBUS bookBUS;
    ImportDTO importDTO = new ImportDTO();
    long total = 0;
    /**
     * Creates new form AddBookDialog
     */
    public AddBookDialog(java.awt.Frame parent, boolean modal, AccountDTO user) {
        super(parent, modal);
        initComponents();
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        setLocationRelativeTo(null);
        
        importDTO.setAccount(user);
        
        try {
            bookBUS = new BookBUS();
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(AddBookDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        staffIDLabel.setText(importDTO.getAccount().getId());
        
    }
    
    private void updateTable(){
        long each;
        total = 0;
        importTable.setRowCount(0);
        for(int i=0;i<importDTO.getFullbooks().size();i++){
            each = importDTO.getFullbooks().get(i).getPrice() * (long) importDTO.getFullbooks().get(i).getQuantity();
            total += each;
            Object row[] = {importDTO.getFullbooks().get(i).getISBN(), importDTO.getFullbooks().get(i).getQuantity(), importDTO.getFullbooks().get(i).getPrice(), each};
            importTable.addRow(row);
        }
        priceLabel.setText(formatter.format(total) + " VNĐ");
    }
    
    private boolean checkBookError() throws SQLException{
        if (!addBook1.getISBNTextField().matches("^97[89]\\d{10}$")){
            JOptionPane.showMessageDialog(null, "ISBN không được bỏ trống và phải thuộc cấu trúc ISBN-13");
            return true;
        }
        if (!addBook1.fullbook.getStatus().equals("ISBNExisted") && bookBUS.getFullBook(addBook1.getISBNTextField()).getISBN() != null){
            JOptionPane.showMessageDialog(null, "Đã có sách với mã ISBN tồn tại trong csdl");
            return true;
        }
        if (addBook1.fullbook.getBookName().getName() == null || addBook1.fullbook.getBookName().getName().isBlank()){
            JOptionPane.showMessageDialog(null, "Tên sách không được bỏ trống");
            return true;
        }
        if (addBook1.fullbook.getAuthors().size() <= 0){
            JOptionPane.showMessageDialog(null, "Tác giả không được bỏ trống");
            return true;
        }
        if (addBook1.getEditionTextField().equals("")){
            JOptionPane.showMessageDialog(null, "Phiên bản không được bỏ trống");
            return true;
        }
        if (addBook1.getPublisherLabel().equals("")){
            JOptionPane.showMessageDialog(null, "Nhà xuất bản không được bỏ trống");
            return true;
        }
        if (addBook1.fullbook.getCategories().size() <= 0){
            JOptionPane.showMessageDialog(null, "Thể loại không được bỏ trống");
            return true;
        }
        try{
            if (addBook1.getPriceTextField() <= 0){
                JOptionPane.showMessageDialog(null, "Số tiến không được bé hơn hoặc bằng 0");
                return true;
            }
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Giá tiền phải là số nguyên");
            return true;
        }
        if (addBook1.imgURL.equals("") && (addBook1.fullbook.getImg() == null || addBook1.fullbook.getImg().equals("/asset/img/AddImage.png"))){
            JOptionPane.showMessageDialog(null, "Ảnh sách không được bỏ trống");
            return true;
        }
        return false;
    }
    
    private void saveImage(String bookImgPath, String ISBN){
        File sourceFile = new File(bookImgPath);
        if (!sourceFile.isFile()) {
            JOptionPane.showMessageDialog(null, "Đường dẫn không phải là một tệp hợp lệ!");
            return;
        }
        Path targetPath = Path.of("src/main/resources/asset/img/book/", ISBN + ".jpg");
        
        try {
            Files.createDirectories(targetPath.getParent());
            Files.copy(sourceFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(1);
        } catch (IOException ex) {
            System.out.println(0);
            JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra. Không lưu hình được");
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

        panelBorder_Statistic_Blue1 = new MyDesign.PanelBorder_Statistic_Blue();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        addBook1 = new GUI.AddBook();
        removeBookButton = new MyDesign.MyButton();
        addBookButton = new MyDesign.MyButton();
        updateBookButton = new MyDesign.MyButton();
        jLabel4 = new javax.swing.JLabel();
        panelBorder_Basic2 = new MyDesign.PanelBorder_Basic();
        panelBorder1 = new MyDesign.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        importTable = new MyDesign.MyTable();
        jLabel5 = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        staffIDLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        SupplierNameLabel = new javax.swing.JLabel();
        findBookNameButton = new MyDesign.MyButton();
        addImportReceiptButton = new MyDesign.MyButton();
        resetBookButton = new MyDesign.MyButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        removeBookButton.setBackground(new java.awt.Color(255, 102, 102));
        removeBookButton.setForeground(new java.awt.Color(255, 255, 255));
        removeBookButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/tru.png"))); // NOI18N
        removeBookButton.setText("Bỏ sách");
        removeBookButton.setBorderColor(new java.awt.Color(255, 102, 102));
        removeBookButton.setColor(new java.awt.Color(255, 102, 102));
        removeBookButton.setColorOver(new java.awt.Color(255, 153, 153));
        removeBookButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        removeBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBookButtonActionPerformed(evt);
            }
        });

        addBookButton.setBackground(new java.awt.Color(76, 175, 80));
        addBookButton.setForeground(new java.awt.Color(255, 255, 255));
        addBookButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/add.png"))); // NOI18N
        addBookButton.setText("Thêm sách");
        addBookButton.setColor(new java.awt.Color(76, 175, 80));
        addBookButton.setColorOver(new java.awt.Color(0, 255, 102));
        addBookButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookButtonActionPerformed(evt);
            }
        });

        updateBookButton.setForeground(new java.awt.Color(51, 51, 51));
        updateBookButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/action-refresh.png"))); // NOI18N
        updateBookButton.setText("Cập nhật");
        updateBookButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        updateBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        panelBorder_Basic1Layout.setHorizontalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
                        .addComponent(addBook1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
                        .addComponent(addBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(removeBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(updateBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))))
        );
        panelBorder_Basic1Layout.setVerticalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addBook1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                        .addComponent(removeBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(addBookButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateBookButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nhập sách");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("DANH SÁCH NHẬP");

        importTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        importTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(importTable);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Giá tiền");

        priceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Mã nhân viên");

        staffIDLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Nhà cung cấp");

        findBookNameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/search.png"))); // NOI18N
        findBookNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findBookNameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(SupplierNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(findBookNameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(staffIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(priceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SupplierNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(findBookNameButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(staffIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        addImportReceiptButton.setBackground(new java.awt.Color(22, 113, 221));
        addImportReceiptButton.setForeground(new java.awt.Color(255, 255, 255));
        addImportReceiptButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/add.png"))); // NOI18N
        addImportReceiptButton.setText("Tạo phiếu nhập");
        addImportReceiptButton.setColor(new java.awt.Color(22, 113, 221));
        addImportReceiptButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addImportReceiptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addImportReceiptButtonActionPerformed(evt);
            }
        });

        resetBookButton.setBackground(new java.awt.Color(158, 158, 158));
        resetBookButton.setForeground(new java.awt.Color(255, 255, 255));
        resetBookButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/action-refresh-white.png"))); // NOI18N
        resetBookButton.setText("Làm mới sách nhập");
        resetBookButton.setColor(new java.awt.Color(158, 158, 158));
        resetBookButton.setColorClick(new java.awt.Color(204, 204, 204));
        resetBookButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        resetBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBookButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder_Basic2Layout = new javax.swing.GroupLayout(panelBorder_Basic2);
        panelBorder_Basic2.setLayout(panelBorder_Basic2Layout);
        panelBorder_Basic2Layout.setHorizontalGroup(
            panelBorder_Basic2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Basic2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Basic2Layout.createSequentialGroup()
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addComponent(resetBookButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addImportReceiptButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder_Basic2Layout.setVerticalGroup(
            panelBorder_Basic2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(resetBookButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addImportReceiptButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelBorder_Statistic_Blue1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue1);
        panelBorder_Statistic_Blue1.setLayout(panelBorder_Statistic_Blue1Layout);
        panelBorder_Statistic_Blue1Layout.setHorizontalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                        .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBorder_Basic2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder_Statistic_Blue1Layout.setVerticalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder_Basic2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder_Statistic_Blue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder_Statistic_Blue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void findBookNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findBookNameButtonActionPerformed
        // TODO add your handling code here:
        ChooseSupplierDialog whid = new ChooseSupplierDialog(new javax.swing.JFrame(), true, this.importDTO.getSupplier(), this::updateSupplier);
        whid.setVisible(true);
    }//GEN-LAST:event_findBookNameButtonActionPerformed

    private void addBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookButtonActionPerformed
        try {
            // TODO add your handling code here:
            if (checkBookError()){
                return;
            }         
            
        } catch (SQLException ex) {
            Logger.getLogger(AddBookDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < importDTO.getFullbooks().size(); i++){
           if (addBook1.getISBNTextField().equals(importDTO.getFullbooks().get(i).getISBN())){
               JOptionPane.showMessageDialog(null, "Đã có sách với mã ISBN tồn tại trong đơn nhập");
                return;
           }
        }
        
        try {
            String tmpISBN = bookBUS.isDifferentISBNExist(addBook1.fullbook);
            if (!tmpISBN.equals("")){
                if(!tmpISBN.equals(addBook1.getISBNTextField())){
                    JOptionPane.showMessageDialog(null, "Đã có sách với những thông tin này tòn tại trong cơ sở dữ liệu");
                    return;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddBookDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addBook1.fullbook.setQuantity((int) addBook1.getQuantitySpinner().getValue());
        Vector<AuthorDTO> authors = new Vector<AuthorDTO>();
        for (AuthorDTO au: addBook1.fullbook.getAuthors()){
            authors.add(au);
        }
        Vector<CategoryDTO> categories = new Vector<CategoryDTO>();
        for (CategoryDTO au: addBook1.fullbook.getCategories()){
            categories.add(au);
        }
        switch(addBook1.fullbook.getStatus()){
            case "ISBNExisted" -> importDTO.getFullbooks().add(new FullBookDTO(addBook1.fullbook.getISBN(), new BookNameDTO(addBook1.fullbook.getBookName().getId(), addBook1.fullbook.getBookName().getName()), addBook1.fullbook.getImg(), new PublisherDTO(addBook1.fullbook.getPublisher().getId(), addBook1.fullbook.getPublisher().getName()), authors, categories, addBook1.fullbook.getEdition(), addBook1.fullbook.getPrice(), addBook1.fullbook.getQuantity(), addBook1.fullbook.getStatus()));
            default -> importDTO.getFullbooks().add(new FullBookDTO(addBook1.getISBNTextField(), new BookNameDTO(addBook1.fullbook.getBookName().getId(), addBook1.fullbook.getBookName().getName()), addBook1.imgURL, new PublisherDTO(addBook1.fullbook.getPublisher().getId(), addBook1.fullbook.getPublisher().getName()), authors, categories, addBook1.getEditionTextField(), addBook1.getPriceTextField(), addBook1.fullbook.getQuantity(), addBook1.fullbook.getStatus()));
        }
        updateTable();
        addBook1.resetBook();
    }//GEN-LAST:event_addBookButtonActionPerformed

    private void removeBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBookButtonActionPerformed
        // TODO add your handling code here:
        int row = importTable.getSelectedRow();
        if (row >= 0) {
            importDTO.getFullbooks().remove(row);
            updateTable();
        }else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sách muốn bỏ.");
        }  
    }//GEN-LAST:event_removeBookButtonActionPerformed

    private void importTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importTableMouseClicked
        // TODO add your handling code here:
        int row = importTable.getSelectedRow();
        if (row >= 0) {
            addBook1.fullbook = importDTO.getFullbooks().get(row);
            addBook1.setUpBookFromTable(true);
        }  
    }//GEN-LAST:event_importTableMouseClicked

    private void updateBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBookButtonActionPerformed
        // TODO add your handling code here:
        int row = importTable.getSelectedRow();
        if (row >= 0) {
            try {
            if (checkBookError()){
                return;
            }
            } catch (SQLException ex) {
                Logger.getLogger(AddBookDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (int i = 0; i < importDTO.getFullbooks().size(); i++){
                if (addBook1.getISBNTextField().equals(importDTO.getFullbooks().get(i).getISBN()) && i != row){
                    JOptionPane.showMessageDialog(null, "Đã có sách với mã ISBN tồn tại trong đơn nhập");
                     return;
                }
             }
            importDTO.getFullbooks().remove(row);
            addBook1.fullbook.setQuantity((int) addBook1.getQuantitySpinner().getValue());
            switch(addBook1.fullbook.getStatus()){
                case "ISBNExisted" -> importDTO.getFullbooks().add(new FullBookDTO(addBook1.fullbook.getISBN(), addBook1.fullbook.getBookName(), addBook1.fullbook.getImg(), addBook1.fullbook.getPublisher(), addBook1.fullbook.getAuthors(), addBook1.fullbook.getCategories(), addBook1.fullbook.getEdition(), addBook1.fullbook.getPrice(), addBook1.fullbook.getQuantity(), addBook1.fullbook.getStatus()));
                default -> importDTO.getFullbooks().add(new FullBookDTO(addBook1.getISBNTextField(), addBook1.fullbook.getBookName(), addBook1.imgURL, addBook1.fullbook.getPublisher(), addBook1.fullbook.getAuthors(), addBook1.fullbook.getCategories(), addBook1.getEditionTextField(), addBook1.getPriceTextField(), addBook1.fullbook.getQuantity(), addBook1.fullbook.getStatus()));
            }
            updateTable();
        }else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sách muốn cập nhật.");
        }  
    }//GEN-LAST:event_updateBookButtonActionPerformed

    private void resetBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBookButtonActionPerformed
        // TODO add your handling code here:
        addBook1.resetBook();
    }//GEN-LAST:event_resetBookButtonActionPerformed

    private void addImportReceiptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addImportReceiptButtonActionPerformed
        // TODO add your handling code here:
        if (importDTO.getFullbooks().isEmpty()){
            JOptionPane.showMessageDialog(null, "Không có sách nhập.");
            return;
        }
        if (SupplierNameLabel.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Nhà cung cấp không được bỏ trống.");
            return;
        }
        
//        for (FullBookDTO u : importDTO.getFullbooks()){
//            System.out.print(u.getStatus() + " ");
//        }
//        
//        System.out.println();
        
        importDTO.setFee(total);

        try {
            if (bookBUS.AddImport(importDTO)) {
                for (FullBookDTO u : importDTO.getFullbooks()){
                    if (!u.getStatus().equals("ISBNExisted")){
                        saveImage(u.getImg(), u.getISBN());
                    }              
                }
                JOptionPane.showMessageDialog(null, "Tạo phiếu nhập thành công");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddBookDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_addImportReceiptButtonActionPerformed

    private void updateSupplier(){
        SupplierNameLabel.setText(importDTO.getSupplier().getName());
    }
    
    /**
     * @param args the command line arguments   
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                AddBookDialog dialog = new AddBookDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SupplierNameLabel;
    private GUI.AddBook addBook1;
    private MyDesign.MyButton addBookButton;
    private MyDesign.MyButton addImportReceiptButton;
    private MyDesign.MyButton findBookNameButton;
    private MyDesign.MyTable importTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private MyDesign.PanelBorder_Basic panelBorder_Basic2;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue1;
    private javax.swing.JLabel priceLabel;
    private MyDesign.MyButton removeBookButton;
    private MyDesign.MyButton resetBookButton;
    private javax.swing.JLabel staffIDLabel;
    private MyDesign.MyButton updateBookButton;
    // End of variables declaration//GEN-END:variables
}
