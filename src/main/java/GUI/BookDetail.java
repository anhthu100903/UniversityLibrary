/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DTO.BookDTO;
import MyDesign.MyLabel;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class BookDetail extends javax.swing.JPanel {
    private GridBagConstraints bookGBC = new GridBagConstraints();
    private BookDTO bookDTO;
    private int rightCount = 0;
    /**
     * Creates new form BookDetail
     */
    public BookDetail() {
        initComponents();
        showBook();
    }
    
    public void setBookDTO(BookDTO bookDTO){
        this.bookDTO = bookDTO;
    }
    
    public void showBook(){
        if (bookDTO == null){
            panelBorder1.setVisible(false);
        }else{
            setBook();
        }
    }
    
    public void setBook(){
        panelBorder1.setVisible(true);
        jLabel1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(bookDTO.getImg())).getImage().getScaledInstance(135, 192,Image.SCALE_SMOOTH)));
        lbNameBook.setText(bookDTO.getName());
        setUpBook();
    }
    
    private void setUpBook(){
        resetGridBagConstraints();
        bookGBC.weightx = 1;
        bookGBC.insets = new Insets(5, 5, 5, 5);
        bookGBC.gridx = 0;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.WEST;
        bookDetailContainer.add(new MyLabel("ISBN", true), bookGBC);
        
        bookGBC.gridx = 1;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.EAST;
        bookDetailContainer.add(new MyLabel(bookDTO.getISBN()), bookGBC);
        
        rightCount++;
        bookGBC.gridx = 0;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.WEST;
        bookDetailContainer.add(new MyLabel("Tác giả", true), bookGBC);
        
        bookGBC.gridx = 1;
        bookGBC.anchor = GridBagConstraints.EAST;
        for(String s : bookDTO.getAuthors()){
            bookGBC.gridy = rightCount;
            bookDetailContainer.add(new MyLabel(s), bookGBC);
            rightCount++;
        }
        
        bookGBC.gridx = 0;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.WEST;
        bookDetailContainer.add(new MyLabel("Thể loại", true), bookGBC);
        
        bookGBC.gridx = 1;
        bookGBC.anchor = GridBagConstraints.EAST;
        for(String s : bookDTO.getCategories()){
            bookGBC.gridy = rightCount;
            bookDetailContainer.add(new MyLabel(s), bookGBC);
            rightCount++;
        }
        
        bookGBC.gridx = 0;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.WEST;
        bookDetailContainer.add(new MyLabel("Nhà xuất bản", true), bookGBC);
        
        bookGBC.gridx = 1;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.EAST;
        bookDetailContainer.add(new MyLabel(bookDTO.getPublisher()), bookGBC);
        
        rightCount++;
        bookGBC.gridx = 0;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.WEST;
        bookDetailContainer.add(new MyLabel("Phiên bản", true), bookGBC);
        
        bookGBC.gridx = 1;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.EAST;
        bookDetailContainer.add(new MyLabel(bookDTO.getEdition()), bookGBC);
        
        rightCount++;
        bookGBC.gridx = 0;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.WEST;
        bookDetailContainer.add(new MyLabel("Vị trí", true), bookGBC);
        
        bookGBC.gridx = 1;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.EAST;
        bookDetailContainer.add(new MyLabel(bookDTO.getLocation()), bookGBC);
        
        rightCount++;
        bookGBC.gridx = 0;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.WEST;
        bookDetailContainer.add(new MyLabel("Giá tiền", true), bookGBC);
        
        bookGBC.gridx = 1;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.EAST;
        bookDetailContainer.add(new MyLabel(String.valueOf(bookDTO.getPrice())), bookGBC);
        
        rightCount++;
        bookGBC.gridx = 0;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.WEST;
        bookDetailContainer.add(new MyLabel("Số lượng", true), bookGBC);
        
        bookGBC.gridx = 1;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.EAST;
        bookDetailContainer.add(new MyLabel(String.valueOf(bookDTO.getAvailable())), bookGBC);
        
        rightCount++;
        bookGBC.gridx = 0;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.WEST;
        bookDetailContainer.add(new MyLabel("Kho", true), bookGBC);
        
        bookGBC.gridx = 1;
        bookGBC.gridy = rightCount;
        bookGBC.anchor = GridBagConstraints.EAST;
        bookDetailContainer.add(new MyLabel(String.valueOf(bookDTO.getQuantity())), bookGBC);    
    }
    
    private void resetGridBagConstraints(){
        bookDetailContainer.removeAll();  
        bookDetailContainer.revalidate(); 
        bookDetailContainer.repaint(); 
        rightCount = 0;
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
        pnImageBook = new MyDesign.PanelBorder_Basic();
        jLabel1 = new javax.swing.JLabel();
        lbNameBook = new javax.swing.JLabel();
        bookDetailContainer = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        panelBorder1.setAutoscrolls(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/ExampleBook.png"))); // NOI18N

        javax.swing.GroupLayout pnImageBookLayout = new javax.swing.GroupLayout(pnImageBook);
        pnImageBook.setLayout(pnImageBookLayout);
        pnImageBookLayout.setHorizontalGroup(
            pnImageBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnImageBookLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(27, 27, 27))
        );
        pnImageBookLayout.setVerticalGroup(
            pnImageBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnImageBookLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        lbNameBook.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lbNameBook.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNameBook.setText("Một đêm không ngủ");

        bookDetailContainer.setBackground(new java.awt.Color(246, 250, 255));
        bookDetailContainer.setAutoscrolls(true);
        bookDetailContainer.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bookDetailContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(pnImageBook, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 93, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(lbNameBook, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnImageBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNameBook)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bookDetailContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
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
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookDetailContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbNameBook;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder_Basic pnImageBook;
    // End of variables declaration//GEN-END:variables
}
