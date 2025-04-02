/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.OtherBUS;
import DTO.AuthorDTO;
import DTO.CategoryDTO;
import DTO.PublisherDTO;
import DTO.SupplierDTO;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author User
 */
public class OtherGUI extends javax.swing.JPanel {
    /**
     * Creates new form OtherGUI
     */
    private AuthorDTO author;
    private CategoryDTO category;
    private PublisherDTO publisher;
    private SupplierDTO supplier;
    
    public OtherGUI() {
        initComponents();
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        render();
    }
    
    public void render(){
        OtherBUS otherBUS = new OtherBUS();
        displayAuthorTable(otherBUS.loadAuthorData());
        styleTable(authorsTable);

        displayCategoryTable(otherBUS.loadCategoryData());
        styleTable(categoryTable);
        
        displayPublisherTable(otherBUS.loadPublisherData());
        styleTable(publishersTable);
        
        displaySupplierTable(otherBUS.loadSupplierData());
        styleTable(suppliersTable);
    }
    
    public void styleTable(JTable table){
        // Căn giữa nội dung các ô trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Căn giữa tiêu đề cột
        JTableHeader header = table.getTableHeader();
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);  // Căn giữa tiêu đề cột
        header.setFont(new Font("Arial", Font.BOLD, 14));  // In đậm tiêu đề cột
        header.setForeground(Color.BLUE);  // Màu chữ cho tiêu đề cột
    }
    
    public void displayAuthorTable(List<AuthorDTO> authors){
        String[] columnNames = {"ID", "Tên", "Năm Sinh"};
        
        // Tạo dữ liệu cho bảng từ List<Author>
        Object[][] data = new Object[authors.size()][3];
        for (int i = 0; i < authors.size(); i++) {
            data[i][0] = authors.get(i).getId();
            data[i][1] = authors.get(i).getName();
            data[i][2] = authors.get(i).getYear();
        }

        // Tạo DefaultTableModel với dữ liệu và tên cột
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        authorsTable.setModel(model);
    }
    
    public void displayCategoryTable(List<CategoryDTO> categories){
        String[] columnNames = {"ID", "Tên"};
        
        Object[][] data = new Object[categories.size()][2];
        for (int i = 0; i < categories.size(); i++) {
            data[i][0] = categories.get(i).getId();
            data[i][1] = categories.get(i).getName();
        }

        // Tạo DefaultTableModel với dữ liệu và tên cột
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        categoryTable.setModel(model);
    }
    
    public void displayPublisherTable(List<PublisherDTO> publishers){
        String[] columnNames = {"ID", "Tên"};
        
        // Tạo dữ liệu cho bảng từ List<Author>
        Object[][] data = new Object[publishers.size()][2];
        for (int i = 0; i < publishers.size(); i++) {
            data[i][0] = publishers.get(i).getId();
            data[i][1] = publishers.get(i).getName();
        }

        // Tạo DefaultTableModel với dữ liệu và tên cột
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        publishersTable.setModel(model);
    }
    
    public void displaySupplierTable(List<SupplierDTO> suppliers){
        String[] columnNames = {"ID", "Tên"};
        
        // Tạo dữ liệu cho bảng từ List<Author>
        Object[][] data = new Object[suppliers.size()][2];
        for (int i = 0; i < suppliers.size(); i++) {
            data[i][0] = suppliers.get(i).getId();
            data[i][1] = suppliers.get(i).getName();
        }

        // Tạo DefaultTableModel với dữ liệu và tên cột
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        suppliersTable.setModel(model);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        authorsTable = new MyDesign.MyTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        categoryTable = new MyDesign.MyTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        publishersTable = new MyDesign.MyTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        suppliersTable = new MyDesign.MyTable();
        addAuthorButton = new MyDesign.MyButton();
        delAuthorButton = new MyDesign.MyButton();
        addPublisherButton = new MyDesign.MyButton();
        delPublisherButton = new MyDesign.MyButton();
        addCateButton = new MyDesign.MyButton();
        delCateButton = new MyDesign.MyButton();
        addSupplierButton = new MyDesign.MyButton();
        delSupplierButton = new MyDesign.MyButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tác giả");

        authorsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên tác giả", "Năm sinh"
            }
        ));
        authorsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authorsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(authorsTable);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thể loại");

        categoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã", "Tên thể loại"
            }
        ));
        categoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(categoryTable);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nhà xuất bản");

        publishersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã", "Tên nhà xuất bản"
            }
        ));
        publishersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                publishersTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(publishersTable);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nhà cung cấp");

        suppliersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã", "Tên nhà cung cấp"
            }
        ));
        suppliersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suppliersTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(suppliersTable);

        addAuthorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/add.png"))); // NOI18N
        addAuthorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAuthorButtonActionPerformed(evt);
            }
        });

        delAuthorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/tru.png"))); // NOI18N
        delAuthorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delAuthorButtonActionPerformed(evt);
            }
        });

        addPublisherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/add.png"))); // NOI18N
        addPublisherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPublisherButtonActionPerformed(evt);
            }
        });

        delPublisherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/tru.png"))); // NOI18N
        delPublisherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delPublisherButtonActionPerformed(evt);
            }
        });

        addCateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/add.png"))); // NOI18N
        addCateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCateButtonActionPerformed(evt);
            }
        });

        delCateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/tru.png"))); // NOI18N
        delCateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delCateButtonActionPerformed(evt);
            }
        });

        addSupplierButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/add.png"))); // NOI18N
        addSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierButtonActionPerformed(evt);
            }
        });

        delSupplierButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/tru.png"))); // NOI18N
        delSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delSupplierButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addAuthorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(delAuthorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addPublisherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(delPublisherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addCateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(delCateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(delSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(71, 71, 71))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(addCateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(delCateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(addAuthorButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(delAuthorButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(addSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(delSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(addPublisherButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(delPublisherButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addAuthorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAuthorButtonActionPerformed
        // TODO add your handling code here:
        AddAuthorsDialog whid = new AddAuthorsDialog(new javax.swing.JFrame(), true, this);
        whid.setVisible(true);
        
    }//GEN-LAST:event_addAuthorButtonActionPerformed

    private void addCateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCateButtonActionPerformed
        // TODO add your handling code here:
        AddCateDialog whid = new AddCateDialog(new javax.swing.JFrame(), true, this);
        whid.setVisible(true);
    }//GEN-LAST:event_addCateButtonActionPerformed

    private void addPublisherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPublisherButtonActionPerformed
        // TODO add your handling code here:
        AddPublisherDialog whid = new AddPublisherDialog(new javax.swing.JFrame(), true, this);
        whid.setVisible(true);
    }//GEN-LAST:event_addPublisherButtonActionPerformed

    private void addSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierButtonActionPerformed
        // TODO add your handling code here:
        AddSupplierDialog whid = new AddSupplierDialog(new javax.swing.JFrame(), true, this);
        whid.setVisible(true);
    }//GEN-LAST:event_addSupplierButtonActionPerformed

    private void authorsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_authorsTableMouseClicked
        // TODO add your handling code here:
        int row = authorsTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)authorsTable.getModel();
        if (row != -1) {
            TableColumnModel columnModel = authorsTable.getColumnModel();
            int columnIndex = columnModel.getColumnIndex("ID");
            int id = (Integer) authorsTable.getValueAt(row, columnIndex);
//            JOptionPane.showMessageDialog(null, "Selected ID: " + id);
            OtherBUS otherBUS = new OtherBUS();
            this.author = otherBUS.getAuthorById(id);
        } else {
            JOptionPane.showMessageDialog(null, "No row selected!");
        }
    }//GEN-LAST:event_authorsTableMouseClicked

    private void delAuthorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delAuthorButtonActionPerformed
        // TODO add your handling code here:
        OtherBUS otherBUS = new OtherBUS();
        if(authorsTable.getSelectedRow() != -1){
            JOptionPane.showMessageDialog(null, otherBUS.deleteAuthor(this.author));
            render();
        }else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tác giả muốn xóa");
        }
    }//GEN-LAST:event_delAuthorButtonActionPerformed

    private void categoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryTableMouseClicked
        // TODO add your handling code here:
        int row = categoryTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)categoryTable.getModel();
        if (row != -1) {
            TableColumnModel columnModel = categoryTable.getColumnModel();
            int columnIndex = columnModel.getColumnIndex("ID");
            int id = (Integer) categoryTable.getValueAt(row, columnIndex);
//            JOptionPane.showMessageDialog(null, "Selected ID: " + id);
            OtherBUS otherBUS = new OtherBUS();
            this.category = otherBUS.getCategoryById(id);
        } else {
            JOptionPane.showMessageDialog(null, "No row selected!");
        }
    }//GEN-LAST:event_categoryTableMouseClicked

    private void delCateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delCateButtonActionPerformed
        // TODO add your handling code here:
        OtherBUS otherBUS = new OtherBUS();
        if(categoryTable.getSelectedRow() != -1){
            JOptionPane.showMessageDialog(null, otherBUS.deleteCategory(this.category));
            render();
        }else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn thể loại muốn xóa");
        }
    }//GEN-LAST:event_delCateButtonActionPerformed

    private void publishersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_publishersTableMouseClicked
        // TODO add your handling code here:
        int row = publishersTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)publishersTable.getModel();
        if (row != -1) {
            TableColumnModel columnModel = publishersTable.getColumnModel();
            int columnIndex = columnModel.getColumnIndex("ID");
            int id = (Integer) publishersTable.getValueAt(row, columnIndex);
//            JOptionPane.showMessageDialog(null, "Selected ID: " + id);
            OtherBUS otherBUS = new OtherBUS();
            this.publisher = otherBUS.getPublisherById(id);
        } else {
            JOptionPane.showMessageDialog(null, "No row selected!");
        }
    }//GEN-LAST:event_publishersTableMouseClicked

    private void suppliersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suppliersTableMouseClicked
        // TODO add your handling code here:
        int row = suppliersTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)suppliersTable.getModel();
        if (row != -1) {
            TableColumnModel columnModel = suppliersTable.getColumnModel();
            int columnIndex = columnModel.getColumnIndex("ID");
            int id = (Integer) suppliersTable.getValueAt(row, columnIndex);
//            JOptionPane.showMessageDialog(null, "Selected ID: " + id);
            OtherBUS otherBUS = new OtherBUS();
            this.supplier = otherBUS.getSupplierById(id);
        } else {
            JOptionPane.showMessageDialog(null, "No row selected!");
        }
    }//GEN-LAST:event_suppliersTableMouseClicked

    private void delPublisherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delPublisherButtonActionPerformed
        // TODO add your handling code here:
        OtherBUS otherBUS = new OtherBUS();
        if(publishersTable.getSelectedRow() != -1){
            JOptionPane.showMessageDialog(null, otherBUS.deletePublisher(this.publisher));
            render();
        }else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà xuất bản muốn xóa");
        }
        
    }//GEN-LAST:event_delPublisherButtonActionPerformed

    private void delSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delSupplierButtonActionPerformed
        // TODO add your handling code here:
        OtherBUS otherBUS = new OtherBUS();
        if(suppliersTable.getSelectedRow() != 1){
            JOptionPane.showMessageDialog(null, otherBUS.deleteSupplier(this.supplier));
            render();
        }else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp");
        }
        
    }//GEN-LAST:event_delSupplierButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton addAuthorButton;
    private MyDesign.MyButton addCateButton;
    private MyDesign.MyButton addPublisherButton;
    private MyDesign.MyButton addSupplierButton;
    private MyDesign.MyTable authorsTable;
    private MyDesign.MyTable categoryTable;
    private MyDesign.MyButton delAuthorButton;
    private MyDesign.MyButton delCateButton;
    private MyDesign.MyButton delPublisherButton;
    private MyDesign.MyButton delSupplierButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.MyTable publishersTable;
    private MyDesign.MyTable suppliersTable;
    // End of variables declaration//GEN-END:variables
}
