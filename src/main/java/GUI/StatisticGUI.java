/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.BookBUS;
import BUS.BorrowBUS;
import DTO.BorrowDTO;
import DTO.BorrowDetailDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class StatisticGUI extends javax.swing.JPanel {

    /**
     * Creates new form StatisticGUI
     */
    public StatisticGUI() {
        initComponents();
        // mặc định chọn ngày 
        java.util.Date utilDateStart = new java.util.Date(twoMonthAgo().getTime());
        java.util.Date utilDateEnd = new java.util.Date(toDay().getTime());
        startDateChooser.setDate(utilDateStart);
        endDateChooser.setDate(utilDateEnd);
        render(twoMonthAgo(), toDay());
    }
    
    public int borrowTotal(List<BorrowDTO> borrows){
        int total = 0;

        // Duyệt qua danh sách BorrowDTO
        for (BorrowDTO borrow : borrows) {
            // Duyệt qua danh sách BorrowDetailDTO của từng BorrowDTO
            for (BorrowDetailDTO detail : borrow.getBorrowDetailDTO()) {
                // Cộng dồn số lượt mượn
                total += detail.getQuantity();
            }
        }
        return total;
    }
    
    public int lostTotal(List<BorrowDTO> borrows) {
        int totalLost = 0;

        // Duyệt qua danh sách BorrowDTO
        for (BorrowDTO borrow : borrows) {
            // Duyệt qua danh sách BorrowDetailDTO của từng BorrowDTO
            for (BorrowDetailDTO detail : borrow.getBorrowDetailDTO()) {
                // Cộng dồn số lượt mất
                totalLost += detail.getLost();
            }
        }

        return totalLost;
    }
    
    public int damagedTotal(List<BorrowDTO> borrows) {
        int totalDamaged = 0;

        // Duyệt qua danh sách BorrowDTO
        for (BorrowDTO borrow : borrows) {
            // Duyệt qua danh sách BorrowDetailDTO của từng BorrowDTO
            for (BorrowDetailDTO detail : borrow.getBorrowDetailDTO()) {
                // Cộng dồn số sách hỏng
                totalDamaged += detail.getBroke();
            }
        }

        return totalDamaged;
    }
    
    public double refundRate(List<BorrowDTO> borrows){
        int onTimeCount = 0; // Biến đếm số lần hoàn trả đúng hạn
        int total = 0;
        
        // Duyệt qua danh sách BorrowDTO
        for (BorrowDTO borrow : borrows) {
            LocalDate returnDate = borrow.getReturnDate(); // Lấy returnDate của BorrowDTO
            LocalDate dueDate = borrow.getDueDate(); // Lấy returnDate của BorrowDTO

            // Kiểm tra nếu returnDate >= ngày hôm nay
            if (returnDate != null) {
                onTimeCount++; // Tăng biến đếm nếu trả đúng hạn
            }
            total++;
        }
        return onTimeCount / (total * 1.0) * 100;
    }
    
    public void render(java.sql.Date date1, java.sql.Date date2){
        try {
            BorrowBUS borrowBUS = new BorrowBUS();
            List<BorrowDTO> list = borrowBUS.getBorrowFromDayToDay(date1, date2);
            displayBorrowDetail(list);
            borrowRecieptNumber.setText(borrowTotal(list) + "");
            lostNumber.setText(lostTotal(list) + "");
            brokeNumber.setText(damagedTotal(list) + "");
            returnRate.setText(String.format("%.1f", refundRate(list)) + "%");
        }catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error initializing database connection: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    public void displayBorrowDetail(List<BorrowDTO> borrows) throws SQLException, ClassNotFoundException {
            try {
            // Mã của bạn ở đây, ví dụ như:
            // Tạo một DefaultTableModel với các cột: "ISBN", "Tên sách", "Phiên bản", "Số lượt mượn"
            String[] columnNames = {"ISBN", "Tên sách", "Phiên bản", "Số lượt mượn"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0); // 0 dòng ban đầu

            // Tạo một Map để lưu tổng số lượt mượn cho mỗi sách (theo ISBN)
            Map<String, Integer> borrowCountMap = new HashMap<>();
            Map<String, String> bookNameMap = new HashMap<>();
            BookBUS bookBUS = new BookBUS(); // Sử dụng BookBUS

            // Duyệt qua danh sách BorrowDTO để tổng hợp thông tin
            for (BorrowDTO borrow : borrows) {
                for (BorrowDetailDTO detail : borrow.getBorrowDetailDTO()) {
                    String ISBN = detail.getISBN();
                    String bookName = detail.getBookName();
                    int quantity = detail.getQuantity();

                    bookNameMap.put(ISBN, bookName);
                    borrowCountMap.put(ISBN, borrowCountMap.getOrDefault(ISBN, 0) + quantity);
                }
            }

            // Sắp xếp danh sách theo số lượt mượn giảm dần
            List<Map.Entry<String, Integer>> sortedBorrowList = new ArrayList<>(borrowCountMap.entrySet());
            sortedBorrowList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())); // Sắp xếp từ cao đến thấp

            // Thêm các hàng dữ liệu vào model
            for (Map.Entry<String, Integer> entry : sortedBorrowList) {
                String ISBN = entry.getKey();
                int totalBorrowCount = entry.getValue();
                String bookName = bookNameMap.get(ISBN);

                // Lấy thông tin phiên bản sách qua BookBUS
                String edition = bookBUS.getEditionByISBN(ISBN); 
                if (edition == null) {
                    edition = "Không xác định"; // Giá trị mặc định nếu không tìm thấy phiên bản
                }

                // Thêm dòng vào model
                model.addRow(new Object[]{ISBN, bookName, edition, totalBorrowCount});
            }

            // Cập nhật bảng với model mới
            mostReadBookNumber.setModel(model);
            } catch (IOException e) {
                // Xử lý khi gặp lỗi IOException
                System.out.println("Lỗi I/O: " + e.getMessage());
                e.printStackTrace();
            } catch (SQLException e) {
                // Xử lý khi gặp lỗi SQL
                System.out.println("Lỗi SQL: " + e.getMessage());
                e.printStackTrace();
            }
    }
    
    public java.sql.Date toDay(){
        LocalDate today = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(today);
        return sqlDate;
    }
    
    public java.sql.Date twoMonthAgo(){
        LocalDate today = LocalDate.now();
        LocalDate oneMonthAgo = today.minusMonths(2);
        java.sql.Date sqlDate = java.sql.Date.valueOf(oneMonthAgo);
        return sqlDate;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        startDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        endDateChooser = new com.toedter.calendar.JDateChooser();
        panelBorder_Statistic_Blue1 = new MyDesign.PanelBorder_Statistic_Blue();
        jLabel3 = new javax.swing.JLabel();
        borrowRecieptNumber = new javax.swing.JLabel();
        panelBorder_Statistic_Red1 = new MyDesign.PanelBorder_Statistic_Red();
        jLabel5 = new javax.swing.JLabel();
        lostNumber = new javax.swing.JLabel();
        panelBorder_Statistic_Red2 = new MyDesign.PanelBorder_Statistic_Red();
        jLabel7 = new javax.swing.JLabel();
        brokeNumber = new javax.swing.JLabel();
        panelBorder_Statistic_Blue2 = new MyDesign.PanelBorder_Statistic_Blue();
        jLabel9 = new javax.swing.JLabel();
        returnRate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mostReadBookNumber = new MyDesign.MyTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("THỐNG KÊ TỪ");

        startDateChooser.setBackground(new java.awt.Color(255, 255, 255));
        startDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                startDateChooserPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("ĐẾN");

        endDateChooser.setBackground(new java.awt.Color(255, 255, 255));
        endDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                endDateChooserPropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SỐ LƯỢT MƯỢN");

        borrowRecieptNumber.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        borrowRecieptNumber.setForeground(new java.awt.Color(255, 255, 255));
        borrowRecieptNumber.setText("1");

        javax.swing.GroupLayout panelBorder_Statistic_Blue1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue1);
        panelBorder_Statistic_Blue1.setLayout(panelBorder_Statistic_Blue1Layout);
        panelBorder_Statistic_Blue1Layout.setHorizontalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(borrowRecieptNumber)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        panelBorder_Statistic_Blue1Layout.setVerticalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(35, 35, 35)
                .addComponent(borrowRecieptNumber)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(248, 67, 67));
        jLabel5.setText("SỐ SÁCH MẤT");

        lostNumber.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lostNumber.setForeground(new java.awt.Color(248, 67, 67));
        lostNumber.setText("1");

        javax.swing.GroupLayout panelBorder_Statistic_Red1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Red1);
        panelBorder_Statistic_Red1.setLayout(panelBorder_Statistic_Red1Layout);
        panelBorder_Statistic_Red1Layout.setHorizontalGroup(
            panelBorder_Statistic_Red1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Red1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Statistic_Red1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Statistic_Red1Layout.createSequentialGroup()
                        .addComponent(lostNumber)
                        .addGap(15, 15, 15)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        panelBorder_Statistic_Red1Layout.setVerticalGroup(
            panelBorder_Statistic_Red1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Red1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addGap(32, 32, 32)
                .addComponent(lostNumber)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(248, 67, 67));
        jLabel7.setText("SỐ SÁCH HỎNG");

        brokeNumber.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        brokeNumber.setForeground(new java.awt.Color(248, 67, 67));
        brokeNumber.setText("1");

        javax.swing.GroupLayout panelBorder_Statistic_Red2Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Red2);
        panelBorder_Statistic_Red2.setLayout(panelBorder_Statistic_Red2Layout);
        panelBorder_Statistic_Red2Layout.setHorizontalGroup(
            panelBorder_Statistic_Red2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Red2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Statistic_Red2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(brokeNumber)
                .addGap(90, 90, 90))
        );
        panelBorder_Statistic_Red2Layout.setVerticalGroup(
            panelBorder_Statistic_Red2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Red2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7)
                .addGap(30, 30, 30)
                .addComponent(brokeNumber)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("TỈ LỆ HOÀN TRẢ");

        returnRate.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        returnRate.setForeground(new java.awt.Color(255, 255, 255));
        returnRate.setText("1%");

        javax.swing.GroupLayout panelBorder_Statistic_Blue2Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue2);
        panelBorder_Statistic_Blue2.setLayout(panelBorder_Statistic_Blue2Layout);
        panelBorder_Statistic_Blue2Layout.setHorizontalGroup(
            panelBorder_Statistic_Blue2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue2Layout.createSequentialGroup()
                .addGroup(panelBorder_Statistic_Blue2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Statistic_Blue2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9))
                    .addGroup(panelBorder_Statistic_Blue2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(returnRate)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        panelBorder_Statistic_Blue2Layout.setVerticalGroup(
            panelBorder_Statistic_Blue2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9)
                .addGap(35, 35, 35)
                .addComponent(returnRate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("SÁCH ĐƯỢC MƯỢN NHIỀU NHẤT");

        mostReadBookNumber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ISBN", "Tên sách", "Phiên bản", "Số lượt mượn"
            }
        ));
        jScrollPane1.setViewportView(mostReadBookNumber);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(panelBorder_Statistic_Blue1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBorder_Statistic_Red1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBorder_Statistic_Red2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBorder_Statistic_Blue2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(27, 27, 27)
                                .addComponent(startDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2)
                                .addGap(33, 33, 33)
                                .addComponent(endDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(endDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(startDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder_Statistic_Blue2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder_Statistic_Red1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder_Statistic_Blue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder_Statistic_Red2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void startDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_startDateChooserPropertyChange
        java.util.Date currentDate = new java.util.Date();

        java.util.Date selectedStartDate = (java.util.Date) startDateChooser.getDate();

        if (selectedStartDate != null) {
            if (selectedStartDate.after(currentDate)) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Ngày bắt đầu không được vượt quá ngày hiện tại!",
                    "Lỗi chọn ngày",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );

                startDateChooser.setDate(previousStartDate);
                return; 
            } else {
                previousStartDate = selectedStartDate;
            }
        }

        java.util.Date utilDateEnd = (java.util.Date) endDateChooser.getDate();
        if (selectedStartDate != null && utilDateEnd != null) {
            java.sql.Date sqlDate1 = new java.sql.Date(selectedStartDate.getTime());
            java.sql.Date sqlDate2 = new java.sql.Date(utilDateEnd.getTime());
            render(sqlDate1, sqlDate2); // Gọi hàm render với các giá trị ngày hợp lệ
        }
    }//GEN-LAST:event_startDateChooserPropertyChange

    private void endDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_endDateChooserPropertyChange
        // TODO add your handling code here:
        java.util.Date utilDateStart = (java.util.Date) startDateChooser.getDate();
        java.util.Date utilDateEnd = (java.util.Date) endDateChooser.getDate();
        // Kiểm tra nếu utilDate không null để tránh lỗi NullPointerException
        if (utilDateStart != null && utilDateEnd != null) {
            // Ép kiểu java.util.Date sang java.sql.Date
            java.sql.Date sqlDate1 = new java.sql.Date(utilDateStart.getTime());
            java.sql.Date sqlDate2 = new java.sql.Date(utilDateEnd.getTime());
            // Gọi hàm render() với sqlDate (nếu cần sử dụng)
            render(sqlDate1, sqlDate2);
        }
    }//GEN-LAST:event_endDateChooserPropertyChange

    private java.util.Date previousStartDate = null;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel borrowRecieptNumber;
    private javax.swing.JLabel brokeNumber;
    private com.toedter.calendar.JDateChooser endDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lostNumber;
    private MyDesign.MyTable mostReadBookNumber;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue1;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue2;
    private MyDesign.PanelBorder_Statistic_Red panelBorder_Statistic_Red1;
    private MyDesign.PanelBorder_Statistic_Red panelBorder_Statistic_Red2;
    private javax.swing.JLabel returnRate;
    private com.toedter.calendar.JDateChooser startDateChooser;
    // End of variables declaration//GEN-END:variables
}
