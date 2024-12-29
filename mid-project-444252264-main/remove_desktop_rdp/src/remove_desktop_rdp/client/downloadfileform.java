
package remove_desktop_rdp.client;

import remove_desktop_rdp.server.FileEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class downloadfileform extends javax.swing.JFrame {
static String ip=""; // Địa chỉ IP của server

    public downloadfileform(String ip) {
        this.ip = ip; // Khởi tạo IP cho server
        initComponents(); // Khởi tạo các thành phần giao diện
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // Định dạng font chữ cho tiêu đề
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Download File");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(266, 41, 165, 32);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // Định dạng font chữ cho nhãn nhập đường dẫn
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nhập đường dẫn file cần tải về");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(192, 100, 316, 24);

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Dialog", 0, 18)); // Định dạng font chữ cho ô nhập liệu
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextField1);
        jTextField1.setBounds(53, 142, 586, 32);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // Định dạng font chữ cho nút tải xuống
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Download");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt); // Xử lý khi nhấn nút Download
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(267, 206, 171, 40);
        
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 700, 300);

        pack();
    }// </editor-fold>                        
    private static ObjectInputStream inputStream = null;
    private FileEvent fileEvent = null;
    private File dstFile = null;
    private FileOutputStream fileOutputStream = null;

	/* Hàm tải xuống file */
    public void downloadFile() {
        try {

		/* Quản lý file */
            fileEvent = (FileEvent) inputStream.readObject();
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {
                System.out.println("Có lỗi xảy ra ..Thoát chương trình");
                System.exit(0);
            }
            String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
            if (!new File(fileEvent.getDestinationDirectory()).exists()) {
                new File(fileEvent.getDestinationDirectory()).mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }

		/* Tạo file đích */
            dstFile = new File(outputFile);

		/* Gửi file dưới dạng đối tượng output */
            fileOutputStream = new FileOutputStream(dstFile);
            fileOutputStream.write(fileEvent.getFileData());
            fileOutputStream.flush();
            fileOutputStream.close();

		/* Thông báo tải xuống thành công */
            System.out.println("File output : " + outputFile + " đã được lưu thành công ");
            JOptionPane.showMessageDialog(null, "Tải file thành công!");
            Thread.sleep(3000);
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
           
            String serverName = ip; // Địa chỉ IP của server
            Socket sock = new Socket(serverName, 1234); // Kết nối với server qua cổng 1234
            this.setBounds(550, 150, 700, 300);
            this.setResizable(false);

		/* Đọc đường dẫn từ ô nhập liệu */
            String keyRead = jTextField1.getText();
            String sourceFilePath2 = keyRead;
            System.out.println("Đường dẫn là: " + sourceFilePath2);
          
               /* Lấy luồng output và tải file xuống */
	    OutputStream ostream = null;
            ostream = sock.getOutputStream();
            PrintWriter pwrite = new PrintWriter(ostream, true);
            pwrite.println(sourceFilePath2 + " download"); // Gửi yêu cầu tải xuống file
            inputStream = new ObjectInputStream(sock.getInputStream());
           
		/* Gọi hàm tải file */
            this.downloadFile();
            sock.close();
            ostream.close();
            pwrite.close();

        } catch (IOException ex) {
            Logger.getLogger(downloadfileform.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                        

    public static void main(String args[]) {
     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName()); // Cài đặt giao diện Nimbus
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(downloadfileform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(downloadfileform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(downloadfileform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(downloadfileform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Tạo và hiển thị giao diện */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new downloadfileform(ip).setVisible(true); // Hiển thị form download file
            }
        });
    }

    // Khai báo các biến không sửa đổi                      
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    // Kết thúc khai báo biến                   
}
