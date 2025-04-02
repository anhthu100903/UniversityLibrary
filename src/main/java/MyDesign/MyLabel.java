/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyDesign;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class MyLabel extends JLabel{
    public MyLabel(String text, boolean isBold) {
        super(text);
        
        if (isBold) {
            this.setFont(new Font("SansSerif", Font.BOLD, 12));
        } else {
            this.setFont(new Font("SansSerif", Font.PLAIN, 12));
        }
    }
    
    // Constructor with default font size 16 and no bold option
    public MyLabel(String text) {
        this(text, false);  // Default to non-bold
    }
}
