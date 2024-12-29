package remove_desktop_rdp.client;

import remove_desktop_rdp.server.Server;
import remove_desktop_rdp.server.serverfile;
import remove_desktop_rdp.server.servermsg;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class clientfirstpage extends javax.swing.JFrame {

    // Khởi tạo JFrame
    public clientfirstpage() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")                 
    private void initComponents() {

        jButtonConnect = new javax.swing.JButton();
        ipAddress = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButtonFileTransfer = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButtonConnect.setText("Connect");
        jButtonConnect.setFont(new java.awt.Font("Dialog", 1, 18)); // Font chữ đậm và kích thước lớn
        jButtonConnect.setBackground(new java.awt.Color(30, 144, 255)); // Màu nền xanh dương
        jButtonConnect.setForeground(new java.awt.Color(255, 255, 255)); // Màu chữ trắng
        jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnectActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonConnect);
        jButtonConnect.setBounds(440, 60, 150, 55);

        ipAddress.setBackground(new java.awt.Color(255, 255, 255));
        ipAddress.setFont(new java.awt.Font("Dialog", 0, 24)); 
        ipAddress.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(ipAddress);
        ipAddress.setBounds(50, 60, 370, 55);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); 
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nhập địa chỉ IP để kết nối:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 130, 316, 24);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 28)); 
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Địa chỉ IP của bạn:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 240, 250, 37);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); 
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Máy tính của bạn có thể được truy cập bởi:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 300, 320, 24);

        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("Dialog", 0, 24)); 
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextField2);
        jTextField2.setBounds(50, 340, 370, 42);

        jButtonFileTransfer.setText("File Transfer");
        jButtonFileTransfer.setFont(new java.awt.Font("Dialog", 1, 18)); 
        jButtonFileTransfer.setBackground(new java.awt.Color(30, 144, 255)); 
        jButtonFileTransfer.setForeground(new java.awt.Color(255, 255, 255)); 
        jButtonFileTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFileTransferActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFileTransfer);
        jButtonFileTransfer.setBounds(610, 60, 180, 55);

        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, 0, 1930, 1050);

        pack();
    }// </editor-fold>                        

    private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // Kiểm tra nếu ô địa chỉ IP để trống
        if (ipAddress.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ IP để kết nối");
        } else {
            // Khởi tạo cửa sổ Chat và Chia sẻ Màn hình
            Client c = new Client(ipAddress.getText());
            clientmsg c1 = new clientmsg(ipAddress.getText());
            c1.setBounds(0, 0, 800, 700);
            c1.setResizable(false);
            c1.setVisible(true);
        }
    }                                           

    private void jButtonFileTransferActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // Kiểm tra nếu ô địa chỉ IP để trống
        if (ipAddress.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ IP để kết nối");
        } else {
            // Khởi tạo Cửa sổ Chuyển Tệp
            clientfileform c = new clientfileform(ipAddress.getText());
            c.setBounds(550, 150, 800, 700);
            c.setResizable(false);
            c.setVisible(true);
        }
    }                                           

    public static void main(String args[]) throws UnknownHostException {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Đặt địa chỉ IP của máy chủ trong frame
                    new clientfirstpage().setVisible(true);
                    
                    // Lấy địa chỉ IP của máy chủ
                    InetAddress IP = InetAddress.getLocalHost();
                    System.out.println("Địa chỉ IP của tôi là:");
                    System.out.println(IP.getHostAddress());
                    String x = IP.getHostAddress();
                    jTextField2.setText(x);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(clientfirstpage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
               
    public javax.swing.JTextField ipAddress;
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JButton jButtonFileTransfer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    public static javax.swing.JTextField jTextField2;
             
}
