package remove_desktop_rdp.server;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class serverstartscreen extends javax.swing.JFrame {

    // Phương thức khởi tạo cho màn hình server
    public serverstartscreen() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Thiết lập cửa sổ ở chế độ toàn màn hình
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout()); // Sử dụng GridBagLayout cho giao diện
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Thêm khoảng cách giữa các thành phần

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 28));
        jLabel1.setForeground(Color.BLACK);
        jLabel1.setText("Welcome to Remote Desktop Connector!");
        gbc.gridx = 0; // Đặt vào cột 0
        gbc.gridy = 0; // Đặt vào hàng 0
        gbc.gridwidth = 2; // Chiếm 2 cột
        getContentPane().add(jLabel1, gbc);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24));
        jLabel2.setForeground(Color.BLACK);
        jLabel2.setText("Server, vui lòng chọn một trong các tùy chọn bên dưới để kết nối với client của bạn");
        gbc.gridy = 1; // Đặt vào hàng 1
        getContentPane().add(jLabel2, gbc);

        // Thay đổi màu nút thành đen đậm
        jButton1.setBackground(new java.awt.Color(0, 0, 0)); // Đặt màu nền đen
        jButton1.setFont(new java.awt.Font("Dialog", 1, 18));
        jButton1.setForeground(Color.WHITE); // Đặt màu chữ trắng
        jButton1.setText("Chat");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gbc.gridy = 2; // Đặt vào hàng 2
        gbc.gridwidth = 1; // Chiếm 1 cột
        getContentPane().add(jButton1, gbc);

        jButton2.setBackground(new java.awt.Color(0, 0, 0)); // Đặt màu nền đen
        jButton2.setFont(new java.awt.Font("Dialog", 1, 18));
        jButton2.setForeground(Color.WHITE); // Đặt màu chữ trắng
        jButton2.setText("File Transfer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton2ActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(serverstartscreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        gbc.gridx = 1; // Đặt vào cột 1
        getContentPane().add(jButton2, gbc);

        // Đặt hình ảnh nền cho màn hình
        URL imageUrl = getClass().getClassLoader().getResource("remove_desktop_rdp/server/svscr.jpg");
        if (imageUrl != null) {
            ImageIcon imageIcon = new ImageIcon(imageUrl);
            jLabel3.setIcon(imageIcon);
        } else {
            System.out.println("Không tìm thấy hình ảnh!");
        }
        gbc.gridx = 0; // Đặt vào cột 0
        gbc.gridy = 3; // Đặt vào hàng 3
        gbc.gridwidth = 2; // Chiếm 2 cột
        getContentPane().add(jLabel3, gbc);

        pack(); // Tự động điều chỉnh kích thước giao diện
    }

    // Sự kiện khi nhấn nút "Chat" để mở giao diện chat server
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        servermsg s = new servermsg();
        s.setBounds(0, 0, 800, 700); // Thiết lập kích thước giao diện
        s.setResizable(false); // Không cho phép thay đổi kích thước
        s.setVisible(true); // Hiển thị giao diện
    }

    // Sự kiện khi nhấn nút "File Transfer" để mở giao diện chuyển file
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        serverfile s = new serverfile();
        s.fileServer();
    }

    // Phương thức main để khởi chạy ứng dụng
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(serverstartscreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(serverstartscreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(serverstartscreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(serverstartscreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new serverstartscreen().setVisible(true); // Hiển thị giao diện chính của server
            }
        });
    }

    // Khai báo các thành phần giao diện
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
}
