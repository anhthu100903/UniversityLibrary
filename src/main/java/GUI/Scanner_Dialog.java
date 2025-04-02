package GUI;

import javax.swing.*;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
public class Scanner_Dialog {
    private JFrame frame;
    public  JTextField txtField;
    public String barcode;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Scanner_Dialog scanner = new Scanner_Dialog();
//            scanner.initAndShowGUI();
        });
    }


    public void initAndShowGUI(BarcodeListener listener) {
        frame = new JFrame("Barcode Scanner");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE );
        initWebcam(listener);
        frame.setVisible(true);
    }
    
    public Scanner_Dialog() {

    }
    
    private void initWebcam(BarcodeListener listener) {
        final Webcam webcam = Webcam.getDefault();

        if (webcam == null) {
            System.out.println("No webcam found");
            return;
        }
        
        if (webcam.isOpen()) {
            webcam.close();
        }

        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setFillArea(true);
        frame.add(panel);
        frame.pack();
        
        final boolean[] running = {true};

        Thread thread = new Thread(() -> {
            do {
                try {
                    LuminanceSource source = new BufferedImageLuminanceSource(webcam.getImage());
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    Reader reader = new MultiFormatReader();
                    Result result = reader.decode(bitmap);
                    if (result != null) {
                        SwingUtilities.invokeLater(() -> {
                            listener.onBarcodeScanned(result.getText());
                            running[0] = false;
                            frame.dispose();
                        });
                    }
                } catch (NotFoundException | ChecksumException | FormatException e) {
                    // Barcode not found
                }
            } while (running[0]);
        });

        thread.setDaemon(true);
        thread.start();
    }
    
    private void initTxtField() {
        txtField = new JTextField(20);
        // Thêm txtField vào frame hoặc panel tại đây
    }
    
    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}


