/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DTO.AccountDTO;
import MyDesign.EventMenuSelected;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class HomePage extends javax.swing.JFrame {
    
    AccountDTO account;
    /**
     * Creates new form HomePage
     */
    public HomePage() {
        initComponents();
        setLocationRelativeTo(null);
        initMoving(this);
        Menu.setMenuItemVisible(1, false);
        Menu.setMenuItemVisible(2, false);
        Menu.setMenuItemVisible(3, false);
        Menu.setMenuItemVisible(4, false);
        Menu.setMenuItemVisible(5, false);
        Menu.setMenuItemVisible(6, false);
        Menu.setMenuItemVisible(7, false);
        Menu.setMenuItemVisible(8, true);
        Menu.setMenuItemVisible(9, false);
        
        myButton1.setVisible(false);

        Menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                switch (index) {
                    case 0:
                        setForm(new BookGUI(account));
                        break;
                    case 1:
                        setForm(new BorrowGUI(account));
                        break;
                    case 2:
                        setForm(new ReaderGUI());
                        break;
                    case 3:
                        setForm(new StaffGUI(account));
                        break;
                    case 4:
                        setForm(new AccountGUI());
                        break;
                    case 5:
                        setForm(new OtherGUI());
                        break;
                    case 6:
                        setForm(new StatisticGUI());
                        break;
                    case 7:
                        setForm(new ImportGUI());
                        break;
                    case 8:
                        Login dia = new Login(new javax.swing.JFrame(), true, 
                                updatedAccount -> {
                                        account = updatedAccount;
                                        addLoginAccount();
                                    });
                        dia.setVisible(true);
                        break;
                    case 9:
                        int response = JOptionPane.showConfirmDialog(
                            null, 
                            "Bạn có chắc muốn đăng xuất?", 
                            "Đăng xuất", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE
                        );
                        if (response == JOptionPane.YES_OPTION) {
                            account = null;
                            addLoginAccount();
                            JOptionPane.showMessageDialog(null, "Bạn đã đăng xuất.");
                        }
                        break;
                    default:
                        // Optionally handle other cases or do nothing
                        break;
                }
            }
        });
        
        Menu.triggerMenuItemClick(0);
    }

    private void addLoginAccount() {
        if (account != null) {
            System.out.println("Login successfully");
            
            switch(account.getRoleDTO().getId()){
                case "SV":
                    Menu.setMenuItemVisible(1, true);
                    Menu.setMenuItemVisible(2, false);
                    Menu.setMenuItemVisible(3, false);
                    Menu.setMenuItemVisible(4, false);
                    Menu.setMenuItemVisible(5, false);
                    Menu.setMenuItemVisible(6, false);
                    Menu.setMenuItemVisible(7, false);
                    Menu.setMenuItemVisible(8, false);
                    Menu.setMenuItemVisible(9, true);
                    break;
                case "GV":
                    Menu.setMenuItemVisible(1, true);
                    Menu.setMenuItemVisible(2, false);
                    Menu.setMenuItemVisible(3, false);
                    Menu.setMenuItemVisible(4, false);
                    Menu.setMenuItemVisible(5, false);
                    Menu.setMenuItemVisible(6, false);
                    Menu.setMenuItemVisible(7, false);
                    Menu.setMenuItemVisible(8, false);
                    Menu.setMenuItemVisible(9, true);
                    break;
                case "TT":
                    Menu.setMenuItemVisible(1, true);
                    Menu.setMenuItemVisible(2, true);
                    Menu.setMenuItemVisible(3, false);
                    Menu.setMenuItemVisible(4, false);
                    Menu.setMenuItemVisible(5, false);
                    Menu.setMenuItemVisible(6, false);
                    Menu.setMenuItemVisible(7, false);
                    Menu.setMenuItemVisible(8, false);
                    Menu.setMenuItemVisible(9, true);
                    break;
                case "TK":
                    myButton1.setVisible(true);
                    Menu.setMenuItemVisible(1, false);
                    Menu.setMenuItemVisible(2, false);
                    Menu.setMenuItemVisible(3, false);
                    Menu.setMenuItemVisible(4, false);
                    Menu.setMenuItemVisible(5, true);
                    Menu.setMenuItemVisible(6, false);
                    Menu.setMenuItemVisible(7, true);
                    Menu.setMenuItemVisible(8, false);
                    Menu.setMenuItemVisible(9, true);
                    break;
                case "QL":
                    myButton1.setVisible(true);
                    Menu.setMenuItemVisible(1, true);
                    Menu.setMenuItemVisible(2, true);
                    Menu.setMenuItemVisible(3, true);
                    Menu.setMenuItemVisible(4, false);
                    Menu.setMenuItemVisible(5, true);
                    Menu.setMenuItemVisible(6, true);
                    Menu.setMenuItemVisible(7, true);
                    Menu.setMenuItemVisible(8, false);
                    Menu.setMenuItemVisible(9, true);
                    break;
                case "AD":
                    Menu.setMenuItemVisible(1, false);
                    Menu.setMenuItemVisible(2, false);
                    Menu.setMenuItemVisible(3, false);
                    Menu.setMenuItemVisible(4, true);
                    Menu.setMenuItemVisible(5, false);
                    Menu.setMenuItemVisible(6, false);
                    Menu.setMenuItemVisible(7, false);
                    Menu.setMenuItemVisible(8, true);
                    break;
            }
            
            
        } else {
            System.out.println("No account logged in");
            myButton1.setVisible(false);
            Menu.setMenuItemVisible(1, false);
            Menu.setMenuItemVisible(2, false);
            Menu.setMenuItemVisible(3, false);
            Menu.setMenuItemVisible(4, false);
            Menu.setMenuItemVisible(5, false);
            Menu.setMenuItemVisible(6, false);
            Menu.setMenuItemVisible(7, false);
            Menu.setMenuItemVisible(8, true);
            Menu.setMenuItemVisible(9, false);
        }
        Menu.triggerMenuItemClick(0);
    }
    
    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
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
        PanelHead = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        myButton1 = new MyDesign.MyButton();
        Menu = new GUI.Menu();
        mainPanel = new MyDesign.PanelBorder_Basic();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(java.awt.Color.orange);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        PanelHead.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        PanelHead.setOpaque(false);
        PanelHead.setPreferredSize(new java.awt.Dimension(900, 62));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(22, 113, 221));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/logo.png"))); // NOI18N
        jLabel2.setText("Library");

        myButton1.setForeground(new java.awt.Color(22, 113, 221));
        myButton1.setText("Nhập sách");
        myButton1.setToolTipText("");
        myButton1.setColorClick(new java.awt.Color(153, 204, 255));
        myButton1.setColorOver(new java.awt.Color(102, 204, 255));
        myButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHeadLayout = new javax.swing.GroupLayout(PanelHead);
        PanelHead.setLayout(PanelHeadLayout);
        PanelHeadLayout.setHorizontalGroup(
            PanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        PanelHeadLayout.setVerticalGroup(
            PanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeadLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(PanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        mainPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mainPanelKeyPressed(evt);
            }
        });
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelHead, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addComponent(PanelHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainPanelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mainPanelKeyPressed
        int keyCode = evt.getKeyCode();
        int modifiers = evt.getModifiers();

        if ((keyCode == KeyEvent.VK_F4 && modifiers == KeyEvent.ALT_MASK) ||
            keyCode == KeyEvent.VK_ESCAPE) {
            // Exit the program
            System.exit(0);
        }
    }//GEN-LAST:event_mainPanelKeyPressed

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        // TODO add your handling code here:
        AddBookDialog whid = new AddBookDialog(new javax.swing.JFrame(), true, account);
        whid.setVisible(true);
    }//GEN-LAST:event_myButton1ActionPerformed

    private int x;
    private int y;

    public void initMoving(JFrame fram) {
        PanelHead.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }

        });
        PanelHead.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.Menu Menu;
    private javax.swing.JPanel PanelHead;
    private javax.swing.JLabel jLabel2;
    private MyDesign.PanelBorder_Basic mainPanel;
    private MyDesign.MyButton myButton1;
    private MyDesign.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
